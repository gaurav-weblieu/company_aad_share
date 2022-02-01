package com.company.companyadda.Pojo;

public class ServiceListPojo {

   String category_id;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    String category_name;
    String service_name;
    String service_id;

    public ServiceListPojo(String category_id, String category_name, String service_name, String service_id) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.service_name = service_name;
        this.service_id = service_id;
    }



}
