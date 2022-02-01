package com.company.companyadda.ApiModels;

import com.company.companyadda.CompanyDetailsPojo.DataPojo;
import com.company.companyadda.DirectorDetailsPojo.DirectorDetailData;

public class DirectorDetailsPojoClass {

    String transaction_id;

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

    public DirectorDetailData getData() {
        return data;
    }

    public void setData(DirectorDetailData data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    String code;
    DirectorDetailData data;
    String timestamp;

    public DirectorDetailsPojoClass(String transaction_id, String code, DirectorDetailData data, String timestamp) {
        this.transaction_id = transaction_id;
        this.code = code;
        this.data = data;
        this.timestamp = timestamp;
    }











}
