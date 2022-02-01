package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.CapitalResult;
import com.company.companyadda.Pojo.DocPojo;

import java.util.List;

public class DocModel {
    CapitalResult result;

    public CapitalResult getResult() {
        return result;
    }

    public void setResult(CapitalResult result) {
        this.result = result;
    }

    public List<DocPojo> getDocs_Details() {
        return Docs_Details;
    }

    public void setDocs_Details(List<DocPojo> docs_Details) {
        Docs_Details = docs_Details;
    }

    List<DocPojo> Docs_Details;


    public DocModel(CapitalResult result, List<DocPojo> docs_Details) {
        this.result = result;
        Docs_Details = docs_Details;
    }

}
