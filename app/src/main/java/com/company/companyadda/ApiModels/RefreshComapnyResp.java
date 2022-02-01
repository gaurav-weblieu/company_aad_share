package com.company.companyadda.ApiModels;

public class RefreshComapnyResp {
    public RefreshComapnyResp(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String message;
}
