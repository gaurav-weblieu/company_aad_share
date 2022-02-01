package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.CapitalResult;
import com.company.companyadda.Pojo.MyRequestDetailsPojo;

import java.util.List;

public class MyRequestDetailsModel {

    List<MyRequestDetailsPojo> Request_List;

    public List<MyRequestDetailsPojo> getRequest_List() {
        return Request_List;
    }

    public void setRequest_List(List<MyRequestDetailsPojo> request_List) {
        Request_List = request_List;
    }

    public CapitalResult getResult() {
        return result;
    }

    public void setResult(CapitalResult result) {
        this.result = result;
    }

    CapitalResult result;

    public MyRequestDetailsModel(List<MyRequestDetailsPojo> request_List, CapitalResult result) {
        Request_List = request_List;
        this.result = result;
    }



}
