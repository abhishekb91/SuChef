package com.mis571_group_d.suchef.data.repo;

import com.mis571_group_d.suchef.data.model.Cuisines;
import com.mis571_group_d.suchef.data.model.UserFavo;

/**
 * Created by rogelio on 12/7/16.
 */

public class CuisinesRepo {

    public CuisinesRepo() {
    }

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTable() {
        String query = "CREATE TABLE " + Cuisines.TABLE + "("
                + Cuisines.KEY_CUISINESID + " INTEGER PRIMARY KET AUTOINCREMENT,"
                + Cuisines.KEY_CUISINESName + " TEXT NOT NULL,"
                + Cuisines.KEY_IS_DELETE + " INT(1) DEFAULT 0 ); ";

        return query;
    }
}
