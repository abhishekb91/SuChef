package com.mis571_group_d.suchef.data.model;

/**
 * Created by abhishek on 11/29/2016.
 */

public class User {

    /**
     * Class name to use in Log
     */
    public static final String TAG = User.class.getSimpleName();

    /**
     * Table name
     *
     */
    public static final String TABLE = "users";

    /**
     * Table Columns names
     *
     */
       public static final String KEY_USERID       = "user_id";
    public static final String KEY_USERNAME     = "username";
    public static final String KEY_PASSWORD     = "password";
    public static final String KEY_GENDER       = "gender";
    public static final String KEY_DOB          = "date_of_birth";
    public static final String KEY_IS_DELETE    = "is_delete";

    /**
     * Defining Class Attributes
     *
     */
    private long mId ;
    private String mUsername;
    private String mPassword ;
    private int mGender ;
    private String mDob;
    private Boolean mIsDelete;

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

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public void setDob(String dob) {
        mDob = dob;
    }

    public String getDob() {
        return mDob;
    }

    public int getGender() { return mGender;}

    public void setGender(int gender) { mGender = gender;}

    public Boolean getIsDelete() {
        return mIsDelete;
    }
}
