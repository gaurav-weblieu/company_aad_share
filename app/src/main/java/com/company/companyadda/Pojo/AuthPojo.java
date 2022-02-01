package com.company.companyadda.Pojo;

public class AuthPojo {
    public AuthPojo(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    String access_token;
}
