package com.company.companyadda.Pojo;

public class Search_all_cat_type {
    public String category_id;
    public String service_id;

    public Search_all_cat_type(String category_id, String service_id, String service_name) {
        this.category_id = category_id;
        this.service_id = service_id;
        this.service_name = service_name;
    }

    public String service_name;


    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

}
