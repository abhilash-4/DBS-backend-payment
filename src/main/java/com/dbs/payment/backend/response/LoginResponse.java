package com.dbs.payment.backend.response;

import java.util.List;

public class LoginResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private List<String> roles;
    private String otpStatus;
    private String username;
    private boolean success;


    public LoginResponse(String accessToken, Long id,String otpStatus,List<String> roles, String username,boolean success) {
        this.otpStatus = otpStatus;
        this.token = accessToken;
        this.id = id;
        this.roles = roles;
        this.username = username;
        this.success=success;
    }


    public Long getId() {
        return id;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getOtpStatus() {
        return otpStatus;
    }

    public void setOtpStatus(String otpStatus) {
        this.otpStatus = otpStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public boolean isSuccess() {
        success=true;
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
