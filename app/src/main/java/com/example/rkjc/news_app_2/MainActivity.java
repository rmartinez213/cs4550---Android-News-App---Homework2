package com.example.rkjc.news_app_2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    //New Code Homework2
    private NewsItemViewModel newsItemViewModel;

    //Hungarian Notation m = Main (It's in the main class)
    private EditText mSearchBoxEditText;
    private TextView mUrlDisplayTextView;
    private TextView mSearchResultsTextView;
    private ProgressBar mProgressBar;
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mAdapter;
    private List<NewsItem> newsItems = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchResultsTextView = (TextView) findViewById(R.id.date);
        mRecyclerView = (RecyclerView)findViewById(R.id.news_recyclerview);
        mAdapter = new NewsRecyclerViewAdapter(this, newsItems);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //New Code Homework2
        newsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);
        newsItemViewModel.getAllNewsItems().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItems) {
                mAdapter.setNewsItems(newsItems);
            }
        });
    }


    public class QueryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL...urls){
            String articleText = "";

            try{
                articleText = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            } catch (IOException e){
                e.printStackTrace();
            }

            return articleText;
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            newsItems = JsonUtils.parseNews(s);
            mAdapter.mNewsItems.addAll(newsItems); //Add all values into adapter
            mAdapter.notifyDataSetChanged(); //Display *live* changes
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("NewsResults", "Test");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){

            case R.id.action_search:
                newsItems = this.newsItemViewModel.sync();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
