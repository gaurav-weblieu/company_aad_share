package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.DirRelatedPojo;

import java.util.List;

public class DirRelatedComp {
    public DirRelatedComp(List<DirRelatedPojo> data) {
        this.data = data;
    }

    public List<DirRelatedPojo> getData() {
        return data;
    }

    public void setData(List<DirRelatedPojo> data) {
        this.data = data;
    }

    List<DirRelatedPojo> data;
}
