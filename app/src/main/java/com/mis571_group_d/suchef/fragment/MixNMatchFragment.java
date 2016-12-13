package com.mis571_group_d.suchef.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;


import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.activity.SearchResultActivity;
import com.mis571_group_d.suchef.adapter.IngredientAdapter;
import com.mis571_group_d.suchef.adapter.UtensilAdapter;
import com.mis571_group_d.suchef.data.model.Ingredient;
import com.mis571_group_d.suchef.data.repo.IngredientRepo;
import com.mis571_group_d.suchef.data.repo.UtensilsRepo;


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

    private Boolean mSearchExactRecipe = false;

    private ArrayList mSelectedIngredients, mSelectedUtensils;

    public MixNMatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mix_nmatch, container, false);

        //Creating ingredientRepo object
        IngredientRepo ingRepo = new IngredientRepo();

        //Getting list of all ingredients
        final ArrayList<Ingredient> ingredients = ingRepo.getIngredients();

        //Initializing Ingredients ArrayList
        mSelectedIngredients = new ArrayList<>();

        //Passing ingredient values to IngredientAdapter
        final IngredientAdapter ingredientAdapter = new IngredientAdapter(getActivity(), ingredients);
        final GridView gridView = (GridView) view.findViewById(R.id.ingredient_grid);

        gridView.setSelector(R.drawable.item_selector);

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

                } else {
                    //If ingredient is not selected, add it to ArrayList
                    mSelectedIngredients.add(id);
                }
                ingredients.get(position).setSelected(!isSelected);
                ingredientAdapter.notifyDataSetChanged();
            }
        });

        //Creating ingredientRepo object
        UtensilsRepo utenRepo = new UtensilsRepo();

        //Getting list of all ingredients
        ArrayList utensils = utenRepo.getUtensils();

        //Initializing Ingredients ArrayList
        mSelectedUtensils = new ArrayList<>();

        //Passing ingredient values to IngredientAdapter
        UtensilAdapter materialAdapterUtensil = new UtensilAdapter(getActivity(), utensils);
        GridView utensilGridView = (GridView) view.findViewById(R.id.utensils_grid);


        //Assigning the adaptor to the grid view
        utensilGridView.setAdapter(materialAdapterUtensil);


        //setting the onClick event
        utensilGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Checking if user have previously selected an ingredient or not
                boolean isSelected = mSelectedUtensils.contains(id);

                if(isSelected) {
                    //If ingredient is selected, remove it from ArrayList
                    mSelectedUtensils.remove(mSelectedUtensils.indexOf(id));
                    view.setBackgroundColor(0x00000000);
                } else {
                    //If ingredient is not selected, add it to ArrayList
                    mSelectedUtensils.add(id);
                    view.setBackgroundColor(0xFF00FFFF);
                }
            }
        });


        Switch SearchExactRecipe = (Switch) view.findViewById(R.id.search_matching_recipe);
        SearchExactRecipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSearchExactRecipe = (isChecked) ? true : false;
            }
        });

        //Search the recipe from the ingredient selected
        mSearchButton = (Button) view.findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if user has selected an ingredient or not
                if(!mSelectedIngredients.isEmpty()) {
                    Intent i = new Intent(getActivity(), SearchResultActivity.class);
                    i.putExtra("searchExactRecipe", mSearchExactRecipe);
                    i.putExtra("selectedIngredient", mSelectedIngredients);
                    i.putExtra("selectedUtensils", mSelectedUtensils);
                    startActivity(i);
                } else {
                    Toast.makeText(getActivity(),"Please select atleast one ingredient",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
