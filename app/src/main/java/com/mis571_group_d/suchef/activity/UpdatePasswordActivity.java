package com.mis571_group_d.suchef.activity;
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
        mPasswordView =(EditText) findViewById(R.id.password);
        mRePassword = (EditText)  findViewById(R.id.newpassward);
        mUpdateButton = (Button)findViewById(R.id.update_button);
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String password = mPasswordView.getText().toString();
                String newpassward = mRePassword.getText().toString();
                User user = new User();
                SessionManager session = new SessionManager(getApplicationContext());
                user.setId(session.getUserDetails());


            }
        });
    }
}

