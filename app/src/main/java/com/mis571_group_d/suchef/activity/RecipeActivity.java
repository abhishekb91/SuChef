package com.mis571_group_d.suchef.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mis571_group_d.suchef.R;

public class RecipeActivity extends AppCompatActivity {

    private static String TAG = RecipeActivity.class.getSimpleName().toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        long recipeId = (long) getIntent().getSerializableExtra("recipeId");

        Log.i(TAG, "recipeID = " + recipeId);
    }
}
