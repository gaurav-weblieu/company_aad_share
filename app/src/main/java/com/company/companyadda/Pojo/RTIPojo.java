package com.company.companyadda.Pojo;

public class RTIPojo {
    public String free_ITR_id;
    public String user_id;
    public String ITR_form;
    public String name;

    public String getFree_ITR_id() {
        return free_ITR_id;
    }

    public void setFree_ITR_id(String free_ITR_id) {
        this.free_ITR_id = free_ITR_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getITR_form() {
        return ITR_form;
    }

    public void setITR_form(String ITR_form) {
        this.ITR_form = ITR_form;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String mobile;
    public String email;
    public String created_at;


    public RTIPojo(String free_ITR_id, String user_id, String ITR_form, String name, String mobile, String email, String created_at) {
        this.free_ITR_id = free_ITR_id;
        this.user_id = user_id;
        this.ITR_form = ITR_form;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.created_at = created_at;
    }

}
