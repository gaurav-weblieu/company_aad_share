package com.company.companyadda.Pojo;

public class DocPojo {

    public String user_document_id;
    public String user_id;

    public String getUser_document_id() {
        return user_document_id;
    }

    public void setUser_document_id(String user_document_id) {
        this.user_document_id = user_document_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }

    public String getDocument_image() {
        return document_image;
    }

    public void setDocument_image(String document_image) {
        this.document_image = document_image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String document_name;
    public String document_image;
    public String created_at;


    public DocPojo(String user_document_id, String user_id, String document_name, String document_image, String created_at) {
        this.user_document_id = user_document_id;
        this.user_id = user_id;
        this.document_name = document_name;
        this.document_image = document_image;
        this.created_at = created_at;
    }

}
