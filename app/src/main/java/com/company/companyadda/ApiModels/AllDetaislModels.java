package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.AllLoginPojo;
import com.company.companyadda.Pojo.BannerPojo;
import com.company.companyadda.Pojo.CapitalResult;
import com.company.companyadda.Pojo.LoginPojo;
import com.company.companyadda.Pojo.PagesDetail;
import com.company.companyadda.Pojo.PaymentsKeys;

import java.util.List;

public class AllDetaislModels {

    List<AllLoginPojo> Login_Details;
    List<BannerPojo> Banner_Details;

    public List<AllLoginPojo> getLogin_Details() {
        return Login_Details;
    }

    public void setLogin_Details(List<AllLoginPojo> login_Details) {
        Login_Details = login_Details;
    }

    public List<BannerPojo> getBanner_Details() {
        return Banner_Details;
    }

    public void setBanner_Details(List<BannerPojo> banner_Details) {
        Banner_Details = banner_Details;
    }

    public List<PagesDetail> getPages_Details() {
        return Pages_Details;
    }

    public void setPages_Details(List<PagesDetail> pages_Details) {
        Pages_Details = pages_Details;
    }

    public PaymentsKeys getPayments_Keys() {
        return Payments_Keys;
    }

    public void setPayments_Keys(PaymentsKeys payments_Keys) {
        Payments_Keys = payments_Keys;
    }

    public CapitalResult getResult() {
        return result;
    }

    public void setResult(CapitalResult result) {
        this.result = result;
    }

    List<PagesDetail> Pages_Details;
    PaymentsKeys Payments_Keys;
    CapitalResult result;

    public AllDetaislModels(List<AllLoginPojo> login_Details, List<BannerPojo> banner_Details, List<PagesDetail> pages_Details, PaymentsKeys payments_Keys, CapitalResult result) {
        Login_Details = login_Details;
        Banner_Details = banner_Details;
        Pages_Details = pages_Details;
        Payments_Keys = payments_Keys;
        this.result = result;
    }



}
