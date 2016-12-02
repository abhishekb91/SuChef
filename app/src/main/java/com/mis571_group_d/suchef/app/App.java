package com.mis571_group_d.suchef.app;

import android.app.Application;
import android.content.Context;

import com.mis571_group_d.suchef.data.DBHelper;
import com.mis571_group_d.suchef.data.DatabaseManager;


/**
 * Created by abhishek on 11/29/2016.
 */

public class  App extends Application {
    private static Context context;
    private static DBHelper dbHelper;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DBHelper();
        DatabaseManager.initializeInstance(dbHelper);
    }

    public static Context getContext(){
        return context;
    }
}
