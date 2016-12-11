package com.mis571_group_d.suchef.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mis571_group_d.suchef.app.App;
import com.mis571_group_d.suchef.data.model.Ingredient;
import com.mis571_group_d.suchef.data.model.User;
import com.mis571_group_d.suchef.data.repo.CuisineRepo;
import com.mis571_group_d.suchef.data.repo.IngredientRepo;
import com.mis571_group_d.suchef.data.repo.RecipeRepo;
import com.mis571_group_d.suchef.data.repo.SampleData;
import com.mis571_group_d.suchef.data.repo.FavouriteRepo;
import com.mis571_group_d.suchef.data.repo.UserRepo;
import com.mis571_group_d.suchef.data.repo.UtensilsRepo;

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

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    public DBHelper( ) {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(UserRepo.createTable());
        db.execSQL(IngredientRepo.createTable());
        db.execSQL(UtensilsRepo.createTable());
        db.execSQL(RecipeRepo.createTable());
        db.execSQL(RecipeRepo.recipeMaterials());
        db.execSQL(FavouriteRepo.createTable());
        db.execSQL(CuisineRepo.createTable());

        //Inserting sample data into application
        db.execSQL(SampleData.ingredients());
        db.execSQL(SampleData.utensils());
        db.execSQL(SampleData.users());
        db.execSQL(SampleData.recipe());
        db.execSQL(SampleData.user_favourites());
        db.execSQL(SampleData.recipe_materials());
        db.execSQL(SampleData.cuisines());
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
