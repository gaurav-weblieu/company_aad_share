package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.CapitalResult;
import com.company.companyadda.Pojo.ProfilePojo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileModel {

    @SerializedName("result")
    private CapitalResult result;

    public CapitalResult getResult() {
        return result;
    }

    public void setResult(CapitalResult result) {
        this.result = result;
    }

    public List<ProfilePojo> getProfile() {
        return Profile;
    }

    public void setProfile(List<ProfilePojo> profile) {
        Profile = profile;
    }

    @SerializedName("Profile")
    private  List<ProfilePojo> Profile;

    public ProfileModel(CapitalResult result, List<ProfilePojo> profile) {
        this.result = result;
        Profile = profile;
    }







}
