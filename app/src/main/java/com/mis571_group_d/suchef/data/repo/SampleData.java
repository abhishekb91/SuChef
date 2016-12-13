package com.mis571_group_d.suchef.data.repo;

import android.content.ContentUris;

import com.mis571_group_d.suchef.data.model.Cuisine;
import com.mis571_group_d.suchef.data.model.Favourite;
import com.mis571_group_d.suchef.data.model.Ingredient;
import com.mis571_group_d.suchef.data.model.Recipe;
import com.mis571_group_d.suchef.data.model.User;
import com.mis571_group_d.suchef.data.model.Utensil;

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

        String query = "INSERT INTO `" + User.TABLE + "`(`" + User.KEY_USERID + "`,`" + User.KEY_USERNAME + "`,`" + User.KEY_PASSWORD + "`) VALUES " +
                "(1, 'abcd@wpi.edu','123456'),(2, 'test@wpi.edu','password')";

        return query;
    }

    /**
     * Sample data for ingredients
     *
     * @return insert queries for ingredients
     */
    public static String ingredients() {

        String query = "INSERT INTO `" + Ingredient.TABLE + "`(`" + Ingredient.KEY_INGREDIENT_ID + "`,`" + Ingredient.KEY_NAME + "`,`" + Ingredient.KEY_CALORIE + "`,`" + Ingredient.KEY_PROTEIN + "`,`" + Ingredient.KEY_CARBOHYDRATE + "`,`" + Ingredient.KEY_FAT + "`, `" + Ingredient.KEY_IMAGE + "`) VALUES " +
                "(1,'Butter',10,20,30,40,'butter'),(2,'Mayonnaise',10,20,30,40,'mayonnaise'),(3,'Rice',10,20,30,40,'rice'),(4,'Lemon',10,20,30,40,'lemon')," +
                "(5,'Eggs',10,20,30,40,'eggs'),(6,'Banana',10,20,30,40,'banana'),(7,'Sugar',10,20,30,40,'sugar'),(8,'Tomato',10,20,30,40,'tomato')," +
                "(9,'Oil',10,20,30,40,'oil'),(10,'Chicken',20,40,30,40,'chicken'),(11,'Onion',10,20,30,50,'onion'),(12,'Garlic',10,10,20,40,'garlic')," +
                "(13,'Salt',1,2,3,4,'salt');";

        return query;
    }

    /**
     * Sample data for utensils
     *
     * @return insert queries for utensils
     */
    public static String utensils() {
        String query = "INSERT INTO `" + Utensil.TABLE + "`(`" + Utensil.KEY_UTENSIL_ID + "`, `" + Utensil.KEY_UTENSIL_NAME + "`, `" + Utensil.KEY_UTENSIL_IMAGE + "`) VALUES " +
                "(1, 'Cooker', 'cooker'), (2, 'Frying Pan', 'frying_pan'), (3, 'Medium Pot', 'medium_pot');";

        return query;
    }

    /**
     * Sample data for recipe
     *
     * @return insert query for recipe
     */
    public static String recipe() {

        String query = "INSERT INTO `" + Recipe.TABLE + "`(`" + Recipe.KEY_RECIPE_ID + "`, `" + Recipe.KEY_NAME + "`,`" + Recipe.KEY_CUSINE_ID + "`,`" + Recipe.KEY_IMAGE + "`,`" + Recipe.KEY_PREPARATION_METHOD + "`) VALUES " +
                "(1, 'Breakfast Sandwich', 1, 'breakfast_sandwich', '1) Preheat oven to 350 degrees.\\n2) Stir eggs, mayonnaise, mustard, honey, horseradish, cayenne pepper, and hot pepper sauce together in a bowl; season with salt and black pepper.\\n3) Place English muffins, cut-side up, on an ungreased baking sheet. Place 1 slice Canadian bacon on each muffin. Spoon 1/4 cup of egg mixture on top of bacon and sprinkle with pepper jack cheese. Top with green onions.\\n4) Bake in preheated oven until cheese melts, 6 to 8 minutes.')," +
                "(2, 'Bread Butter Pudding', 2, 'bread_butter_pudding', '1) Preheat oven to 350 degrees F (175 degrees C). Grease a 9x5 inch loaf pan.\\n2) In a large mixing bowl, mix eggs, milk, sugar, and vanilla until smooth. Stir in bread, bananas, and chocolate chips, and let rest 5 minutes for bread to soak. Pour into prepared pan.\\n3) Line a roasting pan with a damp kitchen towel. Place loaf pan on towel inside roasting pan, and place roasting pan on oven rack. Fill roasting pan with water to reach halfway up the sides of the loaf pan. Bake in preheated oven for 1 hour, or until a knife inserted in the center comes out clean.)')," +
                "(3, 'Lasagna', 3, 'lasagna', '1) Preheat oven to 350 degrees F (175 degrees C).\\n2) Reserve 2/3 cup of the mozzarella cheese for the top layer of the lasagna. In a medium size bowl, combine remaining 1 1/3 cup mozzarella, soup and milk and set aside. In another medium size bowl, combine spinach, egg and ricotta. Mix well, then combine with soup mixture and mix all together.\\n3) In the bottom of a 13x9 inch baking dish, spread 1/3 of the mixture. Spread 1/3 of the chicken over the mixture and arrange 4 lasagna noodles over mixture and repeat. Top with remaining 1/3 of mixture, chicken, noodles and reserved 2/3 cup mozzarella cheese and Parmesan cheese.\\n4) Bake at 350 degrees F (175 degrees C) for 40 minutes or until hot and bubbling. Let stand 15 minutes before serving.');";

        return query;
    }

    /**
     * Sample data for recipe materials
     *
     * @return
     */
    public static String recipe_materials() {

        String query = "INSERT INTO `" + Recipe.RECIPE_MATERIALS_TABLE + "`(`" + Recipe.KEY_RECIPE_ID + "`,`" + Recipe.KEY_MATERIAL_ID + "`,`" + Recipe.KEY_AMOUNT + "`,`" + Recipe.KEY_UNIT + "`,`" + Recipe.KEY_TYPE + "`) VALUES " +
                "(1,1,1,'spoon',1),(1,2,2,'spoon',1),(1,3,3,'spoon',1),(1,4,4,'spoon',1),(1,3,1,'unit',2)," +
                "(2,1,2,'spoon',1),(2,2,3,'spoon',1),(2,3,4,'spoon',1),(2,4,5,'spoon',1),(2,3,1,'unit',2)," +
                "(3,9,2,'spoon',1),(3,10,500,'Gms',1),(3,11,1,'cup',1),(3,12,2,'piece',1),(3,13,2,'spoon',1),(3,3,1,'unit',2),(3,2,1,'unit',2);";

        return query;
    }

    /**
     * Sample data for user favourite
     *
     * @return
     */
    public static String user_favourites() {

        String query = "INSERT INTO `" + Favourite.TABLE + "`(`" + Favourite.KEY_RECIPE_ID + "`,`" + Favourite.KEY_USER_ID + "`,`" + Favourite.KEY_ADDED_DATE + "`) VALUES " +
                "(1,1,'2016-12-09'),(2,1,'2016-12-09')," +
                "(1,2,'2016-12-10'),(2,2,'2016-12-10');";

        return query;
    }

    /**
     * Sample data for Cuisines
     *
     * @return
     */
    public static String cuisines() {

        String query = "INSERT INTO `" + Cuisine.TABLE + "`(`" + Cuisine.KEY_CUISINE_ID + "`,`" + Cuisine.KEY_CUISINE_NAME + "`,`" + Cuisine.KEY_CUISINE_IMAGE + "`) VALUES " +
                "(1,'Italian','italian'),(2,'Indian','indian'),(3,'Chinese','chinese'),(4,'Vegetarian','vegetarian');";

        return query;
    }
}
