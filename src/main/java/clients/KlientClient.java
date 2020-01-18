package clients;

import dto.ContractDto;
import dto.CustomerDto;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by domin on 18-Jan-20.
 */
public class KlientClient {
    private CustomerDto customer;
    private ContractDto contract;
    private RestTemplate restTemplate;
    private String endpoint = "http://localhost:8080/klient/rs";

    public KlientClient(CustomerDto customer, ContractDto contract) {
        if(customer == null)
            throw new IllegalArgumentException("Kunden er ugyldig!");
        else if(contract == null)
            throw new IllegalArgumentException("Avtalen er ugyldig!");
        this.customer = customer;
        this.contract = contract;
    }
    public ResponseEntity<String> sendContractToClient(){
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
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

        ResponseEntity responseEntity = restTemplate.exchange(stringBuilder.toString(), HttpMethod.GET, entity, String.class);
        return responseEntity;
    }
}
