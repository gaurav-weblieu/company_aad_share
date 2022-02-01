package com.company.companyadda.CompanyDetailsPojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataPojo {

    company_master_data company_master_data;
    List<Object> charges;

    public com.company.companyadda.CompanyDetailsPojo.company_master_data getCompany_master_data() {
        return company_master_data;
    }

    public void setCompany_master_data(com.company.companyadda.CompanyDetailsPojo.company_master_data company_master_data) {
        this.company_master_data = company_master_data;
    }

    public List<Object> getCharges() {
        return charges;
    }

    public void setCharges(List<Object> charges) {
        this.charges = charges;
    }

    public List<DirectorsSignatoryDetail> getDirectorsSignatoryDetails() {
        return DirectorsSignatoryDetails;
    }

    public void setDirectorsSignatoryDetails(List<DirectorsSignatoryDetail> directorsSignatoryDetails) {
        DirectorsSignatoryDetails = directorsSignatoryDetails;
    }

    @SerializedName("directors/signatory_details")
    List<DirectorsSignatoryDetail> DirectorsSignatoryDetails;

    public DataPojo(com.company.companyadda.CompanyDetailsPojo.company_master_data company_master_data, List<Object> charges, List<DirectorsSignatoryDetail> directorsSignatoryDetails) {
        this.company_master_data = company_master_data;
        this.charges = charges;
        DirectorsSignatoryDetails = directorsSignatoryDetails;
    }



}
