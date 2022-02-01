package com.company.companyadda.Pojo;

public class PaymentsKeys {
    public String razorpay_key_id;
    public String contact_us_email;

    public String getContact_us_email() {
        return contact_us_email;
    }

    public void setContact_us_email(String contact_us_email) {
        this.contact_us_email = contact_us_email;
    }

    public String getContact_us_mobile() {
        return contact_us_mobile;
    }

    public void setContact_us_mobile(String contact_us_mobile) {
        this.contact_us_mobile = contact_us_mobile;
    }

    public String contact_us_mobile;

    public String getRazorpay_key_id() {
        return razorpay_key_id;
    }

    public void setRazorpay_key_id(String razorpay_key_id) {
        this.razorpay_key_id = razorpay_key_id;
    }

    public String getRazorpay_key_secret() {
        return razorpay_key_secret;
    }

    public void setRazorpay_key_secret(String razorpay_key_secret) {
        this.razorpay_key_secret = razorpay_key_secret;
    }

    public String razorpay_key_secret;


    public PaymentsKeys(String razorpay_key_id, String razorpay_key_secret,String contact_us_email,String contact_us_mobile) {
        this.razorpay_key_id = razorpay_key_id;
        this.razorpay_key_secret = razorpay_key_secret;
        this.contact_us_email = contact_us_email;
        this.contact_us_mobile = contact_us_mobile;
    }

}
