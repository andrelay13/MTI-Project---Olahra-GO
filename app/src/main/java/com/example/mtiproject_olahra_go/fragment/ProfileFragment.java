package com.example.mtiproject_olahra_go.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mtiproject_olahra_go.LoginActivity;
import com.example.mtiproject_olahra_go.R;
import com.example.mtiproject_olahra_go.RegisterVendor;
import com.example.mtiproject_olahra_go.User;
import com.example.mtiproject_olahra_go.UserDB;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    SharedPreferences sp;
    User user;
    UserDB userDB;

    TextView tvNamaUser, tvTelpUser, tvEmailUser, tvSignOut;
    Button btnRegistVendor;

    public ProfileFragment() {

    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        int id = sp.getInt(LoginActivity.SEND_LOGIN, 0);
        userDB = new UserDB(getContext());

        user = userDB.getUserDetail(id);

        tvNamaUser = view.findViewById(R.id.tvNamaUser);
        tvTelpUser = view.findViewById(R.id.tvTelpUser);
        tvEmailUser = view.findViewById(R.id.tvEmailUser);
        tvSignOut = view.findViewById(R.id.tvSignOut);
        btnRegistVendor = view.findViewById(R.id.btnRegistVendor);

        tvNamaUser.setText(user.getUserName());
        tvTelpUser.setText(user.getUserPhone());
        tvEmailUser.setText(user.getUserEmail());

        tvSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().finish();
            }
        });

        btnRegistVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegisterVendor.class);
                startActivity(intent);
            }
        });

        return view;
    }
}