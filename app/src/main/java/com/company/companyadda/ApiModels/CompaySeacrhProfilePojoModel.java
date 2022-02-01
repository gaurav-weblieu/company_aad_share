package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.SearchCompanyProfilePojo;

public class CompaySeacrhProfilePojoModel
{

    public CompaySeacrhProfilePojoModel(SearchCompanyProfilePojo data, String message) {
        this.data = data;
        this.message = message;
    }

    SearchCompanyProfilePojo data;

    public SearchCompanyProfilePojo getData() {
        return data;
    }

    public void setData(SearchCompanyProfilePojo data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String message;

}
