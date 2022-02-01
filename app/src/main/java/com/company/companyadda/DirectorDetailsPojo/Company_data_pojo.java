package com.company.companyadda.DirectorDetailsPojo;

import com.google.gson.annotations.SerializedName;

public class Company_data_pojo {
    String end_date;
    String active_compliance;
    String company_name;

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getActive_compliance() {
        return active_compliance;
    }

    public void setActive_compliance(String active_compliance) {
        this.active_compliance = active_compliance;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getCinFcrn() {
        return CinFcrn;
    }

    public void setCinFcrn(String cinFcrn) {
        CinFcrn = cinFcrn;
    }

    String begin_date;
    @SerializedName("cin/fcrn")
    String CinFcrn;

    public Company_data_pojo(String end_date, String active_compliance, String company_name, String begin_date, String cinFcrn) {
        this.end_date = end_date;
        this.active_compliance = active_compliance;
        this.company_name = company_name;
        this.begin_date = begin_date;
        CinFcrn = cinFcrn;
    }


}
