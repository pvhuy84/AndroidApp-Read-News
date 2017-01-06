package com.example.pvhuy84.readnewsapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by pvhuy84 on 1/6/2017.
 */

public class ResultForRequestNews implements Parcelable {
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

    protected ResultForRequestNews(Parcel in) {
        status = in.readString();
        source = in.readString();
        sortBy = in.readString();
        articles = in.createTypedArrayList(News.CREATOR);
    }

    public static final Creator<ResultForRequestNews> CREATOR = new Creator<ResultForRequestNews>() {
        @Override
        public ResultForRequestNews createFromParcel(Parcel in) {
            return new ResultForRequestNews(in);
        }

        @Override
        public ResultForRequestNews[] newArray(int size) {
            return new ResultForRequestNews[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(status);
        parcel.writeString(source);
        parcel.writeString(sortBy);
        parcel.writeTypedList(articles);
    }

    @Override
    public String toString() {
        return "Size of articles: " + articles.size();
    }
}
