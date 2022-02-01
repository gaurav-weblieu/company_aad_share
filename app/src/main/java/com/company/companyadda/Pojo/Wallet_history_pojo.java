package com.company.companyadda.Pojo;

public class Wallet_history_pojo {

    public String referal_code_id;
    public String user_id;
    public String referal_code;
    public String amount;

    public String getReferal_code_id() {
        return referal_code_id;
    }

    public void setReferal_code_id(String referal_code_id) {
        this.referal_code_id = referal_code_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReferal_code() {
        return referal_code;
    }

    public void setReferal_code(String referal_code) {
        this.referal_code = referal_code;
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

    public Wallet_history_pojo(String referal_code_id, String user_id, String referal_code, String amount, String created_at) {
        this.referal_code_id = referal_code_id;
        this.user_id = user_id;
        this.referal_code = referal_code;
        this.amount = amount;
        this.created_at = created_at;
    }




}
