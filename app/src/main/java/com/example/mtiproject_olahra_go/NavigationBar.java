package com.example.mtiproject_olahra_go;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mtiproject_olahra_go.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class NavigationBar extends AppCompatActivity {

    BottomNavigationView navbar;

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
                    Toast.makeText(NavigationBar.this, "Ini Home", Toast.LENGTH_LONG).show();
                }else if(id == R.id.ic_booking){
                    Toast.makeText(NavigationBar.this, "Ini Booking", Toast.LENGTH_LONG).show();
                }else if(id == R.id.ic_profile){
                    Toast.makeText(NavigationBar.this, "Ini Profile", Toast.LENGTH_LONG).show();
                }

                return true;
            }
        });

    }
}