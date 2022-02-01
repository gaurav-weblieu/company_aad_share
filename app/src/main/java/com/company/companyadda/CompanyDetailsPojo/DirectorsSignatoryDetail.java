package com.company.companyadda.CompanyDetailsPojo;

import com.google.gson.annotations.SerializedName;

public class DirectorsSignatoryDetail {

    public String end_date;
    public String surrendered_din;

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getSurrendered_din() {
        return surrendered_din;
    }

    public void setSurrendered_din(String surrendered_din) {
        this.surrendered_din = surrendered_din;
    }

    public String getDinPan() {
        return dinPan;
    }

    public void setDinPan(String dinPan) {
        this.dinPan = dinPan;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("din/pan")
    public String dinPan;
    public String begin_date;
    public String name;


    public DirectorsSignatoryDetail(String end_date, String surrendered_din, String dinPan, String begin_date, String name) {
        this.end_date = end_date;
        this.surrendered_din = surrendered_din;
        this.dinPan = dinPan;
        this.begin_date = begin_date;
        this.name = name;
    }

}
