package com.company.companyadda.ApiModels;

import com.company.companyadda.CompanyDetailsPojo.DataPojo;
import com.company.companyadda.CompanyDetailsPojo.DirectorsSignatoryDetail;
import com.company.companyadda.CompanyDetailsPojo.company_master_data;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyDetailsPojoClass {
    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataPojo getData() {
        return data;
    }

    public void setData(DataPojo data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    String transaction_id;
    String code;
    DataPojo data;
    String timestamp;

    public CompanyDetailsPojoClass(String transaction_id, String code, DataPojo data, String timestamp) {
        this.transaction_id = transaction_id;
        this.code = code;
        this.data = data;
        this.timestamp = timestamp;
    }







}
