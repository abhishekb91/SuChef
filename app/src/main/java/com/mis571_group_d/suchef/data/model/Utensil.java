package com.mis571_group_d.suchef.data.model;

/**
 * Created by rogelio on 12/8/16.
 */

public class Utensil {
    /**
     */
    public static final String TABLE = "utensils";

    /**
     * Table Columns names
     *
     */
    public static final String KEY_UTENSIL_ID = "utensil_id";
    public static final String KEY_UTENSIL_NAME = "name";
    public static final String KEY_UTENSIL_IMAGE = "image";
    public static final String KEY_IS_DELETE = "is_delete";

    /**
     * Defining Class Attributes
     *
     */
    private long mUtensilID;
    private String mUtensilName;
    private String mUtensilImage;
    private Boolean mIsDelete;

    /**
     * Defining constructor
     *
     * @param id Utensil id
     * @param name Utensil name
     * @param image Utensil image
     */
    public Utensil(long id, String name, String image) {
        mUtensilID = id;
        mUtensilName = name;
        mUtensilImage = image;
    }

    /**
     * Defining Getter and Setter methods
     *
     */

    public long getId() {
        return mUtensilID;
    }

    public void setId(long UtensilsId) {
        mUtensilID = UtensilsId;
    }

    public String getUtensilName() {
        return mUtensilName;
    }

    public void setUtensilsName(String UtensilsName) {
        mUtensilName = UtensilsName;
    }

    public void setUtensilImage (String image) { mUtensilImage = image; }

    public String getUtensilImage () { return mUtensilImage; }

    public void setIsDelete (Boolean isDelete) { mIsDelete = isDelete; }

    public Boolean getIsDelete () { return mIsDelete; }
}
