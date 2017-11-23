package com.bwie.model;

import android.util.Log;

import com.bwie.bean.API;
import com.bwie.bean.ApiService;
import com.bwie.bean.Factory;
import com.bwie.bean.News;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by T_baby on 17/11/22.
 */

public class Model implements IModel {
    private Setnewsdate setnewsdate;

    public Model(Setnewsdate setnewsdate) {
        this.setnewsdate = setnewsdate;
    }

    @Override
    public void getnews(String url) {
        Retrofit retrofit = new Factory().getInstance();
        ApiService service = retrofit.create(ApiService.class);
        service.getnew(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<News>>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(List<News> news) {
                        setnewsdate.Setdate(news.get(0).getData());
                    }
                });
    }

    public interface Setnewsdate {
        void Setdate(List<News.DataBean> list);
    }
}
