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
