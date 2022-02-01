package com.company.companyadda.ApiModels;

import com.company.companyadda.Pojo.ArticlesListPojo;

import java.util.List;

public class NewListModel {
    public NewListModel(String statusCode, List<ArticlesListPojo> articles) {
        this.statusCode = statusCode;
        this.articles = articles;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<ArticlesListPojo> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesListPojo> articles) {
        this.articles = articles;
    }

    String statusCode;
List<ArticlesListPojo> articles;
}
