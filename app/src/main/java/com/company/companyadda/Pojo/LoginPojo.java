package com.company.companyadda.Pojo;

public class LoginPojo {

    public String MESSAGE;
    public String STATUS;
    public String user_id;
    public String email;
    public String referal_code;

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferal_code() {
        return referal_code;
    }

    public void setReferal_code(String referal_code) {
        this.referal_code = referal_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String phone;
    public String name;
    public String code;
    public String token;

    public LoginPojo(String MESSAGE, String STATUS, String user_id, String email, String referal_code, String phone, String name, String code, String token) {
        this.MESSAGE = MESSAGE;
        this.STATUS = STATUS;
        this.user_id = user_id;
        this.email = email;
        this.referal_code = referal_code;
        this.phone = phone;
        this.name = name;
        this.code = code;
        this.token = token;
    }




}
