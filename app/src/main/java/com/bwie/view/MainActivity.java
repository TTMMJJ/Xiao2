package com.bwie.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bwie.bean.Myadapter;
import com.bwie.bean.News;
import com.bwie.bean.R;
import com.bwie.presenter.Presenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView {
    int pag = 1, count = 0;
    @BindView(R.id.xrecycleview)
    XRecyclerView xrecycleview;
    List<News.DataBean> arr = new ArrayList();
    Myadapter adapter;
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        xrecycleview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        presenter = new Presenter(this);
        presenter.getdate("page_" + pag + ".json");
        xrecycleview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pag = 1;
                arr.clear();
                presenter.getdate("page_" + pag + ".json");
                xrecycleview.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                pag = (count + 1) % 3 + 1;
                presenter.getdate("page_" + pag + ".json");
                xrecycleview.loadMoreComplete();
            }
        });
    }
    @Override
    public void getnews(List<News.DataBean> list) {
        if (list.size() != 0) {
            arr.addAll(list);
            adapter = new Myadapter(arr, MainActivity.this);
            xrecycleview.setAdapter(adapter);
            adapter.SetItemListion(new Myadapter.ItemListion() {
                @Override
                public void OnitemListion(View view, int Postion) {
                    Intent intent = new Intent(MainActivity.this, Second.class);
                    intent.putExtra("position", Postion);
                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "解析错误", Toast.LENGTH_SHORT).show();
        }
    }
}
