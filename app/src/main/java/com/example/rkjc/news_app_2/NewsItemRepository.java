package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewsItemRepository {

    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mAllItems;
    private Application application;


    //Constructor
    public NewsItemRepository(Application application){
        NewsItemDatabase db = NewsItemDatabase.getDatabase(application.getApplicationContext());
        this.application = application;
        mNewsItemDao = db.newsItemDao();
        mAllItems = mNewsItemDao.getAllItems();
    }


    // Calls upon actions performed on database
    public LiveData<List<NewsItem>> getmAllItems(){
        new getAllAsyncTask(mNewsItemDao,this).execute();
        return mAllItems;
    }

    public List<NewsItem> syncDataBase(){
        try{
            return new syncDatabase(mNewsItemDao, this, this.application).execute().get();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }


    public void setNewItems(LiveData<List<NewsItem>> items){
        this.mAllItems = items;
    }


    public static class getAllAsyncTask extends AsyncTask<Void, Void, LiveData<List<NewsItem>>>{
        private NewsItemDao newsItemDao;
        private NewsItemRepository newsItemRepository;
        private Application application;

        getAllAsyncTask(NewsItemDao newsItemDao, NewsItemRepository newsItemRepository){
            this.newsItemDao = newsItemDao;
            this.newsItemRepository = newsItemRepository;
        }

        @Override
        protected LiveData<List<NewsItem>> doInBackground(Void... avoid){
            return this.newsItemDao.getAllItems();
        }

        @Override
        protected void onPostExecute(LiveData<List<NewsItem>> listLiveData){
            super.onPostExecute(listLiveData);
            this.newsItemRepository.setNewItems(listLiveData);
        }
    }

    public static class syncDatabase extends AsyncTask<Void, Void, List<NewsItem>>{
        private NewsItemDao newsItemDao;
        private NewsItemRepository newsItemRepository;
        private Application application;

        syncDatabase(NewsItemDao newsItemDao, NewsItemRepository newsItemRepository, Application application){
            this.application = application;
            this.newsItemDao = newsItemDao;
            this.newsItemRepository = newsItemRepository;
        }

        @Override
        protected List<NewsItem> doInBackground(Void...voids){
            String jsonString = null;
            List<NewsItem> items = null;

            try{
                jsonString = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
                items = JsonUtils.parseNews(jsonString);
                newsItemDao.deleteAllItems();
                newsItemDao.insert(items);
            } catch (IOException e){
                e.printStackTrace();
            }
            return items;
        }
    }
}


