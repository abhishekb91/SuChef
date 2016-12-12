package com.mis571_group_d.suchef.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.data.DBHelper;
import com.mis571_group_d.suchef.data.DatabaseManager;
import com.mis571_group_d.suchef.data.model.Ingredient;
import com.mis571_group_d.suchef.data.model.Recipe;
import com.mis571_group_d.suchef.data.repo.IngredientRepo;
import com.mis571_group_d.suchef.data.repo.RecipeRepo;
import com.mis571_group_d.suchef.data.repo.UtensilsRepo;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    private static String TAG = RecipeActivity.class.getSimpleName().toString();
    ImageView recipe_coverImage;
    TextView recipe_name, recipe_description,recipe_preparation_method;
    Button add_favourite;
    List<Recipe> recipeDetailList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        long recipeId = (long) getIntent().getSerializableExtra("recipeId");
        recipeDetailList = new ArrayList<Recipe>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Log.i(TAG, "recipeID = " + recipeId);
        Cursor cursor = db.query("recipes", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndex(Recipe.KEY_RECIPE_ID));
            String name = cursor.getString(cursor.getColumnIndex(Recipe.KEY_NAME));
            String image = cursor.getString(cursor.getColumnIndex(Recipe.KEY_IMAGE));
            Boolean isUserFavourite = cursor.getInt(cursor.getColumnIndex(Recipe.KEY_IS_DELETE)) > 0;
            // TODO: 12/12/16 KEY_IS_FAVOURITE 
            Recipe r = new Recipe(id,name,image,isUserFavourite);
            recipeDetailList.add(r);
        }

        recipe_coverImage=(ImageView)this.findViewById(R.id.recipe_coverImage);
        recipe_name=(TextView)this.findViewById(R.id.recipe_name);
        recipe_description=(TextView)this.findViewById(R.id.recipe_description);
        recipe_preparation_method=(TextView)this.findViewById(R.id.recipe_preparation_method);
        add_favourite=(Button)this.findViewById(R.id.add_favourite);


//        recipe_coverImage.setImageAlpha(recipe_coverImage.);
        recipe_name.setText(recipe_name.toString());
        recipe_description.setText(recipe_description.toString());


    }

}
