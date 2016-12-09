package com.mis571_group_d.suchef.data.repo;


import com.mis571_group_d.suchef.data.model.Utensils;

/**
 * Created by rogelio on 12/8/16.
 */

public class UtensilsRepo {
    public UtensilsRepo() {
    }

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTable() {
        String query = "CREATE TABLE " + Utensils.TABLE + "("
                + Utensils.KEY_UTENSILSID + " INTEGER PRIMARY KET AUTOINCREMENT,"
                + Utensils.KEY_UTENSILSNAME + " VARCHAR(100),";

        return query;
    }
}
//url=ssh://git@github.com/abhishekb91/SuChef.git