package com.mis571_group_d.suchef.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.adapter.RecipeAdapter;
import com.mis571_group_d.suchef.data.model.Cuisine;
import com.mis571_group_d.suchef.data.repo.CuisineRepo;

import java.util.ArrayList;

public class CuisineRecipesActivity extends AppCompatActivity {

    public static String TAG = CuisineRecipesActivity.class.getSimpleName().toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_favourite);

        long cuisineId = (long) getIntent().getSerializableExtra("cuisineId");


        //Create User Repo Object
        CuisineRepo cuisineRepo = new CuisineRepo();

        Cuisine cuisineInfo = cuisineRepo.cuisineInfo(cuisineId);

        //Setting Cuisine Name in Action Bar
        setTitle(cuisineInfo.getCuisineName());

        //Getting list of all ingredients
        ArrayList recipes = cuisineRepo.getCuisineRecipes(cuisineId);

        RecipeAdapter recipeAdapter = new RecipeAdapter(this, recipes);
        ListView listView = (ListView) findViewById(R.id.user_favourite);

        //Assigning the adaptor to the list view
        listView.setAdapter(recipeAdapter);

        //Redirect user to recipe detail screen when he clicks the recipe
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplication(), RecipeActivity.class);
                i.putExtra("recipeId", id);
                startActivity(i);
            }
        });

    }
}
