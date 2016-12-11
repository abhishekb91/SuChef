package com.mis571_group_d.suchef.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.mis571_group_d.suchef.R;
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

        GridView gridView = (GridView) view.findViewById(R.id.cuisine_grid);

        gridView.setAdapter(CuisineAdapter);

        // Inflate the layout for this fragment
        return view;
    }

}
