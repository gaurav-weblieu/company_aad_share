package com.company.companyadda.Pojo;

public class SearchServicePoJo {

    String category_id;
    String category_name;

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

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    String create_at;

    public SearchServicePoJo(String category_id, String category_name, String create_at) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.create_at = create_at;
    }



}
