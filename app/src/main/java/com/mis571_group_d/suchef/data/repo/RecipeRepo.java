package com.mis571_group_d.suchef.data.repo;


import com.mis571_group_d.suchef.data.model.Recipe;

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
    public Recipe recipeDetail(long recipeId) {
        Recipe recipe = new Recipe();

        return recipe;
    }
}
