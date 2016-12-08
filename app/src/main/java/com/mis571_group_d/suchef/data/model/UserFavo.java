package com.mis571_group_d.suchef.data.model;

import java.util.Date;

/**
 * Created by rogelio on 12/7/16.
 */

public class UserFavo {

    /**
     */
    public static final String TABLE = "user_favorite";

    /**
     * Table Columns names
     *
     */
    public static final String KEY_USERID       = "user_id";
    public static final String KEY_RECIPEID     = "recipe_id";
    public static final String KEY_ADDEDDATE     = "added_date";

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

    public long getRecipeID() { return mRecipeID; }

    public void setRecipeID(long recipeID) { mRecipeID = recipeID;}

    public Date getAddedDate() { return mAddedDate; }

    public void setAddedDate(Date addedDate) { mAddedDate = addedDate; }

}

