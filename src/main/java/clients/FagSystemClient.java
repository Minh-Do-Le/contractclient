package clients;

import dto.ContractDto;
import dto.CustomerDto;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by domin on 17-Jan-20.
 */
public class FagSystemClient {
    private CustomerDto customer;
    private ContractDto contract;
    private String endpoint = "http://localhost:8080/fagsystem/rs";

    public FagSystemClient(CustomerDto customer) {
        if(customer == null)
            throw new IllegalArgumentException("Kunden er ugyldig!");
        this.customer = customer;
    }

    public FagSystemClient(ContractDto contract) {
        if(contract == null)
            throw new IllegalArgumentException("Avtalen er ugyldig!");
        this.contract = contract;
    }

    public FagSystemClient(CustomerDto customer, ContractDto contract) {
        if(customer == null)
            throw new IllegalArgumentException("Kunden er ugyldig!");
        else if(contract == null)
            throw new IllegalArgumentException("Avtalen er ugyldig!");
        this.customer = customer;
        this.contract = contract;
    }

    public CustomerDto createCustomer() {
        RestTemplate rt = new RestTemplate();
        MappingJackson2HttpMessageConverter mj2h = new MappingJackson2HttpMessageConverter();
        rt.getMessageConverters().add(mj2h);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(endpoint);
        stringBuilder.append("/createCustomer?");
        stringBuilder.append("forName=");
        stringBuilder.append(this.customer.getForName());
        stringBuilder.append("&lastName=");
        stringBuilder.append(this.customer.getLastName());
        stringBuilder.append("&birthday=");
        stringBuilder.append(this.customer.getBirthday());
        stringBuilder.append("&address=");
        stringBuilder.append(this.customer.getAddress());
        stringBuilder.append("&postNumber=");
        stringBuilder.append(this.customer.getPostNumber());
        stringBuilder.append("&postArea=");
        stringBuilder.append(this.customer.getPostArea());
        stringBuilder.append("&phoneNumber=");
        stringBuilder.append(this.customer.getPhoneNumber());
        stringBuilder.append("&email=");
        stringBuilder.append(this.customer.getEmail());
        CustomerDto customer = rt.getForObject(stringBuilder.toString(), CustomerDto.class);
        return customer;
    }
    public ContractDto createContract() {
        RestTemplate rt = new RestTemplate();
        MappingJackson2HttpMessageConverter mj2h = new MappingJackson2HttpMessageConverter();
        rt.getMessageConverters().add(mj2h);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(endpoint);
        stringBuilder.append("/createContract?");
        stringBuilder.append("customerId=");
        stringBuilder.append(this.customer.getId());
        stringBuilder.append("&contractName=");
        stringBuilder.append(this.contract.getName());
        stringBuilder.append("&contractType=");
        stringBuilder.append(this.contract.getType());
        stringBuilder.append("&contractStatus=");
        stringBuilder.append(this.contract.getContractStatus());

        ContractDto contract = rt.getForObject(stringBuilder.toString(), ContractDto.class);
        return contract;
    }
    public ContractDto updateSendContractStatus() {
        RestTemplate rt = new RestTemplate();
        MappingJackson2HttpMessageConverter mj2h = new MappingJackson2HttpMessageConverter();
        rt.getMessageConverters().add(mj2h);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(endpoint);
        stringBuilder.append("/updateContractStatus?");
        stringBuilder.append("contractId=");
        stringBuilder.append(this.contract.getContractId());
        ContractDto contract = rt.getForObject(stringBuilder.toString(), ContractDto.class);
        return contract;
    }
}
