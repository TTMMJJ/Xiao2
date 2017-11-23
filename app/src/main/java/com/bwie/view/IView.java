package com.bwie.view;

import com.bwie.bean.News;

import java.util.List;

/**
 * Created by T_baby on 17/11/22.
 */

public interface IView {
    void getnews(List<News.DataBean> list);
}
