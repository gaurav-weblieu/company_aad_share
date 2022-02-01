package com.company.companyadda.Pojo;

public class ArticlesListPojo {
    String published_date;
    String title;
    String link;
    NewsSourcePojo source;

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public NewsSourcePojo getSource() {
        return source;
    }

    public void setSource(NewsSourcePojo source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    String description;
    String thumbnail;

    public ArticlesListPojo(String published_date, String title, String link, NewsSourcePojo source, String description, String thumbnail) {
        this.published_date = published_date;
        this.title = title;
        this.link = link;
        this.source = source;
        this.description = description;
        this.thumbnail = thumbnail;
    }


}
