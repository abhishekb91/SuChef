package com.mis571_group_d.suchef.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.data.SessionManager;
import com.mis571_group_d.suchef.data.model.Ingredient;
import com.mis571_group_d.suchef.data.model.Recipe;
import com.mis571_group_d.suchef.data.model.Utensil;
import com.mis571_group_d.suchef.data.repo.RecipeRepo;

import static com.mis571_group_d.suchef.R.id.recipe_preparation_method;

public class RecipeActivity extends AppCompatActivity {

    private static String TAG = RecipeActivity.class.getSimpleName().toString();

    TextView ingredientsTextView, utensilsTextView, preparationTextView;

    Button favouriteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        final long recipeId = (long) getIntent().getSerializableExtra("recipeId");

        final RecipeRepo recipeRepo = new RecipeRepo();

        final Recipe recipe = recipeRepo.recipeDetail(recipeId);

        //Getting Recipe Ingredients in a String
        String recipeIngredents = "";

        for (int i = 0; i < recipe.getRecipeIngredients().size(); i++) {
            Ingredient tempIng = (Ingredient) recipe.getRecipeIngredients().get(i);

            recipeIngredents += (i+1) + ") " + ((int) tempIng.getAmount()) + " " + tempIng.getUnit() + " of " + tempIng.getIngredientName() + "\n";
        }

        //Setting Ingredients in the TextView
        ingredientsTextView = (TextView) findViewById(R.id.recipe_ingredients);
        ingredientsTextView.setText(recipeIngredents);

        //Getting Recipe Ingredients in a String
        String recipeUtensils = "";

        for (int i = 0; i < recipe.getRecipeUtensils().size(); i++) {
            Utensil tempUtensils = (Utensil) recipe.getRecipeUtensils().get(i);

            recipeUtensils += (i+1) + ") " + ((int) tempUtensils.getAmount()) + " " + tempUtensils.getUtensilName() + "\n";
        }

        //Setting Ingredients in the TextView
        utensilsTextView = (TextView) findViewById(R.id.recipe_utensils);
        utensilsTextView.setText(recipeUtensils);


        //Setting Preparation Method
        preparationTextView = (TextView) findViewById(R.id.recipe_preparation_method);
        preparationTextView.setText(recipe.getPreparationMethod().replace("\\n", System.getProperty("line.separator")));

        //Getting user Id
        SessionManager sessionManager = new SessionManager(this);
        final long userId = sessionManager.getUserDetails();

        //Referencing Favourite Button
        favouriteBtn = (Button) findViewById(R.id.add_favourite);

        //Setting Text of Favourite Button
        favouriteBtn.setText((recipeRepo.checkRecipeFavourite(userId, recipeId)) ? "UNFAV" : "FAV");

        //Listening for favourite button click
        favouriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message;

                if( !recipeRepo.checkRecipeFavourite(userId, recipeId) ) {
                    //Save Recipe as user favourite
                    recipeRepo.addRecipeAsFaourite(userId, recipeId);

                    //Changing the Button Text
                    favouriteBtn.setText("UNFAV");

                    //Creating message to show on Snackbar
                    message = recipe.getRecipeName() + " added as favourite";
                } else {
                    //Remove Recipe as user favourite
                    recipeRepo.removeRecipeAsFaourite(userId, recipeId);

                    //Changing the Button Text
                    favouriteBtn.setText("FAV");

                    //Creating message to show on Snackbar
                    message = recipe.getRecipeName() + " removed as favourite";
                }

                //Make Snackbar
                Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();

            }
        });
    }
}
