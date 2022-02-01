package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.CapitalResult;
import com.company.companyadda.Pojo.SearchServicePoJo;
import com.company.companyadda.Pojo.SearchServicePoJoService;

import java.util.List;

public class SearchServiceApiModel {

    List<SearchServicePoJo> category;
    List<SearchServicePoJoService> START_REGISTRATION;
    List<SearchServicePoJoService> LICENSE;
    List<SearchServicePoJoService> TAX;
    List<SearchServicePoJoService> GST;
    List<SearchServicePoJoService> ROC_FILING;
    List<SearchServicePoJoService> LLP;

    public List<SearchServicePoJo> getCategory() {
        return category;
    }

    public void setCategory(List<SearchServicePoJo> category) {
        this.category = category;
    }

    public List<SearchServicePoJoService> getSTART_REGISTRATION() {
        return START_REGISTRATION;
    }

    public void setSTART_REGISTRATION(List<SearchServicePoJoService> START_REGISTRATION) {
        this.START_REGISTRATION = START_REGISTRATION;
    }

    public List<SearchServicePoJoService> getLICENSE() {
        return LICENSE;
    }

    public void setLICENSE(List<SearchServicePoJoService> LICENSE) {
        this.LICENSE = LICENSE;
    }

    public List<SearchServicePoJoService> getTAX() {
        return TAX;
    }

    public void setTAX(List<SearchServicePoJoService> TAX) {
        this.TAX = TAX;
    }

    public List<SearchServicePoJoService> getGST() {
        return GST;
    }

    public void setGST(List<SearchServicePoJoService> GST) {
        this.GST = GST;
    }

    public List<SearchServicePoJoService> getROC_FILING() {
        return ROC_FILING;
    }

    public void setROC_FILING(List<SearchServicePoJoService> ROC_FILING) {
        this.ROC_FILING = ROC_FILING;
    }

    public List<SearchServicePoJoService> getLLP() {
        return LLP;
    }

    public void setLLP(List<SearchServicePoJoService> LLP) {
        this.LLP = LLP;
    }

    public List<SearchServicePoJoService> getINVESTMENT_TAX_PLANNING() {
        return INVESTMENT_TAX_PLANNING;
    }

    public void setINVESTMENT_TAX_PLANNING(List<SearchServicePoJoService> INVESTMENT_TAX_PLANNING) {
        this.INVESTMENT_TAX_PLANNING = INVESTMENT_TAX_PLANNING;
    }

    public CapitalResult getResult() {
        return result;
    }

    public void setResult(CapitalResult result) {
        this.result = result;
    }

    List<SearchServicePoJoService> INVESTMENT_TAX_PLANNING;
    CapitalResult result;

    public SearchServiceApiModel(List<SearchServicePoJo> category, List<SearchServicePoJoService> START_REGISTRATION, List<SearchServicePoJoService> LICENSE, List<SearchServicePoJoService> TAX, List<SearchServicePoJoService> GST, List<SearchServicePoJoService> ROC_FILING, List<SearchServicePoJoService> LLP, List<SearchServicePoJoService> INVESTMENT_TAX_PLANNING, CapitalResult result) {
        this.category = category;
        this.START_REGISTRATION = START_REGISTRATION;
        this.LICENSE = LICENSE;
        this.TAX = TAX;
        this.GST = GST;
        this.ROC_FILING = ROC_FILING;
        this.LLP = LLP;
        this.INVESTMENT_TAX_PLANNING = INVESTMENT_TAX_PLANNING;
        this.result = result;
    }





}
