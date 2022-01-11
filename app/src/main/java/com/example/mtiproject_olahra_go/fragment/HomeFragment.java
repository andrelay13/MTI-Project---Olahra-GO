package com.example.mtiproject_olahra_go.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtiproject_olahra_go.DBHelper;
import com.example.mtiproject_olahra_go.LoginActivity;
import com.example.mtiproject_olahra_go.R;
import com.example.mtiproject_olahra_go.User;
import com.example.mtiproject_olahra_go.UserDB;

public class HomeFragment extends Fragment {


    TextView tvGreet;
    SharedPreferences sp;
    UserDB userDB;
    CardView cvFutsal, cvBadminton, cvBasket;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cvFutsal = view.findViewById(R.id.cardSoccer);
        cvBasket = view.findViewById(R.id.cardBasket);
        cvBadminton = view.findViewById(R.id.cardBadminton);
        tvGreet = view.findViewById(R.id.tvGreet);

        //GREET
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        int id =sp.getInt(LoginActivity.SEND_LOGIN, 0);
        userDB = new UserDB(getContext());
        User user = userDB.getUserDetail(id);
        tvGreet.setText("Helloo " + user.getUserName());

        //Click Card
        cvFutsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "futsal", Toast.LENGTH_LONG).show();
            }
        });

        cvBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Basket", Toast.LENGTH_LONG).show();
            }
        });

        cvBadminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Badminton", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}