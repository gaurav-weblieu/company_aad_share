package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.CapitalResult;
import com.company.companyadda.Pojo.PaymentPojo;

import java.util.List;

public class PaymentPojoModel {
    List<PaymentPojo> Payment;

    public List<PaymentPojo> getProfile() {
        return Payment;
    }

    public void setProfile(List<PaymentPojo> Payment) {
        Payment = Payment;
    }

    public CapitalResult getResult() {
        return result;
    }

    public void setResult(CapitalResult result) {
        this.result = result;
    }

    CapitalResult result;


    public PaymentPojoModel(List<PaymentPojo> Payment, CapitalResult result) {
        Payment = Payment;
        this.result = result;
    }

}
