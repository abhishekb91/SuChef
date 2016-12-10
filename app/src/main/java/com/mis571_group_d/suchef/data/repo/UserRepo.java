package com.mis571_group_d.suchef.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mis571_group_d.suchef.data.DatabaseManager;
import com.mis571_group_d.suchef.data.model.User;

/**
 * Created by abhishek on 11/29/2016.
 */

public class UserRepo {

    /**
     * Class name for Logging
     */
    private static String TAG = UserRepo.class.getSimpleName().toString();

    /**
     * Empty Constructor
     */
    public UserRepo() {
    }

    /**
     * Table creation query
     *
     * @return create table query
     */
    public static String createTable() {
        String query = "CREATE TABLE " + User.TABLE + "("
                + User.KEY_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + User.KEY_USERNAME + " VARCHAR(100) NOT NULL,"
                + User.KEY_PASSWORD + " VARCHAR(100) NOT NULL,"
                + User.KEY_GENDER + " INTEGER,"
                + User.KEY_DOB + " DATE,"
                + User.KEY_IS_DELETE + " INT(1) DEFAULT 0 ); ";

        return query;
    }

    /**
     * Function to register user to the application
     *
     * @param user User object containing username and password
     * @return userId after he has been registered
     */
    public long registration(User user) {
        long userId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(User.KEY_USERNAME, user.getUsername());
        values.put(User.KEY_PASSWORD, user.getPassword());

        // Inserting Row
        userId = db.insert(User.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return userId;
    }

    /**
     * Function to return check if user is valid or not
     *
     * @param username Username of the user
     * @param password Password of the user
     * @return user Id if valid, else -1 if invalid
     */
    public long login(String username, String password) {

        long userId = -1;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = " SELECT u." + User.KEY_USERID +
                " FROM " + User.TABLE + " as u" +
                " WHERE u." + User.KEY_USERNAME + " = '" + username + "'" +
                " AND u." + User.KEY_PASSWORD + " = '" + password + "'";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    userId = cursor.getLong(cursor.getColumnIndex(User.KEY_USERID));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while getting user information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();
        return userId;
    }

    /**
     * This function is used to update the user profile
     *
     * @param user
     * @return true if the profile was updated successfully, else return false
     */
    public Boolean updateProfile(User user) {
        Boolean result = true;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues data = new ContentValues();

        data.put(User.KEY_DOB, user.getDob());
        data.put(User.KEY_GENDER, user.getGender());

        int noRows = db.update(User.TABLE, data, User.KEY_USERID + " = " + user.getId(), null);

        // Inserting Row
        DatabaseManager.getInstance().closeDatabase();

        //Check if update was successfull and return true or false
        return (noRows == 1) ? true : false;
    }

    public void getUserDetails() {

        String userId = "";
        int gender = 0;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = " SELECT u.*" +
                " FROM " + User.TABLE + " as u" +
                " WHERE u."+User.KEY_USERID+"=3";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    userId = cursor.getString(cursor.getColumnIndex(User.KEY_DOB));
                    gender = cursor.getInt(cursor.getColumnIndex(User.KEY_GENDER));

                    Log.i("User", "dob = " + userId + ", gender = " + gender);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while getting user information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();
        return;
    }
}
