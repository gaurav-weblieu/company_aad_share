   package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.CapitalResult;
import com.company.companyadda.Pojo.ServiceListPojo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FormModels {

    @SerializedName("Quotes")
    private List<String> Quotes;

    public List<String> getQuotes() {
        return Quotes;
    }

    public CapitalResult getResult() {
        return result;
    }

    @SerializedName("result")
    private CapitalResult result;

    public FormModels(List<String> quotes, CapitalResult result) {
        Quotes = quotes;
        this.result = result;
    }









}
