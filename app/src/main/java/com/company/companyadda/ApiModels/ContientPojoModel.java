package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.AllcontientPojo;
import com.company.companyadda.Pojo.ArticlesListPojo;

import java.util.ArrayList;
import java.util.List;

public class ContientPojoModel {


    public ContientPojoModel(ArrayList<AllcontientPojo> allcontientPojos) {
        this.allcontientPojos = allcontientPojos;
    }

    public ArrayList<AllcontientPojo> getAllcontientPojos() {
        return allcontientPojos;
    }

    public void setAllcontientPojos(ArrayList<AllcontientPojo> allcontientPojos) {
        this.allcontientPojos = allcontientPojos;
    }

    ArrayList<AllcontientPojo> allcontientPojos;
}
