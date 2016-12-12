package com.mis571_group_d.suchef.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.mis571_group_d.suchef.R;
import com.mis571_group_d.suchef.activity.ProfileActivity;
import com.mis571_group_d.suchef.activity.UpdatePasswordActivity;
import com.mis571_group_d.suchef.data.SessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    public SettingsFragment() {
        // Required empty public constructor
    }


    private TableRow mChangeProfile, mChangePassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.settings_profile, container, false);

        mChangeProfile= (TableRow) view.findViewById(R.id.update_profile);
        mChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ProfileActivity.class);

                startActivity(i);
            }
        });
        mChangePassword=(TableRow) view.findViewById(R.id.update_password);
        mChangePassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), UpdatePasswordActivity.class);

                startActivity(i);
            }
        });

        return view;
        

    }


}
