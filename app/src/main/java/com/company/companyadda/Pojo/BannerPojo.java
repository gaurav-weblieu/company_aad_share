package com.company.companyadda.Pojo;

public class BannerPojo {
    public String banner_id;
    public String banner_name;

    public String getBanner_id() {
        return banner_id;
    }

    public String getBanner_name() {
        return banner_name;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String banner_image;
    public String created_at;

    public BannerPojo(String banner_id, String banner_name, String banner_image, String created_at) {
        this.banner_id = banner_id;
        this.banner_name = banner_name;
        this.banner_image = banner_image;
        this.created_at = created_at;
    }


}
