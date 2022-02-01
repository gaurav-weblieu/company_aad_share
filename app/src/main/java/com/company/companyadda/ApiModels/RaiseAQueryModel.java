package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.CapitalResult;
import com.google.gson.annotations.SerializedName;

public class RaiseAQueryModel {
    public RaiseAQueryModel(CapitalResult result) {
        this.result = result;
    }

    public CapitalResult getResult() {
        return result;
    }

    public void setResult(CapitalResult result) {
        this.result = result;
    }

    @SerializedName("result")
    private CapitalResult result;
}
