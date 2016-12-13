package com.mis571_group_d.suchef.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.activity.CuisineRecipesActivity;
import com.mis571_group_d.suchef.adapter.CuisineAdapter;
import com.mis571_group_d.suchef.data.repo.CuisineRepo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Creating CuisineRepo object
        CuisineRepo ingRepo = new CuisineRepo();

        //Getting list of all cuisines
        ArrayList cuisines = ingRepo.getCuisines();

        //Passing ingredient values to CuisineAdapter
        CuisineAdapter CuisineAdapter = new CuisineAdapter(getActivity(), cuisines);

        ListView cuisineList = (ListView) view.findViewById(R.id.cuisine_list);

        cuisineList.setAdapter(CuisineAdapter);

        //Setting on click method
        cuisineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), CuisineRecipesActivity.class);
                i.putExtra("cuisineId", id);
                startActivity(i);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
