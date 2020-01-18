package clients;

import dto.ContractDto;
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

/**
 * Created by domin on 18-Jan-20.
 */
@RunWith(MockitoJUnitRunner.class)
public class KlientClientTest {
    @Mock
    private RestTemplate restTemplate;
    private KlientClient klientClient;
    private String endpoint = "http://localhost:8080/klient/rs";
    private CustomerDto customer;
    private ContractDto contract;

    @Test(expected = IllegalArgumentException.class)
    public void constructorContractAndCustomerExceptionTest(){
        ContractDto contract = null;
        CustomerDto customer = null;
        klientClient = new KlientClient(customer, contract);
    }

    @Test
    public void testCustomerContractConstructor() throws Exception {
        //this.customer = null;
        //this.contract = null;
        this.customer = new CustomerDto(0, "Ola", "Nordmann", "01.01.2000", "Ole Brum gata 1", "0194", "Oslo", "123456789", "ole@yahoo.no");
        this.contract = new ContractDto(1L, 1L, "HelseForsikring", "Privat", "");
        klientClient = new KlientClient(this.customer, this.contract);
    }

    @Test
    public void testSendContractToClient() throws Exception {
        CustomerDto customer = new CustomerDto(1L, "Ola", "Nordmann", "01.01.2000", "Ole Brum gata 1", "0194", "Oslo", "123456789", "ole@yahoo.no");
        ContractDto contract = new ContractDto(1L, 1L, "HelseForsikring", "Privat", "ACTIVE");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(endpoint);
        stringBuilder.append("/sendContract?");
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
        stringBuilder.append("&contractName=");
        stringBuilder.append(contract.getName());
        stringBuilder.append("&contractType=");
        stringBuilder.append(contract.getType());
        stringBuilder.append("&contractStatus=");
        stringBuilder.append(contract.getContractStatus());
        MappingJackson2HttpMessageConverter mj2h = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(mj2h);
        Mockito.when(restTemplate.exchange(stringBuilder.toString(), HttpMethod.GET, entity, CustomerDto.class)).thenReturn(new ResponseEntity(HttpStatus.OK));
        ResponseEntity responseEntity = restTemplate.exchange(stringBuilder.toString(), HttpMethod.GET, entity, CustomerDto.class);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK) );
    }
}