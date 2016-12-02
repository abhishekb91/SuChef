package com.mis571_group_d.suchef.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by abhishek on 11/29/2016.
 */

public class SessionManager {

    // Shared Preferences
    private SharedPreferences pref;

    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Context
    private Context _context;

    // Shared pref mode
    private int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "suChefPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User id
    private static final String KEY_USER_ID = "userId";


    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(Long userId){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putLong(KEY_USER_ID, userId);

        // commit changes
        editor.commit();
    }

    /**
     * This method will check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public Boolean checkLogin(){
        // Check login status
        if(this.isLoggedIn()){
            return true;
        } else {
            return false;
        }

    }



    /**
     * Get stored session data
     * */
    /*public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }*/
    public long getUserDetails(){

        long userId = pref.getLong(KEY_USER_ID, -1);

        // return user
        return userId;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
