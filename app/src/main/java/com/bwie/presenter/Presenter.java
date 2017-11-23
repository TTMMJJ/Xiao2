package com.bwie.presenter;

import android.util.Log;

import com.bwie.bean.News;
import com.bwie.model.IModel;
import com.bwie.model.Model;
import com.bwie.view.IView;

import java.util.List;

/**
 * Created by T_baby on 17/11/22.
 */

public class Presenter implements Model.Setnewsdate {
    Model model;
    IView iView;

    public Presenter(IView iView) {
        this.iView = iView;
        model = new Model(this);
    }

    public void getdate(String pag) {
        model.getnews(pag);
    }
    @Override
    public void Setdate(List<News.DataBean> list) {
        iView.getnews(list);
    }
}
