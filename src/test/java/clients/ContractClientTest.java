package clients;

import dto.CustomerDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by domin on 17-Jan-20.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractClientTest {
    @Mock
    private RestTemplate restTemplate;
    private ContractClient contractClient;
    private String endpoint = "http://localhost:8080/integration/rs";
    private CustomerDto customer;

    @Test(expected = IllegalArgumentException.class)
    public void constructorExceptionTest(){
        contractClient = new ContractClient(null);
    }

    @Test
    public void testContractClientConstructor() throws Exception {
        //this.customer = null;
        this.customer = new CustomerDto(0, "Ola", "Nordmann", "01.01.2000", "Ole Brum gata 1", "0194", "Oslo", "123456789", "ole@yahoo.no");
        contractClient = new ContractClient(this.customer);
    }

    @Test
    public void testCreateContract() throws Exception {
        CustomerDto customer = new CustomerDto(0, "Ola", "Nordmann", "01.01.2000", "Ole Brum gata 1", "0194", "Oslo", "123456789", "ole@yahoo.no");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(endpoint);
        stringBuilder.append("/create?");
        stringBuilder.append("forName=");
        stringBuilder.append(customer.getForName());
        stringBuilder.append("&lastName=");
        stringBuilder.append(customer.getLastName());
        stringBuilder.append("&birthday=");
        stringBuilder.append(customer.getBirthday());
        stringBuilder.append("&address=");
        stringBuilder.append(customer.getAddress());
        stringBuilder.append("&postNumber=");
        stringBuilder.append(customer.getPostNumber());
        stringBuilder.append("&postArea=");
        stringBuilder.append(customer.getPostArea());
        stringBuilder.append("&phoneNumber=");
        stringBuilder.append(customer.getPhoneNumber());
        stringBuilder.append("&email=");
        stringBuilder.append(customer.getEmail());
        MappingJackson2HttpMessageConverter mj2h = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(mj2h);
        Mockito.when(restTemplate.exchange(stringBuilder.toString(), HttpMethod.GET, entity, CustomerDto.class)).thenReturn(new ResponseEntity(HttpStatus.OK));
        ResponseEntity out = restTemplate.exchange(stringBuilder.toString(), HttpMethod.GET, entity, CustomerDto.class);
        assertThat(out.getStatusCode(), is(HttpStatus.OK) );
    }
}