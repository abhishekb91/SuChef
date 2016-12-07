package com.mis571_group_d.suchef.data.model;

/**
 * Created by abhishek on 12/6/2016.
 */

public class Ingredient {
    /**
     * Class name to use in Log
     */
    public static final String TAG = Ingredient.class.getSimpleName();

    /**
     * Table name
     *
     */
    public static final String TABLE = "ingredients";

    /**
     * Table Columns names
     *
     */
    public static final String KEY_INGREDIENTID = "ingredient_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_CALORIE = "calorie";
    public static final String KEY_PROTIEN = "protein";
    public static final String KEY_CARBOHYDRATE = "carbohydrate";
    public static final String KEY_FAT = "fat";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_IS_DELETE = "is_delete";

    /**
     * Defining Class Attributes
     *
     */
    private long mId ;
    private String mIngredientsName;
    private float mCalorie;
    private float mProtien ;
    private float mCarbohydrate;
    private float mFat;
    private String mImage;
    private Boolean mIsDelete;


    /**
     * Constructor
     */
    public Ingredient() {
    }

    public Ingredient(long id, String ingredientName, String ingredientImage) {
        mId = id;
        mIngredientsName = ingredientName;
        mImage = ingredientImage;
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

    public String getIngredientName() {
        return mIngredientsName;
    }

    public void setIngredientName(String ingredientName) {
        mIngredientsName = ingredientName;
    }

    public float getCalorie() {
        return mCalorie;
    }

    public void setCalorie(float calorie) {
        mCalorie = calorie;
    }

    public void setProtien(float protien) {
        mProtien = protien;
    }

    public float getProtien() {
        return mProtien;
    }

    public void setCarbohydrate(float carbohydrate) {
        mCarbohydrate = carbohydrate;
    }

    public float getCarbohydrate() {
        return mCarbohydrate;
    }

    public void setFat(float fat) {
        mFat = fat;
    }

    public float getFat() {
        return mFat;
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
