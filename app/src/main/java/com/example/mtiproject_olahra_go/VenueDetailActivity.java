package com.example.mtiproject_olahra_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class VenueDetailActivity extends AppCompatActivity {

    TextView tvVenueName, tvVenueCourt, tvVenueAddress, tvSchedule;
    ImageView ivVenue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);
        ivVenue = findViewById(R.id.ivVenue);
        tvVenueName = findViewById(R.id.tvVenueName);
        tvVenueCourt = findViewById(R.id.tvVenueCourt);
        tvVenueAddress = findViewById(R.id.tvVenueAddress);
        tvSchedule = findViewById(R.id.tvSchedule);
        Intent intent = getIntent();
        int venueId = intent.getIntExtra(VenueAdapter.SEND_VENUE, 0);
        VenueDB venueDB = new VenueDB(this);
        Venue venue = venueDB.getVenueDetail(venueId);

        tvVenueName.setText(venue.getVenueName());
        tvVenueCourt.setText("Jumlah Lapangan: " + venue.getVenueCourt());
        tvVenueAddress.setText("Lokasi: " + venue.getVenueAddress());
    }
}