package com.company.companyadda.Pojo;

public class SearchDirectorPojo {

    String DIN;
    String NAME;

    public String getDIN() {
        return DIN;
    }

    public void setDIN(String DIN) {
        this.DIN = DIN;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDESIGNATION() {
        return DESIGNATION;
    }

    public void setDESIGNATION(String DESIGNATION) {
        this.DESIGNATION = DESIGNATION;
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

    String DESIGNATION;
    String DATE_JOIN;
    String DATE_RESIGN;

    public SearchDirectorPojo(String DIN, String NAME, String DESIGNATION, String DATE_JOIN, String DATE_RESIGN) {
        this.DIN = DIN;
        this.NAME = NAME;
        this.DESIGNATION = DESIGNATION;
        this.DATE_JOIN = DATE_JOIN;
        this.DATE_RESIGN = DATE_RESIGN;
    }


}
