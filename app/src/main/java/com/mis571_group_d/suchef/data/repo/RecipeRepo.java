package com.mis571_group_d.suchef.data.repo;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.mis571_group_d.suchef.data.DatabaseManager;
import com.mis571_group_d.suchef.data.model.Favourite;
import com.mis571_group_d.suchef.data.model.Ingredient;
import com.mis571_group_d.suchef.data.model.Recipe;
import com.mis571_group_d.suchef.data.model.Utensil;

import java.util.ArrayList;
import java.util.Date;

import static android.R.attr.type;
import static com.mis571_group_d.suchef.R.string.date;
import static com.mis571_group_d.suchef.data.model.Recipe.KEY_PREPARATION_METHOD;

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
                "`" + Recipe.KEY_RECIPE_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT," +
                "`" + Recipe.KEY_NAME + "` VARCHAR(25)," +
                "`" + Recipe.KEY_CUSINE_ID + "` INTEGER," +
                "`" + Recipe.KEY_IMAGE + "` VARCHAR(20)," +
                "`" + Recipe.KEY_PREPARATION_METHOD + "` TEXT," +
                "`" + Recipe.KEY_IS_DELETE + "` INT(1) DEFAULT 0 );";

        return query;
    }

    /**
     * Recipe Materials query
     *
     * @return create table query
     */
    public static String recipeMaterials() {
        String query = " CREATE TABLE `" + Recipe.RECIPE_MATERIALS_TABLE + "` (" +
                " `" + Recipe.KEY_RECIPE_ID + "` INTEGER, " +
                " `" + Recipe.KEY_MATERIAL_ID + "` INTEGER, " +
                " `" + Recipe.KEY_AMOUNT + "` INTEGER, " +
                " `" + Recipe.KEY_UNIT + "` INTEGER, " +
                " `" + Recipe.KEY_TYPE + "` INTEGER, " +
                " PRIMARY KEY(`" + Recipe.KEY_RECIPE_ID + "`,`" + Recipe.KEY_MATERIAL_ID + "`,`" + Recipe.KEY_TYPE + "`)," +
                " FOREIGN KEY(`" + Recipe.KEY_RECIPE_ID + "`) REFERENCES recipes(recipe_id)" +
                ");";

        return query;
    }

    /**
     * Function for recipe details
     *
     * @param recipeId is the id of recipe
     * @return recipe object
     */
    public Recipe recipeDetail(long recipeId) {

        //Creating Recipe object to save the recipe details
        Recipe recipe = new Recipe();

        //Defining ingredients and utensils ArrayList
        ArrayList ingredients = new ArrayList();

        ArrayList utensils = new ArrayList();

        String query = "SELECT r.recipe_id, r.name as recipe_name, r.image as recipe_image, r.preparation_method as preparation_method," +
                " rm.material_id, rm.amount, rm.unit, rm.type, i.ingredient_id, i.name as ingredient_name, " +
                " u.utensil_id, u.name as utensil_name" +
                " FROM `recipes` as r " +
                " JOIN 'recipe_materials' as rm on rm.recipe_id = r.recipe_id " +
                " LEFT JOIN `ingredients` as i on (i.ingredient_id = rm.material_id and rm.type = 1) " +
                " LEFT JOIN `utensils` as u on (u.utensil_id = rm.material_id and rm.type = 2) " +
                " WHERE r.recipe_id = " + recipeId + " " +
                " ORDER BY rm.type; ";

        Log.d(TAG, query);

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    //Setting ID
                    recipe.setId(cursor.getLong(cursor.getColumnIndex("recipe_id")));

                    //Setting Recipe Name
                    recipe.setName(cursor.getString(cursor.getColumnIndex("recipe_name")));

                    //Setting Recipe Image
                    recipe.setRecipeImage(cursor.getString(cursor.getColumnIndex("recipe_image")));

                    //Setting Recipe Preparation Method
                    recipe.setPreparationMethod(cursor.getString(cursor.getColumnIndex("preparation_method")));

                    if (cursor.getInt(cursor.getColumnIndex(Recipe.KEY_TYPE)) == 1) {
                        ingredients.add(new Ingredient(
                                cursor.getLong(cursor.getColumnIndex("ingredient_id")),
                                cursor.getString(cursor.getColumnIndex("ingredient_name")),
                                cursor.getFloat(cursor.getColumnIndex("amount")),
                                cursor.getString(cursor.getColumnIndex("unit"))
                        ));
                    } else {
                        utensils.add(new Utensil(
                                cursor.getLong(cursor.getColumnIndex("utensil_id")),
                                cursor.getString(cursor.getColumnIndex("utensil_name")),
                                cursor.getFloat(cursor.getColumnIndex("amount")),
                                cursor.getString(cursor.getColumnIndex("unit"))
                        ));
                    }

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error while getting user information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();

        //Setting all the ingredients and utensils info
        recipe.setRecipeIngredients(ingredients);

        recipe.setRecipeUtensils(utensils);

        return recipe;
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

    /**
     * This function is used for recipe result from search screen when user wants to search extensively
     *
     * @param ingredients
     * @param utensils
     * @return
     */
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
                        " WHERE u." + Utensil.KEY_UTENSIL_ID + " = " + utensils.get(i) +
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

    /**
     * This function sets a recipe as User's favourite
     *
     * @param userId
     * @param recipeId
     * @return
     */
    public void addRecipeAsFaourite(Long userId, Long recipeId) {

        Date date = new Date();

        String query = "INSERT INTO `user_favourites`(`user_id`,`recipe_id`,`added_date`) VALUES " +
                "(" + userId + "," + recipeId + ",'" + date.toString() + "');";

        Log.d(TAG, query);

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        db.execSQL(query);
    }

    /**
     * This function Removes a recipe as User's favourite
     *
     * @param userId
     * @param recipeId
     * @return
     */
    public void removeRecipeAsFaourite(Long userId, Long recipeId) {

        String query = "DELETE FROM `user_favourites` " +
                " WHERE `user_id`='" + userId + "'" +
                " AND `recipe_id` = '" + recipeId + "';";

        Log.d(TAG, query);

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        db.delete("user_favourites", "user_id = " + userId + " AND recipe_id = " + recipeId, null);
    }

    /**
     * This function checks if a recipe is user's favourite recipe or not
     *
     * @param userId
     * @param recipeId
     * @return
     */
    public Boolean checkRecipeFavourite(Long userId, Long recipeId) {

        Boolean isFavourite = false;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String query = "SELECT * " +
                " FROM user_favourites " +
                " WHERE user_id = " + userId + " " +
                " AND recipe_id = " + recipeId + ";";

        Cursor cursor = db.rawQuery(query, null);

        try {
            //cursor.getLong(cursor.getColumnIndex(Recipe.KEY_RECIPE_ID)),
            isFavourite = (cursor.getCount() == 0) ? false : true;
        } catch (Exception e) {
            Log.e(TAG, "Error while getting information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();

        return isFavourite;
    }
}
