package com.mis571_group_d.suchef.data.repo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.mis571_group_d.suchef.data.DatabaseManager;
import com.mis571_group_d.suchef.data.model.Ingredient;
import com.mis571_group_d.suchef.data.model.Recipe;

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
                "`" + Ingredient.KEY_INGREDIENT_ID + "`    INTEGER PRIMARY KEY AUTOINCREMENT," +
                "`" + Ingredient.KEY_NAME + "`            TEXT," +
                "`" + Ingredient.KEY_CALORIE + "`         INTEGER," +
                "`" + Ingredient.KEY_PROTEIN + "`         INTEGER," +
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
                            cursor.getLong(cursor.getColumnIndex(Ingredient.KEY_INGREDIENT_ID)),
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
    /**
     * Function for recipe details
     *
     * @param recipeId is the id of recipe
     * @return ingredients object
     */
    /*public static Ingredient recipeDetail(long recipeId) {
        Ingredient ingredient = new Ingredient();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String queryIngredients = "SELECT i." + Ingredient.KEY_NAME + ", m." + Recipe.KEY_AMOUNT + ", m." + Recipe.KEY_UNIT +
                " FROM " + Ingredient.TABLE + " i," + Recipe.TABLE + " m" +
                " WHERE m." + Recipe.KEY_TYPE + " = 1" +
                " AND i." + Ingredient.KEY_INGREDIENT_ID + " = m." + Recipe.KEY_MATERIAL_ID + ";" +
                " AND m." + Recipe.KEY_RECIPE_ID + " = '" + recipeId + "';";

        return ingredient;
    }

    public static Ingredient recipeNutrition(long recipeId) {
        Ingredient ingredient = new Ingredient();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String queryNutrition = "SELECT SUM(i." + Ingredient.KEY_CALORIE + " * m." + Recipe.KEY_AMOUNT + ")" +
                " SUM(i." + Ingredient.KEY_PROTEIN + " * m." + Recipe.KEY_AMOUNT + ")" +
                " SUM(i." + Ingredient.KEY_CARBOHYDRATE + " * m." + Recipe.KEY_AMOUNT + ")" +
                " SUM(i." + Ingredient.KEY_FAT + " * m." + Recipe.KEY_AMOUNT + ")" +
                " FROM " + Ingredient.TABLE + " i," + Recipe.TABLE + " m" +
                " WHERE m." + Recipe.KEY_TYPE + " = 1" +
                " AND i." + Ingredient.KEY_INGREDIENT_ID + " = m." + Recipe.KEY_MATERIAL_ID + ";" +
                " AND m." + Recipe.KEY_RECIPE_ID + " = '" + recipeId + "';";

        return ingredient;
    }*/

}
