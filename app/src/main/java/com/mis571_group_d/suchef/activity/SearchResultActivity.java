package com.mis571_group_d.suchef.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.adapter.RecipeAdapter;
import com.mis571_group_d.suchef.data.repo.RecipeRepo;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {

    private static String TAG = SearchResultActivity.class.getSimpleName().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        ArrayList<Long> ingredients = (ArrayList<Long>) getIntent().getSerializableExtra("selectedIngredient");

        ArrayList<Long> utensils = (ArrayList<Long>) getIntent().getSerializableExtra("selectedUtensils");

        Boolean isExactRecipe = (Boolean) getIntent().getSerializableExtra("searchExactRecipe");

        RecipeRepo repo = new RecipeRepo();

        ArrayList recipes;

        //Check if user has selected Exact Recipe or not
        if(isExactRecipe){
            //searching extensively if recipe contains all ingredients or not
            recipes = repo.recipeSearchResultExtensive(ingredients, utensils);
        } else {
            //Searching if recipe contains all the ingredients or not
            recipes = repo.recipeSearchResult(ingredients, utensils);
        }

        RecipeAdapter recipeAdapter = new RecipeAdapter(this, recipes);
        ListView listView = (ListView) findViewById(R.id.results_found_list);

        //Assigning the adaptor to the list view
        listView.setAdapter(recipeAdapter);

        //Redirect user to recipe detail screen when he clicks the recipe
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), RecipeActivity.class);
                i.putExtra("recipeId", id);
                startActivity(i);
            }
        });
    }
}
