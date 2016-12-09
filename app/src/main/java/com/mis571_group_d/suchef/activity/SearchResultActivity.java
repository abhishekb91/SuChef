package com.mis571_group_d.suchef.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mis571_group_d.suchef.R;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {

    private static String TAG = SearchResultActivity.class.getSimpleName().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        ArrayList<Long> ingredients = (ArrayList<Long>) getIntent().getSerializableExtra("selectedIngredient");

        Boolean isExactRecipe = (Boolean) getIntent().getSerializableExtra("searchExactRecipe");

        Log.i(TAG, "inside");
    }
}
