package com.dbs.payment.backend.DTO;

import java.util.Set;

public class SignupDTO {

    private String username;

    private String password;

    private String customerId;

    private Set<String> rolename;

    private String phone;

    private String ipAddress;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<String> getRolename() {
        return rolename;
    }

    public void setRolename(Set<String> rolename) {
        this.rolename = rolename;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
