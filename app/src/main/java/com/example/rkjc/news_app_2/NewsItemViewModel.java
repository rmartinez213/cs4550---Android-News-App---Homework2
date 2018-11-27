package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {

    private  NewsItemRepository repository;
    private LiveData<List<NewsItem>> allNewsItems;



    public NewsItemViewModel(@NonNull Application application) {
        super(application);
        this.repository =  new NewsItemRepository(application);
        this.allNewsItems = repository.getmAllItems();
    }


    public LiveData<List<NewsItem>> getAllNewsItems(){
        return allNewsItems;
    }

    public List<NewsItem> sync(){
        Log.d("LOOOOOG", "In Sync");
        return this.repository.syncDataBase();
    }
}
