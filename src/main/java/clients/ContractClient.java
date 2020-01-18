package clients;

import dto.CustomerDto;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


/**
 * Created by domin on 16-Jan-20.
 */
public class ContractClient {

    private CustomerDto customer;
    private RestTemplate restTemplate;
    private String endpoint = "http://localhost:8080/integration/rs";
    public ContractClient(CustomerDto customer) {
        if(customer == null)
            throw new IllegalArgumentException("Kunden er ugyldig!");
        this.customer = customer;
    }

    public ResponseEntity<String> createContract(){
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(endpoint);
        stringBuilder.append("/create?");
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

        ResponseEntity responseEntity = restTemplate.exchange(stringBuilder.toString(), HttpMethod.GET, entity, String.class);
        return responseEntity;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
}
