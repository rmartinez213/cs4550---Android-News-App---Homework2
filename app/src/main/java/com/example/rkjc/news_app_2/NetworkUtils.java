package com.example.rkjc.news_app_2;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class NetworkUtils{

    //Base API Endpoint
    final static String base_url = "https://newsapi.org/v1/articles";

    //Query1 (Source)
    final static String PARAM_QUERY = "source";
    final static String source = "the-next-web";


    //Query2 (Sort)
    final static String PARAM_SORT = "sortBy";
    final static String sort = "latest";

    //Query3 (API Key)
    final static String PARAM_APIKEY = "apiKey";
    final static String key = "b56e8c10476548f5811e3b3872f8b145";


    //Builds the URL for calling the API
    public static URL buildURL(){
        Uri builtUri = Uri.parse(base_url).buildUpon()
                .appendQueryParameter(PARAM_QUERY, source)
                .appendQueryParameter(PARAM_SORT, sort)
                .appendQueryParameter(PARAM_APIKEY, key)
                .build();

        URL url = null;

        try{
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        //System.out.println(url.toString());
        Log.d("TAG", url.toString());


        return url;
    }


    //Connects by opening a connection with the url and returns the text
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try{
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if(hasInput)
                return scanner.next();

            else
                return null;
        } finally {
            urlConnection.disconnect();
        }
    }
}

