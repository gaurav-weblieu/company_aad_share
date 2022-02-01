package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.CapitalResult;
import com.company.companyadda.Pojo.WalletDetails;
import com.company.companyadda.Pojo.Wallet_history_pojo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WalletListModel {


    @SerializedName("Wallet_details")
    List<WalletDetails> Wallet_details;

    public List<WalletDetails> getWallet_details() {
        return Wallet_details;
    }

    public void setWallet_details(List<WalletDetails> wallet_details) {
        Wallet_details = wallet_details;
    }

    public List<Wallet_history_pojo> getWallet_list() {
        return Wallet_list;
    }

    public void setWallet_list(List<Wallet_history_pojo> wallet_list) {
        Wallet_list = wallet_list;
    }

    public CapitalResult getResult() {
        return result;
    }

    public void setResult(CapitalResult result) {
        this.result = result;
    }

    @SerializedName("Wallet_list")
    List<Wallet_history_pojo> Wallet_list;

    @SerializedName("result")
    CapitalResult result;


    public WalletListModel(List<WalletDetails> wallet_details, List<Wallet_history_pojo> wallet_list, CapitalResult result) {
        Wallet_details = wallet_details;
        Wallet_list = wallet_list;
        this.result = result;
    }






}
