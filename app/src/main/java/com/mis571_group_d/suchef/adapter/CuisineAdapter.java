
package com.mis571_group_d.suchef.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.data.model.Cuisine;

import java.util.ArrayList;

/**
 * Created by user on 16/12/7.
 */

public class CuisineAdapter extends ArrayAdapter<Cuisine> {

    private ArrayList<Cuisine> mCusisine;

    private Context mContext;

    public CuisineAdapter(Activity context, ArrayList<Cuisine> cuisine) {
        super(context, 0, cuisine);

        mCusisine = cuisine;

        mContext = context;
    }

    @Override
    public long getItemId(int position) {
        return mCusisine.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listCuisineView = convertView;

        if (listCuisineView == null) {
            listCuisineView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_cuisine, parent, false
            );
        }

        //Get current reference ingredient
        Cuisine currentCuisine = getItem(position);

        //Setting the ingredient name to the grid
        TextView cuisineName = (TextView) listCuisineView.findViewById(R.id.cuisine_name);
        cuisineName.setText(currentCuisine.getCuisineName());

        //Getting image's resource id
        int resourceId = mContext.getResources().getIdentifier("drawable/"+ currentCuisine.getCuisineImage(), null, mContext.getPackageName());

        //Setting the image to the grid
        ImageView imageView = (ImageView) listCuisineView.findViewById(R.id.cuisine_image);
        imageView.setImageResource(resourceId);

        //returning the Ingredient list
        return listCuisineView;
    }
}
