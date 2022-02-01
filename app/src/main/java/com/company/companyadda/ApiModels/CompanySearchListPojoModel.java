package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.ComapnySearchPOjo;

import java.util.List;

public class CompanySearchListPojoModel {

    public CompanySearchListPojoModel(List<ComapnySearchPOjo> data) {
        this.data = data;
    }

    public List<ComapnySearchPOjo> getData() {
        return data;
    }

    public void setData(List<ComapnySearchPOjo> data) {
        this.data = data;
    }

    List<ComapnySearchPOjo> data;
}
