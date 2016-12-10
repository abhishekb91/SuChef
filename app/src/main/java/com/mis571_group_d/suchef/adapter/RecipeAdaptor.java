package com.mis571_group_d.suchef.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.data.model.Ingredient;
import com.mis571_group_d.suchef.data.model.Recipe;

import java.util.ArrayList;

/**
 * Created by abhishek on 12/10/2016.
 */

public class RecipeAdaptor extends ArrayAdapter<Recipe> {

    private Context mContext;

    private ArrayList<Recipe> mRecipes;

    public RecipeAdaptor(Activity context, ArrayList<Recipe> recipes) {
        super(context, 0, recipes);

        mContext = context;

        mRecipes = recipes;
    }

    @Override
    public long getItemId(int position) {
        return mRecipes.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listRecipeView  = convertView;

        if(listRecipeView  == null) {
            listRecipeView = LayoutInflater.from(getContext()).inflate(R.layout.list_recipe, parent, false);
        }

        //Get current reference ingredient
        Recipe currentRecipe = getItem(position);

        //Assigning Recipe name
        TextView recipeName = (TextView) listRecipeView.findViewById(R.id.recipe_name);
        recipeName.setText(currentRecipe.getRecipeName());

        //Getting the image of recipe from database
        int resourceId = mContext.getResources().getIdentifier("drawable/"+ currentRecipe.getRecipeImage(), null, mContext.getPackageName());

        //Assigning Recipe Image
        ImageView recipeImage = (ImageView) listRecipeView.findViewById(R.id.recipe_image);
        recipeImage.setImageResource(resourceId);


        return listRecipeView;
    }
}
