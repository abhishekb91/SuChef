package com.mis571_group_d.suchef.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mis571_group_d.suchef.app.App;
import com.mis571_group_d.suchef.data.model.Ingredient;
import com.mis571_group_d.suchef.data.model.User;
import com.mis571_group_d.suchef.data.repo.IngredientRepo;
import com.mis571_group_d.suchef.data.repo.SampleData;
import com.mis571_group_d.suchef.data.repo.UserRepo;

/**
 * Created by abhishek on 11/29/2016.
 */

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "suChef";

    private static final String TAG = DBHelper.class.getSimpleName().toString();

    public DBHelper( ) {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(UserRepo.createTable());
        db.execSQL(IngredientRepo.createTable());

        //Inserting sample data into application
        db.execSQL(SampleData.ingredients());
        db.execSQL(SampleData.users());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Ingredient.TABLE);
        onCreate(db);
    }

}
