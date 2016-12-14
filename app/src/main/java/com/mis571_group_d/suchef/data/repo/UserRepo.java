package com.mis571_group_d.suchef.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mis571_group_d.suchef.data.DatabaseManager;
import com.mis571_group_d.suchef.data.model.User;

import static android.R.attr.id;

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
                + User.KEY_USERNAME + " TEXT NOT NULL,"
                + User.KEY_PASSWORD + " TEXT NOT NULL,"
                + User.KEY_GENDER + " TEXT,"
                + User.KEY_DOB + " DATE,"
                + User.KEY_IS_DELETE + " INTEGER DEFAULT 0 ); ";

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
     * This function is used to update the user's password
     *
     * @param user
     * @param currentPassword
     * @return
     */
    public int updatePassword(User user, String currentPassword) {

        int status = 0;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();


        String query = "SELECT * FROM users " +
                " WHERE user_id=" + user.getId() + "" +
                " AND password='" + currentPassword + "';";

        Log.d(TAG, query);
        Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.getCount() == 1) {

                String updateQuery = " UPDATE `users` " +
                        " SET `password`= '" + user.getPassword() + "'" +
                        " WHERE `user_id`='" + user.getId() + "';";

                db.execSQL(updateQuery);

                status = 1;
            } else {
                status = 2;
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while getting user information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        return status;
    }

    /**
     * This function is used for getting user profile details
     *
     * @param userId is the User Id
     * @return User object
     */
    public User userProfile(long userId){

        User user = new User();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = " SELECT u.* " +
                " FROM " + User.TABLE + " as u" +
                " WHERE u." + User.KEY_USERID + " = '" + userId + "';";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    //Setting up user's date of birth
                    user.setDob(cursor.getString(cursor.getColumnIndex(User.KEY_DOB)));

                    //Setting up user's gender
                    user.setGender(cursor.getInt(cursor.getColumnIndex(User.KEY_GENDER)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while getting user information");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().closeDatabase();
        }

        cursor.close();

        return user;
    }
    /**
     * This function is used to update the user's profile
     *
     * @param user
     * @return
     */
    public int updateProfile(User user) {

        long userId = -1;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        //Creating ContentValue
        ContentValues cv = new ContentValues();

        cv.put("date_of_birth",user.getDob());
        cv.put("gender",user.getGender());

        int noOfRows = db.update("users", cv, "user_id="+user.getId(), null);

        return noOfRows;
    }
}
