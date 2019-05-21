package com.myweb.content.dto;

public class ApiClientDTO {

    private String clientId;

    private String clientSecret;

    private String purpose;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getPurpose() {
        return purpose;
    }
}
