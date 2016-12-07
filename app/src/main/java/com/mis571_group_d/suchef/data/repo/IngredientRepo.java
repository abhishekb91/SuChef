package com.mis571_group_d.suchef.data.repo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.mis571_group_d.suchef.data.DatabaseManager;
import com.mis571_group_d.suchef.data.model.Ingredient;
import java.util.ArrayList;

/**
 * Created by abhishek on 12/6/2016.
 */

public class IngredientRepo {
    /**
     * Class name for Logging
     */
    private static String TAG = IngredientRepo.class.getSimpleName().toString();

    /**
     * Empty Constructor
     */
    public IngredientRepo() {
    }

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTable() {
        String query = "CREATE TABLE `" + Ingredient.TABLE + "` (" +
                "`" + Ingredient.KEY_INGREDIENTID + "`    INTEGER PRIMARY KEY AUTOINCREMENT," +
                "`" + Ingredient.KEY_NAME + "`            TEXT," +
                "`" + Ingredient.KEY_CALORIE + "`         INTEGER," +
                "`" + Ingredient.KEY_PROTIEN + "`         INTEGER," +
                "`" + Ingredient.KEY_CARBOHYDRATE + "`    INTEGER," +
                "`" + Ingredient.KEY_FAT + "`             INTEGER," +
                "`" + Ingredient.KEY_IMAGE + "`           TEXT," +
                "`" + Ingredient.KEY_IS_DELETE + "`       INT(1) DEFAULT 0" +
                ");";

        return query;
    }

    /**
     * Function to get list of all Ingredients in the system
     * @return
     */
    public ArrayList getIngredients() {
        ArrayList ingredients = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = " SELECT i.* "+
                " FROM " + Ingredient.TABLE + " as i" +
                " WHERE i." + Ingredient.KEY_IS_DELETE + " = 0" ;

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    //Inserting data into list
                    ingredients.add(new Ingredient(
                            cursor.getLong(cursor.getColumnIndex(Ingredient.KEY_INGREDIENTID)),
                            cursor.getString(cursor.getColumnIndex(Ingredient.KEY_NAME)),
                            cursor.getString(cursor.getColumnIndex(Ingredient.KEY_IMAGE))
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

        return ingredients;
    }
}
