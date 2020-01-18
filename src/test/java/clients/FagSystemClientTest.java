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
 * Created by domin on 17-Jan-20.
 */
@RunWith(MockitoJUnitRunner.class)
public class FagSystemClientTest {
    @Mock
    private RestTemplate restTemplate;
    private FagSystemClient fagSystemClient;
    private String endpoint = "http://localhost:8080/fagsystem/rs";
    private CustomerDto customer;
    private ContractDto contract;

    @Test(expected = IllegalArgumentException.class)
    public void constructorCustomerExceptionTest(){
        CustomerDto customer = null;
        fagSystemClient = new FagSystemClient(customer);
    }

    @Test
    public void testCustomerConstructor() throws Exception {
        //this.customer = null;
        this.customer = new CustomerDto(0, "Ola", "Nordmann", "01.01.2000", "Ole Brum gata 1", "0194", "Oslo", "123456789", "ole@yahoo.no");
        fagSystemClient = new FagSystemClient(this.customer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorContractExceptionTest(){
        ContractDto contract = null;
        fagSystemClient = new FagSystemClient(contract);
    }

    @Test
    public void testContractConstructor() throws Exception {
        //this.contract = null;
        this.contract = new ContractDto(1L, 1L, "HelseForsikring", "Privat", "");
        fagSystemClient = new FagSystemClient(this.contract);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorContractAndCustomerExceptionTest(){
        ContractDto contract = null;
        CustomerDto customer = null;
        fagSystemClient = new FagSystemClient(customer, contract);
    }

    @Test
    public void testCustomerContractConstructor() throws Exception {
        //this.customer = null;
        //this.contract = null;
        this.customer = new CustomerDto(0, "Ola", "Nordmann", "01.01.2000", "Ole Brum gata 1", "0194", "Oslo", "123456789", "ole@yahoo.no");
        this.contract = new ContractDto(1L, 1L, "HelseForsikring", "Privat", "");
        fagSystemClient = new FagSystemClient(this.customer, this.contract);
    }

    @Test
    public void testCreateCustomer() throws Exception {
        CustomerDto customer = new CustomerDto(0, "Ola", "Nordmann", "01.01.2000", "Ole Brum gata 1", "0194", "Oslo", "123456789", "ole@yahoo.no");
        CustomerDto cust = new CustomerDto(1, "Ola", "Nordmann", "01.01.2000", "Ole Brum gata 1", "0194", "Oslo", "123456789", "ole@yahoo.no");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(endpoint);
        stringBuilder.append("/createCustomer?");
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
        Mockito.when(restTemplate.getForObject(stringBuilder.toString(), CustomerDto.class)).thenReturn(cust);
        customer= restTemplate.getForObject(stringBuilder.toString(), CustomerDto.class);
        assertThat(customer.getId(), is(1L));
    }
    @Test
    public void testCreateContract() throws Exception {
        CustomerDto customer = new CustomerDto(1L, "Ola", "Nordmann", "01.01.2000", "Ole Brum gata 1", "0194", "Oslo", "123456789", "ole@yahoo.no");
        ContractDto contract = new ContractDto(0, 1L, "HelseForsikring", "Privat", "");
        ContractDto cont = new ContractDto(1, 1L, "HelseForsikring", "Privat", "");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(endpoint);
        stringBuilder.append("/createContract?");
        stringBuilder.append("customerId=");
        stringBuilder.append(customer.getId());
        stringBuilder.append("&contractName=");
        stringBuilder.append(contract.getName());
        stringBuilder.append("&contractType=");
        stringBuilder.append(contract.getType());
        stringBuilder.append("&contractStatus=");
        stringBuilder.append(contract.getContractStatus());

        MappingJackson2HttpMessageConverter mj2h = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(mj2h);
        Mockito.when(restTemplate.getForObject(stringBuilder.toString(), ContractDto.class)).thenReturn(cont);
        contract = restTemplate.getForObject(stringBuilder.toString(), ContractDto.class);
        assertThat(contract.getContractId(), is(1L));
    }
    @Test
    public void testUpdateSendContractStatus() throws Exception {
        ContractDto contract = new ContractDto(1L, 1L, "HelseForsikring", "Privat", "");
        ContractDto cont = new ContractDto(1, 1L, "HelseForsikring", "Privat", "ACTIVE");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(endpoint);
        stringBuilder.append("/sendContract?");
        stringBuilder.append("contractId=");
        stringBuilder.append(contract.getContractId());

        MappingJackson2HttpMessageConverter mj2h = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(mj2h);
        Mockito.when(restTemplate.getForObject(stringBuilder.toString(), ContractDto.class)).thenReturn(cont);
        contract = restTemplate.getForObject(stringBuilder.toString(), ContractDto.class);
        assertThat(contract.getContractStatus(), is("ACTIVE"));
    }
}