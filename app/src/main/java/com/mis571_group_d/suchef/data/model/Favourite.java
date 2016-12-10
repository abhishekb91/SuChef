package com.mis571_group_d.suchef.data.model;

import java.util.Date;

/**
 * Created by rogelio on 12/7/16.
 */

public class Favourite {

    /**
     */
    public static final String TABLE = "user_favourites";

    /**
     * Table Columns names
     *
     */
    public static final String KEY_USER_ID       = "user_id";
    public static final String KEY_RECIPE_ID     = "recipe_id";
    public static final String KEY_ADDED_DATE    = "added_date";

    /**
     * Defining Class Attributes
     *
     */
    private long mUserId;
    private long mRecipeID;
    private Date mAddedDate ;

    /**
     * Defining Getter and Setter methods
     *
     */

    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long user_id) {
        mUserId = user_id;
    }

    public long getRecipeID() {
        return mRecipeID;
    }

    public void setRecipeID(long recipeID) {
        mRecipeID = recipeID;
    }

    public Date getAddedDate() {
        return mAddedDate;
    }

    public void setAddedDate(Date addedDate) {
        mAddedDate = addedDate;
    }

}

