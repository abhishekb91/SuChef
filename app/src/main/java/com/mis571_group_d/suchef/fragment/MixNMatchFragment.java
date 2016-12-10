package com.mis571_group_d.suchef.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.Switch;


import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.activity.RecipeActivity;
import com.mis571_group_d.suchef.activity.SearchResultActivity;
import com.mis571_group_d.suchef.adapter.IngredientAdapter;
import com.mis571_group_d.suchef.data.repo.IngredientRepo;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MixNMatchFragment extends Fragment {

    /**
     * Class name for Logging
     */
    private static String TAG = MixNMatchFragment.class.getSimpleName().toString();

    private Button mSearchButton;

    private Boolean mSearchExactRecipe;

    private ArrayList mSelectedIngredients;

    public MixNMatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mix_nmatch, container, false);

        //Creating ingredientRepo object
        IngredientRepo ingRepo = new IngredientRepo();

        //Getting list of all ingredients
        ArrayList ingredients = ingRepo.getIngredients();

        //Initializing Ingredients ArrayList
        mSelectedIngredients = new ArrayList<>();

        //Passing ingredient values to IngredientAdapter
        IngredientAdapter ingredientAdapter = new IngredientAdapter(getActivity(), ingredients);
        GridView gridView = (GridView) view.findViewById(R.id.ingredient_grid);

        Switch SearchExactRecipe = (Switch) view.findViewById(R.id.search_matching_recipe);
        SearchExactRecipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSearchExactRecipe = (isChecked) ? true : false;
            }
        });

        //Assigning the adaptor to the grid view
        gridView.setAdapter(ingredientAdapter);

        //setting the onClick event
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Checking if user have previously selected an ingredient or not
                boolean isSelected = mSelectedIngredients.contains(id);

                if(isSelected) {
                    //If ingredient is selected, remove it from ArrayList
                    mSelectedIngredients.remove(mSelectedIngredients.indexOf(id));
                    view.setBackgroundColor(0x00000000);
                } else {
                    //If ingredient is not selected, add it to ArrayList
                    mSelectedIngredients.add(id);
                    view.setBackgroundColor(0xFF00FFFF);
                }
            }
        });

        //Search the recipe from the ingredient selected
        mSearchButton = (Button) view.findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), SearchResultActivity.class);
                i.putExtra("searchExactRecipe", mSearchExactRecipe);
                i.putExtra("selectedIngredient", mSelectedIngredients);
                startActivity(i);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
