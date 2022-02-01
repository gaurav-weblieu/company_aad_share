package com.company.companyadda.Pojo;

public class MyRequestDetailsPojo {

    public String quote_id;
    public String user_id;
    public String user_name;
    public String user_email;
    public String user_mobile;
    public String service_name;

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(String quote_id) {
        this.quote_id = quote_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getUser_subject() {
        return user_subject;
    }

    public void setUser_subject(String user_subject) {
        this.user_subject = user_subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String service_id;
    public String user_subject;
    public String status;
    public String created_at;

    public MyRequestDetailsPojo(String quote_id, String user_id, String user_name, String user_email,
                                String user_mobile, String service_id, String user_subject, String status,
                                String created_at,String service_name) {
        this.quote_id = quote_id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_mobile = user_mobile;
        this.service_id = service_id;
        this.user_subject = user_subject;
        this.status = status;
        this.created_at = created_at;
        this.service_name = service_name;
    }


}
