package com.mis571_group_d.suchef.data.model;

/**
 * Created by rogelio on 12/7/16.
 */

public class Cuisine {
    /**
     */
    public static final String TABLE = "cuisines";

    /**
     * Table Columns names
     *
     */
    public static final String KEY_CUISINE_ID   = "cuisine_id";
    public static final String KEY_CUISINE_NAME = "name";
    public static final String KEY_CUISINE_IMAGE = "image";
    public static final String KEY_IS_DELETE    = "is_delete";

    /**
     * Defining Class Attributes
     *
     */
    private long mCuisineID;
    private String mCuisineName;
    private String mCuisineImage;
    private Boolean mIsDelete ;


    /**
     * Constructor
     *
     * @param cuisineId
     * @param cuisineName
     * @param cuisineImage
     */
    public Cuisine(long cuisineId, String cuisineName, String cuisineImage) {
        mCuisineID = cuisineId;
        mCuisineName = cuisineName;
        mCuisineImage = cuisineImage;
    }

    public Cuisine() {

    }

    /**
     * Defining Getter and Setter methods
     *
     */

    public long getId() {
        return mCuisineID;
    }

    public void setId(long CuisineId) {
        mCuisineID = CuisineId;
    }

    public String getCuisineName() {
        return mCuisineName;
    }

    public void setCuisineName(String name) {
        mCuisineName = name;
    }

    public String getCuisineImage() {
        return mCuisineImage;
    }

    public void setCuisineImage(String image) {
        mCuisineImage = image;
    }

    public Boolean getIsDelete() {
        return mIsDelete;
    }

}


