package clients;

import dto.ContractDto;
import dto.CustomerDto;
import dto.SendContractStatusDto;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by domin on 17-Jan-20.
 */
public class BrevTjenesteClient {
    private CustomerDto customer;
    private ContractDto contract;
    private RestTemplate restTemplate;
    private String endpoint = "http://localhost:8080/brevtjeneste/rs";

    public BrevTjenesteClient(CustomerDto customer, ContractDto contract) {
        if(customer == null)
            throw new IllegalArgumentException("Kunden er ugyldig!");
        else if(contract == null)
            throw new IllegalArgumentException("Avtalen er ugyldig!");
        this.customer = customer;
        this.contract = contract;
    }

    public SendContractStatusDto sendContract() {
        restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mj2h = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(mj2h);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(endpoint);
        stringBuilder.append("/sendContract?");
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
        stringBuilder.append("&contractName=");
        stringBuilder.append(this.contract.getName());
        stringBuilder.append("&contractType=");
        stringBuilder.append(this.contract.getType());
        stringBuilder.append("&contractStatus=");
        stringBuilder.append(this.contract.getContractStatus());
        SendContractStatusDto sendContractStatus = restTemplate.getForObject(stringBuilder.toString(), SendContractStatusDto.class);
        return sendContractStatus;
    }
}
