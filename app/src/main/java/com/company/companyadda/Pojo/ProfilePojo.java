package com.company.companyadda.Pojo;

public class ProfilePojo {

    public String user_id;
    public String name;
    public String mobile;
    public String otp;
    public String email;
    public String language;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getReferal_code() {
        return referal_code;
    }

    public void setReferal_code(String referal_code) {
        this.referal_code = referal_code;
    }

    public String getWallet_amount() {
        return wallet_amount;
    }

    public void setWallet_amount(String wallet_amount) {
        this.wallet_amount = wallet_amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String password;
    public String services;
    public String referal_code;
    public String wallet_amount;
    public String image;
    public String created_at;

    public ProfilePojo(String user_id, String name, String mobile, String otp, String email, String language, String password, String services, String referal_code, String wallet_amount, String image, String created_at) {
        this.user_id = user_id;
        this.name = name;
        this.mobile = mobile;
        this.otp = otp;
        this.email = email;
        this.language = language;
        this.password = password;
        this.services = services;
        this.referal_code = referal_code;
        this.wallet_amount = wallet_amount;
        this.image = image;
        this.created_at = created_at;
    }

}
