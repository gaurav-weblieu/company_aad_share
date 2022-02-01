package com.company.companyadda.Pojo;

import com.google.gson.annotations.SerializedName;

public class CapitalResult {
    private String MESSAGE;
    private String STATUS;

    public CapitalResult(String MESSAGE, String STATUS) {
        this.MESSAGE = MESSAGE;
        this.STATUS = STATUS;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public String getSTATUS() {
        return STATUS;
    }
}
