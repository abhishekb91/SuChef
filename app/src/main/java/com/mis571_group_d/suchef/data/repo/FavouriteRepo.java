package com.mis571_group_d.suchef.data.repo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mis571_group_d.suchef.data.DatabaseManager;
import com.mis571_group_d.suchef.data.model.Ingredient;
import com.mis571_group_d.suchef.data.model.Recipe;
import com.mis571_group_d.suchef.data.model.User;
import com.mis571_group_d.suchef.data.model.Favourite;

import java.util.ArrayList;

/**
 * Created by rogelio on 12/7/16.
 */

public class FavouriteRepo {

    private static String TAG = FavouriteRepo.class.getSimpleName().toString();

    public FavouriteRepo() {
    }

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTable() {
        String query = "CREATE TABLE `" + Favourite.TABLE + "` (" +
                "`" + Favourite.KEY_USER_ID + "` INTEGER, " +
                "`" + Favourite.KEY_RECIPE_ID + "` INTEGER, " +
                "`" + Favourite.KEY_ADDED_DATE + "` DATE, " +
                "PRIMARY KEY(`" + Favourite.KEY_USER_ID + "`,`" + Favourite.KEY_RECIPE_ID + "`), " +
                "FOREIGN KEY(`" + Favourite.KEY_USER_ID + "`) REFERENCES " + User.TABLE + "(" + User.KEY_USERID + "), " +
                "FOREIGN KEY(`" + Favourite.KEY_RECIPE_ID + "`) REFERENCES " + Recipe.TABLE + "(" + Recipe.KEY_RECIPE_ID + ")" +
                ");";

        return query;
    }

    /**
     * This function is used to get User's favourite recipe
     *
     * @param user User object for which favourite recipe is needed
     * @return the list of Recipe for a user
     */
    public ArrayList getUserFavourites(User user) {
        ArrayList recipes = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = " SELECT r.* " +
                " FROM `" + Favourite.TABLE + "` us " +
                " JOIN `" + Recipe.TABLE + "` r on r." + Recipe.KEY_RECIPE_ID + " = us." + Favourite.KEY_RECIPE_ID +
                " WHERE us." + Favourite.KEY_USER_ID + " = " + user.getId() + ";";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    //Inserting data into list
                    recipes.add(new Recipe(
                                    cursor.getLong(cursor.getColumnIndex(Recipe.KEY_RECIPE_ID)),
                                    cursor.getString(cursor.getColumnIndex(Recipe.KEY_NAME)),
                                    cursor.getString(cursor.getColumnIndex(Recipe.KEY_IMAGE)),
                                    true
                            )
                    );

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while getting user information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();

        return recipes;
    }
}
