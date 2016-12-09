package com.mis571_group_d.suchef.data.repo;

import com.mis571_group_d.suchef.data.model.UserFavo;

/**
 * Created by rogelio on 12/7/16.
 */

public class UserFavoRepo {

    public UserFavoRepo() {
    }

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTable() {
        String query = "CREATE TABLE " + UserFavo.TABLE + "("
                + UserFavo.KEY_USERID + " INTEGER,"
                + UserFavo.KEY_RECIPEID + " INTEGER,"
                + UserFavo.KEY_ADDEDDATE + " DATE,"
                + "PRIMARY KEY (" + UserFavo.KEY_USERID + ", " + UserFavo.KEY_RECIPEID + "),"
                + "FOREIGN KEY (" + UserFavo.KEY_USERID + ") REFERENCES users (" + UserFavo.KEY_USERID + "),"
                + "FOREIGN KEY (" + UserFavo.KEY_RECIPEID + ") REFERENCES recipes (" + UserFavo.KEY_RECIPEID + ");";

        return query;
    }
}
