package com.example.mtiproject_olahra_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText txtPassword, txtUsername;
    Button btnLogin;
    TextView tvRegister;
    UserDB userDb;
    Context context;
    VenueDB venueDB;
    public static final String SEND_LOGIN = "com.example.application.OlahraGO.SEND_LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        userDb = new UserDB(this);

        //INSERT DATA MANUALLY
//        insertData();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkUsername() && checkPassword()){
                    String username = txtUsername.getText().toString();
                    String password = txtPassword.getText().toString();
                    Integer id = userDb.getId(username);

                    if(userDb.checkUsers(username, password)){
                        Intent intent = new Intent(LoginActivity.this, NavigationBar.class);
                        SaveId(SEND_LOGIN, id);
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                        startActivity(intent);

                    }else{
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                        txtUsername.setError("Username is wrong!");
                        txtPassword.setError("Password does not match!");
                    }
                }

            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public Boolean checkUsername(){
        if(txtUsername.getText().toString().isEmpty()) {
            txtUsername.setError("Usenrame cannot be empty");
            return false;
        }

        return true;
    }

    public Boolean checkPassword(){
        if(txtPassword.getText().toString().isEmpty()) {
            txtPassword.setError("Password cannot be empty");
            return false;
        }

        return true;
    }

    public void SaveId(String key, int id){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, id);
        editor.commit();
    }

    public void insertData(){
        UserDB userDB = new UserDB(this);
        User admin = new User();
        admin.setUserName("admin");
        admin.setUserPassword("admin");
        admin.setUserEmail("admin");
        admin.setUserPhone("0123456789");
        userDB.insertUser(admin);

        venueDB = new VenueDB(this);

        Venue venue1 = new Venue();
        venue1.setVenueName("Champion Futsal");
        venue1.setVenuePhone("02164715383");
        venue1.setVenueAddress("Jalan Kemanggisan");
        venue1.setVenueSport("Futsal");
        venue1.setVenueCourt(1);
        venue1.setVenuePrice(20000);
        venueDB.insertVenue(venue1);

        Venue venue2 = new Venue();
        venue2.setVenueName("Orion Basket");
        venue2.setVenuePhone("02164715384");
        venue2.setVenueAddress("Jalan Pluit");
        venue2.setVenueSport("Basket");
        venue2.setVenueCourt(1);
        venue2.setVenuePrice(400000);
        venueDB.insertVenue(venue2);

        Venue venue3 = new Venue();
        venue3.setVenueName("Elang Badminton");
        venue3.setVenuePhone("02164715385");
        venue3.setVenueAddress("Jalan Pademangan");
        venue3.setVenueSport("Badminton");
        venue3.setVenueCourt(4);
        venue3.setVenuePrice(50000);
        venueDB.insertVenue(venue3);

        Venue venue4 = new Venue();
        venue4.setVenueName("Maestro Futsal");
        venue4.setVenuePhone("02164715386");
        venue4.setVenueAddress("Jalan Kemayoran");
        venue4.setVenueSport("Futsal");
        venue4.setVenueCourt(6);
        venue4.setVenuePrice(180000);
        venueDB.insertVenue(venue4);

        Venue venue5 = new Venue();
        venue5.setVenueName("Darcici Basket");
        venue5.setVenuePhone("02164715387");
        venue5.setVenueAddress("Jalan Sunter");
        venue5.setVenueSport("Basket");
        venue5.setVenueCourt(2);
        venue5.setVenuePrice(350000);
        venueDB.insertVenue(venue5);

        Venue venue6 = new Venue();
        venue6.setVenueName("GOR Badminton");
        venue6.setVenuePhone("02164715388");
        venue6.setVenueAddress("Jalan Ancol");
        venue6.setVenueSport("Badminton");
        venue6.setVenueCourt(4);
        venue6.setVenuePrice(60000);
        venueDB.insertVenue(venue6);

        //Jadwal
        JadwalDB jadwalDB = new JadwalDB(this);
        Jadwal senin = new Jadwal();
        senin.setDay("Senin");
        senin.setStartTime(8);
        senin.setCloseTime(24);
        jadwalDB.insertJadwal(senin);

        Jadwal selasa = new Jadwal();
        selasa.setDay("Selasa");
        selasa.setStartTime(8);
        selasa.setCloseTime(24);
        jadwalDB.insertJadwal(selasa);

        Jadwal rabu = new Jadwal();
        rabu.setDay("Rabu");
        rabu.setStartTime(8);
        rabu.setCloseTime(24);
        jadwalDB.insertJadwal(rabu);

        Jadwal kamis  = new Jadwal();
        kamis.setDay("Kamis");
        kamis.setStartTime(8);
        kamis.setCloseTime(24);
        jadwalDB.insertJadwal(kamis);

        Jadwal jumat = new Jadwal();
        jumat.setDay("Jumat");
        jumat.setStartTime(8);
        jumat.setCloseTime(24);
        jadwalDB.insertJadwal(jumat);

        Jadwal sabtu = new Jadwal();
        sabtu.setDay("Sabtu");
        sabtu.setStartTime(8);
        sabtu.setCloseTime(24);
        jadwalDB.insertJadwal(sabtu);

        Jadwal minggu = new Jadwal();
        minggu.setDay("Minggu");
        minggu.setStartTime(8);
        minggu.setCloseTime(24);
        jadwalDB.insertJadwal(minggu);

    }

}