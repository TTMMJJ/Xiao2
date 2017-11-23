package com.bwie.bean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by T_baby on 17/11/22.
 */

public interface ApiService {
    @GET("iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=vedio")
    Observable<Movie> getvideo();
    @GET
    Observable<List<News>> getnew(@Url String url);
}
