package dto;

import java.io.Serializable;

/**
 * Created by domin on 17-Jan-20.
 */
public class SendContractStatusDto implements Serializable{
    private long contractId;
    private String sendStatus;

    public SendContractStatusDto() {
    }
    public SendContractStatusDto(long contractId, String sendStatus) {
        this.contractId = contractId;
        this.sendStatus = sendStatus;
    }

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }
}
