package com.example.pvhuy84.readnewsapp;

import java.util.ArrayList;

/**
 * Created by pvhuy84 on 1/6/2017.
 */

public class ResultForRequestNews {
    private String status;
    private String source;
    private String sortBy;
    private ArrayList<News> articles;

    public ResultForRequestNews() {
    }

    public ResultForRequestNews(String status, String source, String sortBy, ArrayList<News> articles) {
        this.status = status;
        this.source = source;
        this.sortBy = sortBy;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public ArrayList<News> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<News> articles) {
        this.articles = articles;
    }
}
