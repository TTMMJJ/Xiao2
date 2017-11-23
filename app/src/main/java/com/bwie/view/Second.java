package com.bwie.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bwie.bean.API;
import com.bwie.bean.ApiService;
import com.bwie.bean.GlidImageLoader;
import com.bwie.bean.Myadapter2;
import com.bwie.bean.R;
import com.bwie.bean.Movie;
import com.youth.banner.Banner;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Second extends AppCompatActivity {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.banner)
    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        recycleview.setLayoutManager(new LinearLayoutManager(Second.this));
        int pag = getIntent().getIntExtra("position", 1);
        text.setText(pag + "");
        getdate();
    }

    //请求数据
    public void getdate() {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        ApiService retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(API.BASEURL)
                .client(httpClient)
                .build()
                .create(ApiService.class);
        Observable<Movie> ob = retrofit.getvideo();
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Movie movie) {
                        ArrayList<String> imgs = new ArrayList<>();
                        final List<Movie.DataBean> list = movie.getData();
                        for (Movie.DataBean bean : list) {
                            imgs.add(bean.getImage_url());
                        }
                        banner.setImageLoader(new GlidImageLoader());
                        banner.setImages(imgs);
                        banner.start();
                        Myadapter2 myadapter = new Myadapter2(list);
                        recycleview.setAdapter(myadapter);
                        myadapter.setSetOnclickListion(new Myadapter2.Onclicklistion() {
                            @Override
                            public void ClickListion(View v, int positin) {
                                String url = list.get(positin).getVedio_url();
                                Intent intent = new Intent(Second.this, Three.class);
                                intent.putExtra("path", url);
                                startActivity(intent);
                            }
                        });
                    }
                });

    }
}
