package com.mis571_group_d.suchef.data.model;

/**
 * Created by rogelio on 12/8/16.
 */

public class Utensils {
    /**
     */
    public static final String TABLE = "utensils";

    /**
     * Table Columns names
     *
     */
    public static final String KEY_UTENSILSID   = "utensils_id";
    public static final String KEY_UTENSILSNAME = "name";

    /**
     * Defining Class Attributes
     *
     */
    private long mUtensilsID;
    private long mUtensilsName;

    /**
     * Defining Getter and Setter methods
     *
     */

    public long getUtensilsId() {
        return mUtensilsID;
    }

    public void setUtensilsId(long UtensilsId) {
        mUtensilsID = UtensilsId;
    }

    public long getUtensilsName() {
        return mUtensilsName;
    }

    public void setUtensilsName(long UtensilsName) {
        mUtensilsName = UtensilsName;
    }
}
