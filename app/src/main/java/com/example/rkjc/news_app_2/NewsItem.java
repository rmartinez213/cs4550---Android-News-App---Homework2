package com.example.rkjc.news_app_2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;


@Entity(tableName = "news_item")
public class NewsItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Author")
    public String mAuthor;

    @ColumnInfo(name = "Title")
    public String mTitle;

    @ColumnInfo(name = "Description")
    public String mDescription;

    @ColumnInfo(name = "Url")
    public String mUrl;

    @ColumnInfo(name = "Url_To_Image")
    public String mUrlToImage;

    @ColumnInfo(name = "Published_Date")
    public String mPublishedAt;


    public NewsItem(String mAuthor, String mTitle, String mDescription, String mUrl, String mUrlToImage, String mPublishedAt) {
        this.mAuthor = mAuthor;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mUrl = mUrl;
        this.mUrlToImage = mUrlToImage;
        this.mPublishedAt = mPublishedAt;
    }

    @Ignore
    public NewsItem() {

    }


    public int getId() {
        return id;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmPublishedAt() {
        return mPublishedAt;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmUrlToImage() {
        return mUrlToImage;
    }

    public void setId(int id) {
        this.id = id;
    }
}

