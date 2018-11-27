package com.example.rkjc.news_app_2;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;

@Database(entities = {NewsItem.class}, version = 1)
public abstract class NewsItemDatabase extends RoomDatabase {


    private static volatile NewsItemDatabase INSTANCE;
    public abstract NewsItemDao newsItemDao();

    public static synchronized NewsItemDatabase getDatabase(Context context){
        if(INSTANCE == null){
            synchronized (NewsItemDatabase.class){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NewsItemDatabase.class, "newsitem_database").build();
            }
        }
        return INSTANCE;
    }

//    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            new PopulateDBAsyncTask(INSTANCE).execute();
//        }
//    };
//
//    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void>{
//
//        private NewsItemDao newsItemDao;
//        ArrayList<NewsItem> NewsItemArrayList;
//
//        private PopulateDBAsyncTask(NewsItemDatabase db){
//            newsItemDao = db.newsItemDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            newsItemDao.insert(new NewsItem("Gables Condas", "Twilight of the night", "A book about a lot of people and their twilight nightmares", "https://mURL", "https://mURLImage", "9/9/1994"));
//            newsItemDao.insert(new NewsItem("Ryen Steffar", "Above the crusade", "A crusade of unnatural beings", "https://mURL", "https://mURLImage", "1/21/1984"));
//            newsItemDao.insert(new NewsItem("Steven Pecker", "The blue oyster", "A story about a sad oyster who found food and enjoyed life to the fullest", "https://mURL", "https://mURLImage", "5/28/1996"));
//            return null;
//        }
//    }
}