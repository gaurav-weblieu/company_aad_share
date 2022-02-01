package com.company.companyadda.Pojo;

public class DirRelatedPojo {
    String CIN;
    String DATE_JOIN;
    String DATE_RESIGN;
    String DESIGNATION;

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getDATE_JOIN() {
        return DATE_JOIN;
    }

    public void setDATE_JOIN(String DATE_JOIN) {
        this.DATE_JOIN = DATE_JOIN;
    }

    public String getDATE_RESIGN() {
        return DATE_RESIGN;
    }

    public void setDATE_RESIGN(String DATE_RESIGN) {
        this.DATE_RESIGN = DATE_RESIGN;
    }

    public String getDESIGNATION() {
        return DESIGNATION;
    }

    public void setDESIGNATION(String DESIGNATION) {
        this.DESIGNATION = DESIGNATION;
    }

    public String getCOMPANY_NAME() {
        return COMPANY_NAME;
    }

    public void setCOMPANY_NAME(String COMPANY_NAME) {
        this.COMPANY_NAME = COMPANY_NAME;
    }

    public String getCOMPANY_STATUS() {
        return COMPANY_STATUS;
    }

    public void setCOMPANY_STATUS(String COMPANY_STATUS) {
        this.COMPANY_STATUS = COMPANY_STATUS;
    }

    public String getPAIDUP_CAPITAL() {
        return PAIDUP_CAPITAL;
    }

    public void setPAIDUP_CAPITAL(String PAIDUP_CAPITAL) {
        this.PAIDUP_CAPITAL = PAIDUP_CAPITAL;
    }

    String COMPANY_NAME;
    String COMPANY_STATUS;
    String PAIDUP_CAPITAL;

    public DirRelatedPojo(String CIN, String DATE_JOIN, String DATE_RESIGN, String DESIGNATION, String COMPANY_NAME, String COMPANY_STATUS, String PAIDUP_CAPITAL) {
        this.CIN = CIN;
        this.DATE_JOIN = DATE_JOIN;
        this.DATE_RESIGN = DATE_RESIGN;
        this.DESIGNATION = DESIGNATION;
        this.COMPANY_NAME = COMPANY_NAME;
        this.COMPANY_STATUS = COMPANY_STATUS;
        this.PAIDUP_CAPITAL = PAIDUP_CAPITAL;
    }



}
