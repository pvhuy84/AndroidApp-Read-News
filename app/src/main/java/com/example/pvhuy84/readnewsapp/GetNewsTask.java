package com.example.pvhuy84.readnewsapp;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by pvhuy84 on 1/6/2017.
 */

public class GetNewsTask extends AsyncTask<String, Void, ResultForRequestNews> {
    @Override
    protected ResultForRequestNews doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            OkHttpClient client = new OkHttpClient();

            // Request to webservice and get result with response is json string value
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            String jsonResult = response.body().string();

            ResultForRequestNews result;
            Gson gson = new Gson();

            // Cover json to ResultForRequestNews class
            result = gson.fromJson(jsonResult, ResultForRequestNews.class);

            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
