package com.company.companyadda.Pojo;

public class PagesDetail {
    public PagesDetail(String id, String timestamp, String page_name, String content) {
        this.id = id;
        this.timestamp = timestamp;
        this.page_name = page_name;
        this.content = content;
    }

    public String id;
    public String timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String page_name;
    public String content;
}
