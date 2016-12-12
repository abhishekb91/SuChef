package com.mis571_group_d.suchef.data.repo;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.mis571_group_d.suchef.data.DatabaseManager;
import com.mis571_group_d.suchef.data.model.Favourite;

import android.database.sqlite.SQLiteDatabase;

import com.mis571_group_d.suchef.data.DatabaseManager;
import com.mis571_group_d.suchef.data.model.Cuisine;
import com.mis571_group_d.suchef.data.model.Ingredient;
import com.mis571_group_d.suchef.data.model.Recipe;
import com.mis571_group_d.suchef.data.model.User;
import com.mis571_group_d.suchef.data.model.Ingredient;
import com.mis571_group_d.suchef.data.model.Recipe;
import com.mis571_group_d.suchef.data.model.Utensil;

import java.util.ArrayList;

/**
 * Created by abhishek on 12/7/2016.
 */

public class RecipeRepo {
    /**
     * Class name for Logging
     */
    private static String TAG = RecipeRepo.class.getSimpleName().toString();

    /**
     * Empty Constructor
     */
    public RecipeRepo() {
    }

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTable() {

        String query = "CREATE TABLE `" + Recipe.TABLE + "` (" +
                "`" + Recipe.KEY_RECIPE_ID + "`     INTEGER PRIMARY KEY AUTOINCREMENT," +
                "`" + Recipe.KEY_NAME + "`          VARCHAR(50)," +
                "`" + Recipe.KEY_CUSINE_ID + "`     INTEGER," +
                "`" + Recipe.KEY_IMAGE + "`         TEXT," +
                "`" + Recipe.KEY_IS_DELETE + "`     INT(1) DEFAULT 0 );";

        return query;
    }

    /**
     * Recipe Materials query
     *
     * @return create table query
     */
    public static String recipeMaterials() {
        String query = "CREATE TABLE `" + Recipe.RECIPE_MATERIALS_TABLE + "` (" +
                "`" + Recipe.KEY_RECIPE_ID + "` INTEGER, " +
                "`" + Recipe.KEY_MATERIAL_ID + "` INTEGER, " +
                "`" + Recipe.KEY_AMOUNT + "` INTEGER, " +
                "`" + Recipe.KEY_UNIT + "` INTEGER, " +
                "`" + Recipe.KEY_TYPE + "` INT(1) " +
                /*"PRIMARY KEY(`recipe_id`,`material_id`),\n" +
                "FOREIGN KEY(`recipe_id`) REFERENCES recipes(recipe_id)\n" +*/
                ");";

        return query;
    }

    /**
     * Function for recipe details
     *
     * @param recipeId is the id of recipe
     * @return recipe object
     */
    public String recipeDetail(long recipeId) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();


        String queryDetail = "SELECT r." + Recipe.KEY_NAME + ", r." + Recipe.KEY_DESCRIPTION +
                ", r." + Recipe.KEY_PREPARATION_METHOD + ", c." + Cuisine.KEY_CUISINE_NAME +
                " FROM " + Recipe.TABLE + " r," + Cuisine.TABLE + " c" +
                " WHERE r." + Recipe.KEY_RECIPE_ID + " = '" + recipeId + "'" +
                " AND r." + Recipe.KEY_CUSINE_ID + " = c." + Cuisine.KEY_CUISINE_ID + ";";
//       "SELECT r.name, r.description
//      , r.preparation_method, c.name
//        FROM recipes r, cuisines c
//        WHERE r.recipe_id = recipeId
//        AND r.cuisines_id = c.cuisines_id;

        return queryDetail;
    }

    /**
     * This function is used for recipe result from search screen
     *
     * @param ingredients
     * @param utensils
     * @return
     */
    public ArrayList recipeSearchResult(ArrayList ingredients, ArrayList utensils) {
        ArrayList result = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String query;

        query = "SELECT r." + Recipe.KEY_RECIPE_ID + " as " + Recipe.KEY_RECIPE_ID + ", " +
                "r." + Recipe.KEY_NAME + " as " + Recipe.KEY_NAME + ", " +
                "r." + Recipe.KEY_IMAGE + " as " + Recipe.KEY_IMAGE + " " +
                "FROM `" + Recipe.TABLE + "` as r " +
                "JOIN `" + Recipe.RECIPE_MATERIALS_TABLE + "` as rm on r." + Recipe.KEY_RECIPE_ID + " = rm." + Recipe.KEY_RECIPE_ID + " " +
                "JOIN `" + Ingredient.TABLE + "` as i on (i." + Ingredient.KEY_INGREDIENT_ID + " = rm." + Recipe.KEY_MATERIAL_ID + " AND type = 1) " +
                "WHERE ( i." + Ingredient.KEY_INGREDIENT_ID + " IN (" + TextUtils.join(", ", ingredients) + ")  ) " +
                "GROUP BY r." + Recipe.KEY_RECIPE_ID + " ";


        //Check if user had selected utensils or not
        if (!utensils.isEmpty()) {

            query += "INTERSECT " +

                    "SELECT r." + Recipe.KEY_RECIPE_ID + " as " + Recipe.KEY_RECIPE_ID + ", " +
                    "r." + Recipe.KEY_NAME + " as " + Recipe.KEY_NAME + ", " +
                    "r." + Recipe.KEY_IMAGE + " as " + Recipe.KEY_IMAGE + " " +
                    "FROM `" + Recipe.TABLE + "` as r " +
                    "JOIN `" + Recipe.RECIPE_MATERIALS_TABLE + "` as rm on r." + Recipe.KEY_RECIPE_ID + " = rm." + Recipe.KEY_RECIPE_ID + " " +
                    "JOIN `" + Utensil.TABLE + "` as u on (u." + Utensil.KEY_UTENSIL_ID + " = rm." + Recipe.KEY_MATERIAL_ID + " AND rm.type = 2) " +
                    "WHERE ( u." + Utensil.KEY_UTENSIL_ID + " IN (" + TextUtils.join(", ", utensils) + ")  ) " +
                    "GROUP BY r." + Recipe.KEY_RECIPE_ID + ";";
        }

        Log.d(TAG, query);

        Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    //Inserting data into list
                    result.add(new Recipe(
                                    cursor.getLong(cursor.getColumnIndex(Recipe.KEY_RECIPE_ID)),
                                    cursor.getString(cursor.getColumnIndex(Recipe.KEY_NAME)),
                                    cursor.getString(cursor.getColumnIndex(Recipe.KEY_IMAGE)),
                                    true
                            )
                    );

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error while getting user information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();

        return result;
    }

    public ArrayList recipeSearchResultExtensive(ArrayList ingredients, ArrayList utensils) {
        ArrayList result = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String query = "";

        for (int i = 0; i < ingredients.size(); i++) {

            if (i != 0) {
                query += " INTERSECT ";
            }

            query += " SELECT r." + Recipe.KEY_RECIPE_ID + " as " + Recipe.KEY_RECIPE_ID + ", " +
                    " r." + Recipe.KEY_NAME + " as " + Recipe.KEY_NAME + ", " +
                    " r." + Recipe.KEY_IMAGE + " as " + Recipe.KEY_IMAGE + " " +
                    " FROM `" + Recipe.TABLE + "` as r " +
                    " JOIN `" + Recipe.RECIPE_MATERIALS_TABLE + "` as rm on r." + Recipe.KEY_RECIPE_ID + " = rm." + Recipe.KEY_RECIPE_ID + " " +
                    " JOIN `" + Ingredient.TABLE + "` as i on (i." + Ingredient.KEY_INGREDIENT_ID + " = rm." + Recipe.KEY_MATERIAL_ID + " AND type = 1) " +
                    " WHERE i." + Ingredient.KEY_INGREDIENT_ID + " = " + ingredients.get(i) +
                    " GROUP BY r." + Recipe.KEY_RECIPE_ID + " ";
        }

        //Check if user had selected utensils or not
        if (!utensils.isEmpty()) {

            for (int i = 0; i < utensils.size(); i++) {

                query += " INTERSECT " +
                        " SELECT r." + Recipe.KEY_RECIPE_ID + " as " + Recipe.KEY_RECIPE_ID + ", " +
                        " r." + Recipe.KEY_NAME + " as " + Recipe.KEY_NAME + ", " +
                        " r." + Recipe.KEY_IMAGE + " as " + Recipe.KEY_IMAGE + " " +
                        " FROM `" + Recipe.TABLE + "` as r " +
                        " JOIN `" + Recipe.RECIPE_MATERIALS_TABLE + "` as rm on r." + Recipe.KEY_RECIPE_ID + " = rm." + Recipe.KEY_RECIPE_ID + " " +
                        " JOIN `" + Utensil.TABLE + "` as u on (u." + Utensil.KEY_UTENSIL_ID + " = rm." + Recipe.KEY_MATERIAL_ID + " AND rm.type = 2) " +
                        " WHERE u." + Utensil.KEY_UTENSIL_ID + " = " + utensils.get(i)  +
                        " GROUP BY r." + Recipe.KEY_RECIPE_ID + ";";
            }

        }

        Log.d(TAG, query);

        Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    //Inserting data into list
                    result.add(new Recipe(
                                    cursor.getLong(cursor.getColumnIndex(Recipe.KEY_RECIPE_ID)),
                                    cursor.getString(cursor.getColumnIndex(Recipe.KEY_NAME)),
                                    cursor.getString(cursor.getColumnIndex(Recipe.KEY_IMAGE)),
                                    true
                            )
                    );

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error while getting user information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();

        return result;
    }
}
