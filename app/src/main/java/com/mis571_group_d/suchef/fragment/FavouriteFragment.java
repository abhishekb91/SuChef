package com.mis571_group_d.suchef.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.activity.RecipeActivity;
import com.mis571_group_d.suchef.adapter.RecipeAdapter;
import com.mis571_group_d.suchef.data.SessionManager;
import com.mis571_group_d.suchef.data.model.User;
import com.mis571_group_d.suchef.data.repo.FavouriteRepo;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {

    private View mView;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        SessionManager sessionManager = new SessionManager(getActivity());
        //Create User object
        User user = new User();

        //Getting User id from session manager and saving it in user object
        user.setId(sessionManager.getUserDetails());

        //Create User Repo Object
        FavouriteRepo favRepo = new FavouriteRepo();

        //Getting list of all ingredients
        ArrayList recipes = favRepo.getUserFavourites(user);

        RecipeAdapter recipeAdapter = new RecipeAdapter(getActivity(), recipes);
        ListView listView = (ListView) mView.findViewById(R.id.user_favourite);

        //Assigning the adaptor to the list view
        listView.setAdapter(recipeAdapter);

        //Redirect user to recipe detail screen when he clicks the recipe
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), RecipeActivity.class);
                i.putExtra("recipeId", id);
                startActivity(i);
            }
        });

        Log.e("Favourite", "On Resume");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_favourite, container, false);

        return mView;
    }

}
