package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.CapitalResult;
import com.company.companyadda.Pojo.ServiceListPojo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceModels {

    public List<ServiceListPojo> getService_list() {
        return Service_list;
    }

    public void setService_list(List<ServiceListPojo> service_list) {
        Service_list = service_list;
    }

    public CapitalResult getResult() {
        return result;
    }

    public void setResult(CapitalResult result) {
        this.result = result;
    }

    @SerializedName("Service_List")
    private List<ServiceListPojo> Service_list;

    @SerializedName("result")
    private CapitalResult result;


    public ServiceModels(List<ServiceListPojo> service_list, CapitalResult result) {
        Service_list = service_list;
        this.result = result;
    }




}
