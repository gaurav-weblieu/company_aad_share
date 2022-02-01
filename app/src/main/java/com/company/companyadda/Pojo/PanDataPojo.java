package com.company.companyadda.Pojo;

public class PanDataPojo {

    String entity;
    String pan;
    String first_name;
    String last_name;
    String full_name;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAadhaar_seeding_status() {
        return aadhaar_seeding_status;
    }

    public void setAadhaar_seeding_status(String aadhaar_seeding_status) {
        this.aadhaar_seeding_status = aadhaar_seeding_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    String aadhaar_seeding_status;
    String status;
    String category;
    String last_updated;

    public PanDataPojo(String entity, String pan, String first_name, String last_name, String full_name, String aadhaar_seeding_status, String status, String category, String last_updated) {
        this.entity = entity;
        this.pan = pan;
        this.first_name = first_name;
        this.last_name = last_name;
        this.full_name = full_name;
        this.aadhaar_seeding_status = aadhaar_seeding_status;
        this.status = status;
        this.category = category;
        this.last_updated = last_updated;
    }







}
