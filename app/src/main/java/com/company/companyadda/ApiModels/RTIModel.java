package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.CapitalResult;
import com.company.companyadda.Pojo.RTIPojo;

import java.util.List;

public class RTIModel {

    public RTIModel(CapitalResult result, List<RTIPojo> free_ITR_Details) {
        this.result = result;
        Free_ITR_Details = free_ITR_Details;
    }

    CapitalResult result;

    public CapitalResult getResult() {
        return result;
    }

    public void setResult(CapitalResult result) {
        this.result = result;
    }

    public List<RTIPojo> getFree_ITR_Details() {
        return Free_ITR_Details;
    }

    public void setFree_ITR_Details(List<RTIPojo> free_ITR_Details) {
        Free_ITR_Details = free_ITR_Details;
    }

    List<RTIPojo> Free_ITR_Details;
}
