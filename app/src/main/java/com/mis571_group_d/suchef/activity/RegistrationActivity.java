package com.mis571_group_d.suchef.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.data.SessionManager;
import com.mis571_group_d.suchef.data.model.User;
import com.mis571_group_d.suchef.data.repo.UserRepo;


/**
 * A login screen that offers login via email/password.
 */
public class RegistrationActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mRegistrationButton = (Button) findViewById(R.id.email_sign_in_button);
        mRegistrationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegistration();
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptRegistration() {

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            //focusView.requestFocus();
        } else {

            //Creating a user object
            User user = new User();
            user.setUsername(email);
            user.setPassword(password);

            //Registering a new user
            UserRepo newUser = new UserRepo();
            long userId = newUser.registration(user);

            Log.i("User_id", "User id is = " + userId);

            if(userId != -1) {
                // Session class instance
                SessionManager session = new SessionManager(getApplicationContext());

                session.createLoginSession(userId);

                //Showing Dummy Spinner
                final ProgressDialog dialog = new ProgressDialog(RegistrationActivity.this);

                // Set progress dialog style spinner
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                // Set the progress dialog title and message
                dialog.setTitle(R.string.app_name);
                dialog.setMessage("Creating new account...");

                dialog.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {

                        dialog.dismiss();

                        // Staring HomeActivity
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);

                        // Closing all the Activities
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        // Add new Flag to start new Activity
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        // Staring Login Activity
                        startActivity(i);

                        finish();
                    }
                }, 3000);

            } else {
                Toast.makeText(this, getString(R.string.error_invalid_credentials), Toast.LENGTH_SHORT).show();
            }

        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}

