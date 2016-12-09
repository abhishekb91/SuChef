package com.mis571_group_d.suchef.data.model;

/**
 * Created by abhishek on 12/7/2016.
 */

public class Recipe {
    /**
     * Class name to use in Log
     */
    public static final String TAG = Recipe.class.getSimpleName();

    /**
     * Table name
     *
     */
    public static final String TABLE = "recipes";

    /**
     * Table Columns names
     *
     */
    public static final String KEY_RECIPE_ID = "recipe_id";
    public static final String KEY_CUSINE_ID = "cuisine_id";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_NAME = "name";
    public static final String KEY_PREPARATION_METHOD = "preparation_method";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_IS_DELETE = "is_delete";

    /**
     * Defining Class Attributes
     *
     */
    private long mId ;
    private long mCuisineId;
    private String mRecipeName;
    private String mPreparationMethod;
    private String mImage;
    private Boolean mIsDelete;


    /**
     * Constructor
     */
    public Recipe() {
    }


    /**
     * Defining Getter and Setter methods
     *
     */

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getCuisineId() {
        return mCuisineId;
    }

    public void setCuisineId(long cuisineId) {
        mCuisineId = cuisineId;
    }


    public void setName(String name) {
        mRecipeName = name;
    }

    public String getPreparationMethod() {
        return mPreparationMethod;
    }

    public void setPreparationMethod(String preparationMethod) {
        mPreparationMethod = preparationMethod;
    }

    public String getIngredientImage() {
        return mImage;
    }

    public void setIngredientImage(String ingredientName) {
        mImage = ingredientName;
    }

    public Boolean getIsDelete() {
        return mIsDelete;
    }
}
