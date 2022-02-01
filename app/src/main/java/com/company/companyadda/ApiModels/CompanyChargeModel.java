package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.CompanyChargesPojo;

import java.util.List;

public class CompanyChargeModel {
    public CompanyChargeModel(List<CompanyChargesPojo> data) {
        this.data = data;
    }

    public List<CompanyChargesPojo> getData() {
        return data;
    }

    public void setData(List<CompanyChargesPojo> data) {
        this.data = data;
    }

    List<CompanyChargesPojo> data;
}
