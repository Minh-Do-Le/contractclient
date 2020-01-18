package dto;

import java.io.Serializable;

/**
 * Created by domin on 17-Jan-20.
 */
public class ContractDto implements Serializable {
    private long contractId;
    private long customerId;
    private String name;
    private String type;
    private String contractStatus;

    public ContractDto() {
    }

    public ContractDto(long contractId, long customerId, String name, String type, String contractStatus) {
        this.contractId = contractId;
        this.customerId = customerId;
        this.name = name;
        this.type = type;
        this.contractStatus = contractStatus;
    }

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }
}
