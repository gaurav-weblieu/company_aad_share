package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.CapitalResult;
import com.google.gson.annotations.SerializedName;

public class ReferModel {
    public ReferModel(CapitalResult result) {
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
