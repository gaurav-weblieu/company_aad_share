package com.company.companyadda.Pojo;

public class PaymentPojo {

    public String payment_id;
    public String user_id;
    public String payment_via;
    public String razorpay_payment_id;
    public String razorpay_order_id;
    public String razorpay_signature;
    public String Status;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String note;
    public String amount;

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPayment_via() {
        return payment_via;
    }

    public void setPayment_via(String payment_via) {
        this.payment_via = payment_via;
    }

    public String getRazorpay_payment_id() {
        return razorpay_payment_id;
    }

    public void setRazorpay_payment_id(String razorpay_payment_id) {
        this.razorpay_payment_id = razorpay_payment_id;
    }

    public String getRazorpay_order_id() {
        return razorpay_order_id;
    }

    public void setRazorpay_order_id(String razorpay_order_id) {
        this.razorpay_order_id = razorpay_order_id;
    }

    public String getRazorpay_signature() {
        return razorpay_signature;
    }

    public void setRazorpay_signature(String razorpay_signature) {
        this.razorpay_signature = razorpay_signature;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String created_at;

    public PaymentPojo(String payment_id, String user_id, String payment_via, String razorpay_payment_id, String razorpay_order_id, String razorpay_signature, String status, String amount,String note, String created_at) {
        this.payment_id = payment_id;
        this.user_id = user_id;
        this.payment_via = payment_via;
        this.razorpay_payment_id = razorpay_payment_id;
        this.razorpay_order_id = razorpay_order_id;
        this.razorpay_signature = razorpay_signature;
        this.Status = status;
        this.note = note;
        this.amount = amount;
        this.created_at = created_at;
    }







}
