package com.mis571_group_d.suchef.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.adapter.IngredientAdapter;
import com.mis571_group_d.suchef.data.repo.IngredientRepo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MixNMatchFragment extends Fragment {


    public MixNMatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mix_nmatch, container, false);
        //Creating ingredientRepo object
        IngredientRepo ingRepo = new IngredientRepo();

        //Getting list of all ingredients
        ArrayList ingredients = new ArrayList<>();
        ingredients = ingRepo.getIngredients();


        //Passing ingredient values to IngredientAdapter
        IngredientAdapter ingredientAdapter = new IngredientAdapter(getActivity(), ingredients);
        GridView gridView = (GridView) view.findViewById(R.id.ingredient_grid);

        gridView.setAdapter(ingredientAdapter);

        // Inflate the layout for this fragment
        return view;
    }

}
