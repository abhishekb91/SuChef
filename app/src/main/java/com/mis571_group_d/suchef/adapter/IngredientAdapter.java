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
import com.mis571_group_d.suchef.data.model.Ingredient;

import java.util.ArrayList;

import static com.mis571_group_d.suchef.R.id.imageView;

/**
 * Created by abhishek on 12/7/2016.
 */

public class IngredientAdapter extends ArrayAdapter<Ingredient>{

    private Context mContext;

    public IngredientAdapter(Activity context, ArrayList<Ingredient> ingredient) {
        super(context, 0, ingredient);

        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listIngredientView  = convertView;

        if(listIngredientView  == null) {
            listIngredientView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_ingredients, parent, false
            );
        }

        //Get current reference ingredient
        Ingredient currentIngredient = getItem(position);

        //Setting the ingredient name to the grid
        TextView ingredientName = (TextView) listIngredientView.findViewById(R.id.ingredient_name);
        ingredientName.setText(currentIngredient.getIngredientName());

        //Getting image's resource id
        int resourceId = mContext.getResources().getIdentifier("drawable/"+ currentIngredient.getIngredientImage(), null, mContext.getPackageName());
        int butter = R.drawable.butter;

        //Setting the image to the grid
        ImageView imageView = (ImageView) listIngredientView.findViewById(R.id.ingredient_image);
        imageView.setImageResource(resourceId);

        //returning the Ingredient list
        return listIngredientView;
    }
}
