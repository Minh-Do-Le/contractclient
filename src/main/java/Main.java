import clients.ContractClient;
import clients.FagSystemClient;
import dto.CustomerDto;
import org.springframework.http.ResponseEntity;


/**
 * Created by domin on 16-Jan-20.
 */
public class Main {
    public static void main(String[] args){
        CustomerDto customer = new CustomerDto(0, "Ola", "Nordmann", "01.01.2000","Ole Brum gata 1", "0194", "Oslo", "+47123456789", "ole@yahoo.no");
        ContractClient contractClient = new ContractClient(customer);
        ResponseEntity<String> responseEntity = contractClient.createContract();
        System.out.println("Status code: " + responseEntity.getStatusCodeValue());
    }
}
