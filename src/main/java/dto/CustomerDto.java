package dto;

import java.io.Serializable;

/**
 * Created by domin on 17-Jan-20.
 */
public class CustomerDto implements Serializable {

    private long id;
    private String forName;
    private String lastName;
    private String birthday;
    private String address;
    private String postNumber;
    private String postArea;
    private String phoneNumber;
    private String email;

    public CustomerDto() {
    }
    public CustomerDto(long id, String forName, String lastName, String birthday, String address, String postNumber, String postArea, String phoneNumber, String email) {
        this.id = id;
        this.forName = forName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.postNumber = postNumber;
        this.postArea = postArea;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getForName() {
        return forName;
    }

    public void setForName(String forName) {
        this.forName = forName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }

    public String getPostArea() {
        return postArea;
    }

    public void setPostArea(String postArea) {
        this.postArea = postArea;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
