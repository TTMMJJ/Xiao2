package com.bwie.bean;

import android.app.Application;
import android.content.Context;

import com.bwie.green.DaoMaster;
import com.bwie.green.DaoSession;
import com.bwie.green.UserDao;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by T_baby on 17/11/11.
 */

public class Myapp extends Application {
    private static Context context;
    public static UserDao userDao;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        context = getApplicationContext();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "lenvess.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }
    public static Context  GetContext(){
        return context;
    }
}
