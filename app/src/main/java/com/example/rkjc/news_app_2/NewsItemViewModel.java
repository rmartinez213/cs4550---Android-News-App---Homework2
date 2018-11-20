package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {

    private  NewsItemRepository repository;
    private LiveData<List<NewsItem>> allNewsItems;



    public NewsItemViewModel(@NonNull Application application) {
        super(application);
        repository =  new NewsItemRepository(application);
        allNewsItems = repository.getmAllItems();
    }

    public void insert(NewsItem newsItem){
        repository.insert(newsItem);
    }

    public void deleteAllNewsItems(){
        repository.deleteAllItems();
    }

    public LiveData<List<NewsItem>> getAllNewsItems(){
        return allNewsItems;
    }
}
