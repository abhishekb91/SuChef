package com.mis571_group_d.suchef.data.model;

import java.util.Date;

/**
 * Created by rogelio on 12/7/16.
 */

public class Cuisines {
    /**
     */
    public static final String TABLE = "cuisines";

    /**
     * Table Columns names
     *
     */
    public static final String KEY_CUISINESID   = "cuisines_id";
    public static final String KEY_CUISINESName = "name";
    public static final String KEY_IS_DELETE    = "is_delete";

    /**
     * Defining Class Attributes
     *
     */
    private long mCuisineID;
    private String mCuisineName;
    private Boolean mIsDelete ;

    /**
     * Defining Getter and Setter methods
     *
     */

    public long getCuisineId() {
        return mCuisineID;
    }

    public void setCuisineId(long CuisineId) {
        mCuisineID = CuisineId;
    }

    public String getCuisineName() {
        return mCuisineName;
    }

    public void setCuisineName(String name) {
        mCuisineName = name;
    }

    public Boolean getIsDelete() {
        return mIsDelete;
    }

}


