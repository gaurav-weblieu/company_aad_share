package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.BannerPojo;
import com.company.companyadda.Pojo.CapitalResult;
import com.company.companyadda.Pojo.LoginPojo;
import com.company.companyadda.Pojo.PagesDetail;
import com.company.companyadda.Pojo.PaymentsKeys;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginModels {

    public LoginModels(LoginPojo result) {
        this.result = result;
    }

    public LoginPojo getResult() {
        return result;
    }

    public void setResult(LoginPojo result) {
        this.result = result;
    }


    @SerializedName("result")
    LoginPojo result;

}
