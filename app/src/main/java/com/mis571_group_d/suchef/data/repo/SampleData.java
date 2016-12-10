package com.mis571_group_d.suchef.data.repo;

import com.mis571_group_d.suchef.data.model.Favourite;
import com.mis571_group_d.suchef.data.model.Ingredient;
import com.mis571_group_d.suchef.data.model.Recipe;
import com.mis571_group_d.suchef.data.model.User;

/**
 * Created by abhishek on 12/7/2016.
 */

public class SampleData {

    /**
     * Sample data for ingredients
     *
     * @return insert queries for ingredients
     */
    public static String users() {

        String query = "INSERT INTO `" + User.TABLE + "`(`" + User.KEY_USERNAME + "`,`" + User.KEY_PASSWORD + "`) VALUES " +
                "('abcd@wpi.edu','123456'),('test@wpi.edu','password')";

        return query;
    }

    /**
     * Sample data for ingredients
     *
     * @return insert queries for ingredients
     */
    public static String ingredients() {

        String query = "INSERT INTO `" + Ingredient.TABLE + "`(`" + Ingredient.KEY_INGREDIENTID + "`,`" + Ingredient.KEY_NAME + "`,`" + Ingredient.KEY_CALORIE + "`,`" + Ingredient.KEY_PROTIEN + "`,`" + Ingredient.KEY_CARBOHYDRATE + "`,`" + Ingredient.KEY_FAT + "`, `" + Ingredient.KEY_IMAGE + "`) VALUES " +
                "(NULL,'butter',10,20,30,40,'butter'),(NULL,'mayonnaise',10,20,30,40,'mayonnaise'),(NULL,'rice',10,20,30,40,'rice'),(NULL,'lemon',10,20,30,40,'lemon')," +
                "(NULL,'eggs',10,20,30,40,'eggs'),(NULL,'banana',10,20,30,40,'banana'),(NULL,'sugar',10,20,30,40,'sugar'),(NULL,'tomato',10,20,30,40,'tomato');";

        return query;
    }

    /**
     * Sample data for recipe
     *
     * @return insert query for recipe
     */
    public static String recipe() {

        String query = "INSERT INTO `" + Recipe.TABLE + "`(`" + Recipe.KEY_NAME + "`,`" + Recipe.KEY_CUSINE_ID + "`,`" + Recipe.KEY_IMAGE + "`) VALUES " +
                "('Breakfast Sandwich', 1, 'breakfast_sandwich')," +
                "('Bread Butter Pudding', 2, 'bread_butter_pudding');";

        return query;
    }

    /**
     * Sample data for recipe materials
     *
     * @return
     */
    public static String recipe_materials() {

        String query = "INSERT INTO `"+Recipe.RECIPE_MATERIALS_TABLE+"`(`"+Recipe.KEY_RECIPE_ID+"`,`"+Recipe.KEY_MATERIAL_ID+"`,`"+Recipe.KEY_AMOUNT+"`,`"+Recipe.KEY_UNIT+"`,`"+Recipe.KEY_TYPE+"`) VALUES " +
                "(1,1,1,'spoon',1),(1,2,2,'spoon',1),(1,3,3,'spoon',1),(1,4,4,'spoon',1)," +
                "(2,1,2,'spoon',1),(2,2,3,'spoon',1),(2,3,4,'spoon',1),(2,4,5,'spoon',1)";

        return query;
    }

    /**
     * Sample data for user favourite
     *
     * @return
     */
    public static String user_favourites() {

        String query = "INSERT INTO `"+ Favourite.TABLE+"`(`"+Favourite.KEY_RECIPE_ID+"`,`"+Favourite.KEY_USER_ID+"`,`"+Favourite.KEY_ADDED_DATE+"`) VALUES " +
                "(1,1,'2016-12-09'),(2,1,'2016-12-09')," +
                "(1,2,'2016-12-10'),(2,2,'2016-12-10');";

        return query;
    }
}
