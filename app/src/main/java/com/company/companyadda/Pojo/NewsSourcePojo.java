package com.company.companyadda.Pojo;

public class NewsSourcePojo {
    String title;
    String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    String favicon;


    public NewsSourcePojo(String title, String url, String favicon) {
        this.title = title;
        this.url = url;
        this.favicon = favicon;
    }

}
