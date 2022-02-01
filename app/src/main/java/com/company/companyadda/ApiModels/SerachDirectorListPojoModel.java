package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.SearchDirectorPojo;

import java.util.List;

public class SerachDirectorListPojoModel {

    public SerachDirectorListPojoModel(List<SearchDirectorPojo> data, String message) {
        this.data = data;
        this.message = message;
    }

    public List<SearchDirectorPojo> getData() {
        return data;
    }

    public void setData(List<SearchDirectorPojo> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    List<SearchDirectorPojo> data;
    String message;
}
