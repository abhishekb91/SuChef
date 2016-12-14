package com.mis571_group_d.suchef.activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.app.Dialog;

import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.data.SessionManager;
import com.mis571_group_d.suchef.data.model.User;
import com.mis571_group_d.suchef.data.repo.UserRepo;

import java.lang.reflect.Array;
import java.util.Date;

public class ProfileActivity extends AppCompatActivity {

    private Button mUpdateButton;

    private DatePicker mDatepicker;

    private RadioGroup mRadioGroup;

    private UserRepo userRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        //Referencing Date Picker
        mDatepicker = (DatePicker) findViewById(R.id.user_date_of_birth);

        //Referencing Radio Group
        mRadioGroup = (RadioGroup) findViewById(R.id.radioSex);

        //Referencing Button
        mUpdateButton = (Button) findViewById(R.id.update_button);

        //Create Session object
        final SessionManager session = new SessionManager(getApplicationContext());

        //Creating UserRepo class
        userRepo = new UserRepo();

        //Get data on click of button
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get index of the gender radio button
                int index = mRadioGroup.indexOfChild(findViewById(mRadioGroup.getCheckedRadioButtonId()));

                //get date from the entered field
                String date = mDatepicker.getYear() + "-" + (mDatepicker.getMonth() + 1) + "-" + mDatepicker.getDayOfMonth();

                //Create User object
                User user = new User();

                //Setting User id
                user.setId(session.getUserDetails());

                //Setting user date of birth
                user.setDob(date);

                //Setting user gender
                user.setGender(index);

                if(userRepo.updateProfile(user) == 1) {

                    Toast.makeText(getApplication(), "Successfully updated the profile", Toast.LENGTH_LONG).show();

                    // Staring HomeActivity
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);

                    // Closing all the Activities
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(i);

                    finish();

                } else {

                    Toast.makeText(getApplication(), "Unable to save your Profile", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Load current user's profile
        loadProfile(session.getUserDetails());
    }

    /**
     * This function is used to load User's Profile
     *
     * @param userId Id of user
     */
    public void loadProfile(long userId) {
        mDatepicker.setMinDate(0);
        mDatepicker.setMaxDate(new Date().getTime());

        //Getting user's profile information
        User userProfile = userRepo.userProfile(userId);

        //Splitting DOB into array
        String[] date = userProfile.getDob().split("-");

        //Updating the datepicker
        mDatepicker.updateDate(Integer.parseInt(date[0]), (Integer.parseInt(date[1]) - 1 ), Integer.parseInt(date[2]));

        //Setting up Gender radio button
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioSex);

        Log.d("Gender", "gender = " + userProfile.getGender());

        if(userProfile.getGender() == 0){
            radioGroup.check(R.id.female);
        } else {
            radioGroup.check(R.id.male);
        }

    }
}
