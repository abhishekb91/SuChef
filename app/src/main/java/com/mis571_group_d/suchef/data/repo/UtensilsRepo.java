package com.mis571_group_d.suchef.data.repo;


import android.database.sqlite.SQLiteDatabase;

import com.mis571_group_d.suchef.data.DatabaseManager;
import com.mis571_group_d.suchef.data.model.Recipe;
import com.mis571_group_d.suchef.data.model.Utensil;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by rogelio on 12/8/16.
 */

public class UtensilsRepo {

    private static String TAG = UtensilsRepo.class.getSimpleName().toString();

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTable() {
        String query = "CREATE TABLE `" + Utensil.TABLE + "` (" +
                "`" + Utensil.KEY_UTENSIL_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "`" + Utensil.KEY_UTENSIL_NAME + "` TEXT," +
                "`" + Utensil.KEY_UTENSIL_IMAGE + "` TEXT," +
                "`" + Utensil.KEY_IS_DELETE + "` INTEGER DEFAULT 0 );";

        return query;
    }

    public ArrayList getUtensils() {
        ArrayList ingredients = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = " SELECT u.* "+
                " FROM " + Utensil.TABLE + " as u" +
                " WHERE u." + Utensil.KEY_IS_DELETE + " = 0" ;

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    //Inserting data into list
                    ingredients.add(new Utensil(
                                    cursor.getLong(cursor.getColumnIndex(Utensil.KEY_UTENSIL_ID)),
                                    cursor.getString(cursor.getColumnIndex(Utensil.KEY_UTENSIL_NAME)),
                                    cursor.getString(cursor.getColumnIndex(Utensil.KEY_UTENSIL_IMAGE))
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
    /**
     * Function for recipe details
     *
     * @param recipeId is the id of recipe
     * @return utensils object
     */
    public static Utensil recipeDetail(long recipeId) {
        Utensil utensils = new Utensil(recipeId, Utensil.KEY_UTENSIL_NAME,Utensil.KEY_UTENSIL_IMAGE);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String queryUtensil = "SELECT u." + Utensil.KEY_UTENSIL_NAME + ", m." + Recipe.KEY_AMOUNT + ", m." + Recipe.KEY_UNIT +
                " FROM " + Utensil.TABLE + " u," + Recipe.TABLE + " m" +
                " WHERE m." + Recipe.KEY_TYPE + " = 2" +
                " AND u." + Utensil.KEY_UTENSIL_ID + " = m." + Recipe.KEY_MATERIAL_ID + ";" +
                " AND m." + Recipe.KEY_RECIPE_ID + " = '" + recipeId + "';";
//        "SELECT u.name, m.amount, m.unit
//        FROM utensils u, recipe_materials m
//        WHERE m.type = //  type 2 is utensils
//        AND u.utensils_id = m.material_id
//        AND m.recipe_id = recipeId

        return utensils;
    }
}