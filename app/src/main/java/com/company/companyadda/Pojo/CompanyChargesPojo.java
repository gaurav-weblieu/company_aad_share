package com.company.companyadda.Pojo;

public class CompanyChargesPojo {
    public String ID;
    public String CIN;
    public String SLNO;
    public String CHARGE_ID;
    public String SRN;
    public String CHARGE_HOLDER;
    public String DATE_CREATE;
    public String DATE_MODIFIED;
    public String DATE_SATISFIED;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getSLNO() {
        return SLNO;
    }

    public void setSLNO(String SLNO) {
        this.SLNO = SLNO;
    }

    public String getCHARGE_ID() {
        return CHARGE_ID;
    }

    public void setCHARGE_ID(String CHARGE_ID) {
        this.CHARGE_ID = CHARGE_ID;
    }

    public String getSRN() {
        return SRN;
    }

    public void setSRN(String SRN) {
        this.SRN = SRN;
    }

    public String getCHARGE_HOLDER() {
        return CHARGE_HOLDER;
    }

    public void setCHARGE_HOLDER(String CHARGE_HOLDER) {
        this.CHARGE_HOLDER = CHARGE_HOLDER;
    }

    public String getDATE_CREATE() {
        return DATE_CREATE;
    }

    public void setDATE_CREATE(String DATE_CREATE) {
        this.DATE_CREATE = DATE_CREATE;
    }

    public String getDATE_MODIFIED() {
        return DATE_MODIFIED;
    }

    public void setDATE_MODIFIED(String DATE_MODIFIED) {
        this.DATE_MODIFIED = DATE_MODIFIED;
    }

    public String getDATE_SATISFIED() {
        return DATE_SATISFIED;
    }

    public void setDATE_SATISFIED(String DATE_SATISFIED) {
        this.DATE_SATISFIED = DATE_SATISFIED;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getHASH() {
        return HASH;
    }

    public void setHASH(String HASH) {
        this.HASH = HASH;
    }

    public String getTIMESTAMP() {
        return TIMESTAMP;
    }

    public void setTIMESTAMP(String TIMESTAMP) {
        this.TIMESTAMP = TIMESTAMP;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String AMOUNT;
    public String ADDRESS;
    public String HASH;
    public String TIMESTAMP;
    public String STATUS;

    public CompanyChargesPojo(String ID, String CIN, String SLNO, String CHARGE_ID, String SRN, String CHARGE_HOLDER, String DATE_CREATE, String DATE_MODIFIED, String DATE_SATISFIED, String AMOUNT, String ADDRESS, String HASH, String TIMESTAMP, String STATUS) {
        this.ID = ID;
        this.CIN = CIN;
        this.SLNO = SLNO;
        this.CHARGE_ID = CHARGE_ID;
        this.SRN = SRN;
        this.CHARGE_HOLDER = CHARGE_HOLDER;
        this.DATE_CREATE = DATE_CREATE;
        this.DATE_MODIFIED = DATE_MODIFIED;
        this.DATE_SATISFIED = DATE_SATISFIED;
        this.AMOUNT = AMOUNT;
        this.ADDRESS = ADDRESS;
        this.HASH = HASH;
        this.TIMESTAMP = TIMESTAMP;
        this.STATUS = STATUS;
    }


}
