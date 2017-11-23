package com.bwie.bean;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by T_baby on 17/11/22.
 */

public class Factory {
    public static Retrofit moviceretrofit = null;
    public static Retrofit newsretrofit = null;

    public Retrofit getmovice() {
        if (moviceretrofit == null) {
            //设置链接超时
            okhttp3.OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(5, TimeUnit.SECONDS);
            moviceretrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(API.BASEURL)
                    .build();
        }
        return moviceretrofit;
    }
    public Retrofit getInstance() {
        if (newsretrofit == null) {
            //设置链接超时
            okhttp3.OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(5, TimeUnit.SECONDS);
            newsretrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(API.NEWSURL)
                    .build();
        }
        return newsretrofit;
    }
}
