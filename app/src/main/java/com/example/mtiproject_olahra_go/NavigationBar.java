package com.example.mtiproject_olahra_go;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mtiproject_olahra_go.fragment.BookingFragment;
import com.example.mtiproject_olahra_go.fragment.HomeFragment;
import com.example.mtiproject_olahra_go.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class NavigationBar extends AppCompatActivity {

    BottomNavigationView navbar;
    public static final String FRAGMENT_TAG = "com.example.mtiproject.FRAGMENT_TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);

        navbar = findViewById(R.id.bottomNavBar);

        navbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.ic_home){
                    HomeFragment homeFragment = HomeFragment.newInstance();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    transaction.replace(R.id.fragments, homeFragment);
                    transaction.commit();
                }else if(id == R.id.ic_booking){
                    BookingFragment bookingFragment = BookingFragment.newInstance();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    transaction.replace(R.id.fragments, bookingFragment);
                    transaction.commit();
                }else if(id == R.id.ic_profile){
                    ProfileFragment profileFragment = ProfileFragment.newInstance();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    transaction.replace(R.id.fragments, profileFragment);
                    transaction.commit();
                }

                return true;
            }
        });

    }

}