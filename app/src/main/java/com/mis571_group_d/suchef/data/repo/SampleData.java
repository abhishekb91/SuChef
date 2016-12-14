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
                "(3, 'Lasagna', 1, 'lasagna', '1) Preheat oven to 350 degrees F (175 degrees C).\\n2) Reserve 2/3 cup of the mozzarella cheese for the top layer of the lasagna. In a medium size bowl, combine remaining 1 1/3 cup mozzarella, soup and milk and set aside. In another medium size bowl, combine spinach, egg and ricotta. Mix well, then combine with soup mixture and mix all together.\\n3) In the bottom of a 13x9 inch baking dish, spread 1/3 of the mixture. Spread 1/3 of the chicken over the mixture and arrange 4 lasagna noodles over mixture and repeat. Top with remaining 1/3 of mixture, chicken, noodles and reserved 2/3 cup mozzarella cheese and Parmesan cheese.\\n4) Bake at 350 degrees F (175 degrees C) for 40 minutes or until hot and bubbling. Let stand 15 minutes before serving.'), " +
                "(4, 'Fried Eggs with Tomatoes', 3, 'fried_eggs_with_tomatoes', '1) Cut tomatoes into small pieces\\n2) Stir eggs\\n3) Add oil into cooker till hot, pour into tomatoes, egg liquid, salt into cooker. Fry them until done.' )," +
                "(5, 'Steamed Rice with Red-Cooked Pork', 3, 'steamed_rice_with_red_cooked_pork', '1) Cut pork into small cubes\\n2) Preheat the cooker and add oil\\n3) Stir in sugar, soy sauce, onion, and pork, then add a lot of water which immerse our ingredients\\n4) Boil them about 1 hour, until the pork become attractive red and there is no redundant water in our cooker\\n5) Steam rice')," +
                "(6, 'Chicken Curry', 2, 'chicken_curry', '1) cut onion, tomato and chicken into cubes\\n2) Add one fifth water into big cooker, and add all Curry powder and onion cubes into it. Boil 20 minutes till all ingredients mix with each other, then we pour our chicken into the curry soup, after 20 minutes cook, we fill a bowl with our curry chicken\\n3) stir salt into curry chicken')," +
                "(7, 'Biryani', 2, 'biryani', '1) In a large skillet, in 2 tablespoons vegetable oil (or ghee) fry potatoes until brown, drain and reserve the potatoes. Add remaining 2 tablespoons oil to the skillet and fry onion, garlic and ginger until onion is soft and golden. Add chili, pepper, turmeric, cumin, salt and the tomatoes. Fry, stirring constantly for 5 minutes. Add yogurt, mint, cardamom and cinnamon stick. Cover and cook over low heat, stirring occasionally until the tomatoes are cooked to a pulp. It may be necessary to add a little hot water if the mixture becomes too dry and starts to stick to the pan.\\n2) When the mixture is thick and smooth, add the chicken pieces and stir well to coat them with the spice mixture. Cover and cook over very low heat until the chicken is tender, approximately 35 to 45 minutes. There should only be a little very thick gravy left when chicken is finished cooking. If necessary cook uncovered for a few minutes to reduce the gravy.\\n3) Wash rice well and drain in colander for at least 30 minutes.\\n4) In a large skillet, heat vegetable oil (or ghee) and fry the onions until they are golden. Add saffron, cardamom, cloves, cinnamon stick, ginger and rice. Stir continuously until the rice is coated with the spices.\\n5) In a medium-size pot, heat the chicken stock and salt. When the mixture is hot pour it over the rice and stir well. Add the chicken mixture and the potatoes; gently mix them into the rice. Bring to boil. Cover the saucepan tightly, turn heat to very low and steam for 20 minutes. Do not lift lid or stir while cooking. Spoon biryani onto a warm serving dish.')," +
                "(8, 'Pasta Puttanesca', 1, 'pasta_puttanesca', '1) Cook spaghetti in a pasta pot of boiling salted water (2 1/2 Tbsp salt for 6 qt water) until barely al dente.\\n2) While pasta boils, cook garlic, anchovy paste, red-pepper flakes, 1 tsp salt, and 1/2 tsp pepper in oil in a 12-inch heavy skillet over medium-high heat, stirring occasionally, until fragrant and pale golden, about 2 minutes.\\n3)Meanwhile, purée tomatoes with juice in a blender.\\n4)Add tomato purée to garlic oil along with olives and capers and simmer, stirring occasionally, until pasta is ready. Stir in sugar if desired.\\n5)Drain pasta and add to sauce. Simmer, turning pasta with tongs, until pasta is al dente, about 2 minutes. Sprinkle with basil.')," +
                "(9, 'Kimchi Pancake', 4, 'kimchi_pancake', '1) place 1 cup of chopped kimchi, 1ts salt, 1 ts sugar, 1 cup flour and ¼ cup of water and mix it well with a spoon.\\n2) Heat up a 12 inch non-stick pan over medium high heat and drizzle about 2 tbs oil.\\n3) Place the mixture of kimchi pancake batter on the pan and spread it thinly and evenly with a spoon.\\n4) Cook it for 1 to 1½ minutes until the bottom gets golden brown and crispy.\\n5) Turn it over with a spatula or flip it.\\n6) Lower the heat to medium and cook for another 1½ minutes.\\n7) Turn it over one more time and cook for 30 seconds before transfering it to a serving plate.')," +
                "(10,'Tteokbokki', 4, 'tteokbokki', '1) Add the water, dried anchovies, and dried kelp to a shallow pot or pan.\\n2) Boil for 15 minutes over medium high heat without the lid.\\n3) Combine hot pepper paste, hot pepper flakes, and sugar in a small bowl. Remove the anchovies and kelp from the pot and add the rice cake, the mixture in the bowl, the green onion, and the optional fish cakes and hard boiled eggs. The stock will be about 2 ½ cups.\\n4) Stir gently with a wooden spoon when it starts to boil. Keep stirring until the rice cake turns soft and the sauce thickens and looks shiny, which should take about 10 -15 minutes. If the rice cake is not soft enough, add more water and continue stirring until soften. When you use freshly made rice cake, it takes shorter time. If you use frozen rice cake, thaw it out and soak in cold water to soften it before cooking.');";

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
                "(3,9,2,'spoon',1),(3,10,500,'Gms',1),(3,11,1,'cup',1),(3,12,2,'piece',1),(3,13,2,'spoon',1),(3,3,1,'unit',2),(3,2,1,'unit',2)," +
                "(4,8,2,'cup',1),(4,5,2,'',1),(4,13,1,'cup',1),(4,2,1,'unit',2)," +
                "(5,9,2,'spoon',1),(5,10,500,'Gms',1),(5,3,1,'bowl',1),(5,11,1,'cup',1),(5,3,1,'unit',2)," +
                "(6,10,500,'Gms',1),(6,11,1,'cups',1),(6,8,1,'cups',1),(6,1,1,'unit',2)," +
                "(7,9,3,'spoon',1),(7,7,2,'spoon',1),(7,13,3,'spoon',1),(7,12,2,'spoon',1),(7,1,1,'unit',2)," +
                "(8,8,2,'cups',1),(8,9,3,'spoon',1),(8,10,500,'Gms',1),(8,1,1,'unit',2)," +
                "(9,1,1,'cups',1),(9,7,2,'cups',1),(9,9,2,'spoons',1), (9,2,1,'unit',2)," +
                "(10,10,500,'Gms',1),(10,3,3,'cups',1),(10,7,2,'cups',1),(10,12,1,'cup',1),(10,1,1,'unit',2);";

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
                "(1,'Italian','italian'),(2,'Indian','indian'),(3,'Chinese','chinese'),(4,'Korean','korean');";

        return query;
    }
}
