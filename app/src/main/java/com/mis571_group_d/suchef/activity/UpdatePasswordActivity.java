package com.mis571_group_d.suchef.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.data.SessionManager;
import com.mis571_group_d.suchef.data.model.User;
import com.mis571_group_d.suchef.data.repo.UserRepo;

public class UpdatePasswordActivity extends AppCompatActivity {
    private Button mUpdateButton;
    private EditText mPasswordView, mRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        mPasswordView = (EditText) findViewById(R.id.current_password);
        mRePassword = (EditText) findViewById(R.id.new_password);
        mUpdateButton = (Button) findViewById(R.id.update_button);

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Getting user entered password
                String password = mPasswordView.getText().toString();
                String newPassword = mRePassword.getText().toString();

                //Creating User Object
                User user = new User();
                SessionManager session = new SessionManager(getApplicationContext());

                //Setting user id and password
                user.setId(session.getUserDetails());
                user.setPassword(newPassword);

                //Creating User Repository
                UserRepo userRepo = new UserRepo();

                //Update user password
                int status = userRepo.updatePassword(user, password);

                if(status == 1) {

                    Toast.makeText(getApplication(), "Successfully changed the password", Toast.LENGTH_LONG).show();

                    // Staring HomeActivity
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);

                    // Closing all the Activities
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(i);

                    finish();

                } else {
                    Toast.makeText(getApplication(), "Please check your current password entered", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

