package com.example.rkjc.news_app_2;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NewsItemDao {

    //Inserts data
    @Insert
    void insert(List<NewsItem> item);

    //Clears all data
    @Query("DELETE FROM news_item")
    void deleteAllItems();

    //Loads all data
    @Query("SELECT * FROM news_item ORDER BY id ASC")
    LiveData<List<NewsItem>> getAllItems();

}
