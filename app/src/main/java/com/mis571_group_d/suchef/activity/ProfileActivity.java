package com.mis571_group_d.suchef.activity;
import android.app.DatePickerDialog;
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

public class ProfileActivity extends AppCompatActivity {

    private Button mUpdateButton;
    private DatePicker mDatepicker;
    private RadioGroup mRadioGroup;
    private TextView mdateView;
    private int year, month, day;


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

        mdateView = (TextView) findViewById(R.id.textView4);

        showDate(year,month,day);
        year = mDatepicker.getYear();
        month = mDatepicker.getMonth();
        day = mDatepicker.getDayOfMonth();

        //Get data on click of button
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get index of the gender radio button
                int index = mRadioGroup.indexOfChild(findViewById(mRadioGroup.getCheckedRadioButtonId()));

                //get date from the entered field
                String date = year + "-" + (month + 1) + "-" + day;

                //Create Session object
                SessionManager session = new SessionManager(getApplicationContext());

                //Create User object
                User user = new User();

                //Setting User id
                user.setId(session.getUserDetails());

                //Setting user date of birth
                user.setDob(date);

                //Setting user gender
                user.setGender(index);



            }
        });
    }


    private void showDate(int year, int month, int day) {
        mdateView.setText(year + "-" + (month + 1) + "-" + day);

    }


}
