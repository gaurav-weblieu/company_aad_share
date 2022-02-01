package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.PanDataPojo;

public class PanDetailsModel {
    String code;
    String timestamp;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public PanDataPojo getData() {
        return data;
    }

    public void setData(PanDataPojo data) {
        this.data = data;
    }

    String transaction_id;
    PanDataPojo data;

    public PanDetailsModel(String code, String timestamp, String transaction_id, PanDataPojo data) {
        this.code = code;
        this.timestamp = timestamp;
        this.transaction_id = transaction_id;
        this.data = data;
    }


}
