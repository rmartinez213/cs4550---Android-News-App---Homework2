package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;

public class NewsItemRepository {

    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mAllItems;


    //Constructor
    public NewsItemRepository(Application application){
        NewsItemDatabase db = NewsItemDatabase.getDatabase(application);
        mNewsItemDao = db.newsItemDao();
        mAllItems = mNewsItemDao.getAllItems();
    }



    //Actions performed on database
    LiveData<List<NewsItem>> getmAllItems(){
        return mAllItems;
    }

    public void insert(NewsItem item){
        new insertNewsItemAsyncTask(mNewsItemDao).execute(item);
    }

    public void deleteAllItems(){
        new deleteAllNewsItemAsyncTask(mNewsItemDao).execute();
    }


    //AsyncTask that inserts NewsItems
    private static class insertNewsItemAsyncTask extends AsyncTask<NewsItem, Void, Void> {

        private NewsItemDao newsItemDao; //Makes data base operations

        private insertNewsItemAsyncTask(NewsItemDao newsItemDao){
            this.newsItemDao = newsItemDao;
        }


        @Override
        protected Void doInBackground(NewsItem... newsItems) {
            newsItemDao.insert(newsItems[0]);
            return null;
        }
    }

    //AsyncTask that deletes all AsyncTask
    private static class deleteAllNewsItemAsyncTask extends AsyncTask<Void, Void, Void> {

        private NewsItemDao newsItemDao; //Makes data base operations

        private deleteAllNewsItemAsyncTask(NewsItemDao newsItemDao){
            this.newsItemDao = newsItemDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            newsItemDao.deleteAllItems();
            return null;
        }
    }

}
