package com.example.mtiproject_olahra_go;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mtiproject_olahra_go.fragment.BookingFragment;

import java.util.Vector;

public class VenueDetailActivity extends AppCompatActivity {

    public static final String SEND_VENUEID = "com.example.mtiproject.SEND_VENUEID";
    private int venueId;

    TextView tvVenueName, tvVenueCourt, tvVenueAddress, tvSchedule, tvPrice;
    ImageView ivVenue;
    Button btnBook;
    RecyclerView rvJadwal, rvBooking;
    Vector<Jadwal> vecJadwal = new Vector<>();
    Vector<Booking> bookings = new Vector<>();

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);
        ivVenue = findViewById(R.id.ivVenue);
        tvVenueName = findViewById(R.id.tvVenueName);
        tvVenueCourt = findViewById(R.id.tvVenueCourt);
        tvVenueAddress = findViewById(R.id.tvVenueAddress);
        tvSchedule = findViewById(R.id.tvSchedule);
        tvPrice = findViewById(R.id.tvVenuePrice);
        btnBook = findViewById(R.id.btnBook);
        rvJadwal = findViewById(R.id.rvSchedule);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        int id = sp.getInt(LoginActivity.SEND_LOGIN, 0);

        //get VenueId
        Intent intent = getIntent();
        venueId = intent.getIntExtra(VenueAdapter.SEND_VENUE, 0);
        VenueDB venueDB = new VenueDB(this);
        Venue venue = venueDB.getVenueDetail(venueId);

        BookingDB bookingDB = new BookingDB(this);
        bookings = bookingDB.getUserBookings(id);

        tvVenueName.setText(venue.getVenueName());
        tvVenueCourt.setText("Jumlah Lapangan: " + venue.getVenueCourt());
        tvVenueAddress.setText("Lokasi: " + venue.getVenueAddress());
        tvPrice.setText("Rp. " + venue.getVenuePrice() + "/jam");
        getRvData();

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
                int id = venue.getVenueId();
                intent.putExtra(SEND_VENUEID,id);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(PaymentActivity.flag == true){
        finish();
        }
    }

    public void getRvData(){
        JadwalDB jadwalDB = new JadwalDB(this);
        vecJadwal = jadwalDB.getJadwal();

        JadwalAdapter jadwalAdapter = new JadwalAdapter(this);
        jadwalAdapter.setVecJadwal(vecJadwal);

        rvJadwal.setAdapter(jadwalAdapter);
        rvJadwal.setLayoutManager(new LinearLayoutManager(this));

    }


}