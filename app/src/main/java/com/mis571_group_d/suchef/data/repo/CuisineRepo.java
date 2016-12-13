package com.mis571_group_d.suchef.data.repo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mis571_group_d.suchef.data.DatabaseManager;
import com.mis571_group_d.suchef.data.model.Cuisine;
import com.mis571_group_d.suchef.data.model.Recipe;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by rogelio on 12/7/16.
 */

public class CuisineRepo {

    public CuisineRepo() {
    }

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTable() {
        String query = "CREATE TABLE " + Cuisine.TABLE + "("
                + Cuisine.KEY_CUISINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Cuisine.KEY_CUISINE_NAME + " TEXT NOT NULL,"
                + Cuisine.KEY_CUISINE_IMAGE + " TEXT NOT NULL,"
                + Cuisine.KEY_IS_DELETE + " INT(1) DEFAULT 0 ); ";

        return query;
    }

    public ArrayList getCuisines() {

        ArrayList cuisines = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = " SELECT c.* " +
                " FROM " + Cuisine.TABLE + " as c" +
                " WHERE c." + Cuisine.KEY_IS_DELETE + " = 0" +
                " ORDER BY c." + Cuisine.KEY_CUISINE_NAME + " ;";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    //Inserting data into list
                    cuisines.add(new Cuisine(
                                    cursor.getLong(cursor.getColumnIndex(Cuisine.KEY_CUISINE_ID)),
                                    cursor.getString(cursor.getColumnIndex(Cuisine.KEY_CUISINE_NAME)),
                                    cursor.getString(cursor.getColumnIndex(Cuisine.KEY_CUISINE_IMAGE))
                            )
                    );

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while getting cuisine information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();

        return cuisines;
    }

    public Cuisine cuisineInfo(long cuisineId) {

        Cuisine cuisine = new Cuisine();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = " SELECT * " +
                " FROM `" + Cuisine.TABLE + "` as r " +
                " WHERE r." + Cuisine.KEY_CUISINE_ID + " = " + cuisineId + ";";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    //Setting Cuisine Id
                    cuisine.setId(cursor.getLong(cursor.getColumnIndex(Cuisine.KEY_CUISINE_ID)));

                    //Setting Cuisine Name
                    cuisine.setCuisineName(cursor.getString(cursor.getColumnIndex(Cuisine.KEY_CUISINE_NAME)));

                    //Setting Cuisine Image
                    cuisine.setCuisineImage(cursor.getString(cursor.getColumnIndex(Cuisine.KEY_CUISINE_IMAGE)));

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while getting cuisine information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();

        return cuisine;
    }

    public ArrayList getCuisineRecipes(long cuisineId) {

        ArrayList recepies = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = " SELECT * " +
                " FROM `" + Recipe.TABLE + "` as r " +
                " WHERE r.cuisine_id = " + cuisineId + ";";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    //Inserting data into list
                    recepies.add(new Recipe(
                                    cursor.getLong(cursor.getColumnIndex(Recipe.KEY_RECIPE_ID)),
                                    cursor.getString(cursor.getColumnIndex(Recipe.KEY_NAME)),
                                    cursor.getString(cursor.getColumnIndex(Recipe.KEY_IMAGE)),
                                    false
                            )
                    );

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while getting cuisine information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();

        return recepies;
    }
}
