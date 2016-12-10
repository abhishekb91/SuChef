package com.mis571_group_d.suchef.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.adapter.RecipeAdaptor;
import com.mis571_group_d.suchef.data.SessionManager;
import com.mis571_group_d.suchef.data.model.User;
import com.mis571_group_d.suchef.data.repo.FavouriteRepo;
import com.mis571_group_d.suchef.data.repo.UserRepo;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {

    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        SessionManager sessionManager = new SessionManager(getActivity());
        //Create User object
        User user = new User();

        //Getting User id from session manager and saving it in user object
        user.setId(sessionManager.getUserDetails());

        //Create User Repo Object
        FavouriteRepo favRepo = new FavouriteRepo();

        //Getting list of all ingredients
        ArrayList recipes = favRepo.getUserFavourites(user);

        RecipeAdaptor recipeAdaptor = new RecipeAdaptor(getActivity(), recipes);
        ListView listView = (ListView) view.findViewById(R.id.user_favourite);

        //Assigning the adaptor to the list view
        listView.setAdapter(recipeAdaptor);

        return view;
    }

}
