package com.example.mtiproject_olahra_go;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mtiproject_olahra_go.fragment.BookingFragment;
import com.example.mtiproject_olahra_go.fragment.VenueListFragment;

import org.w3c.dom.Text;

public class PaymentActivity extends AppCompatActivity {

    public static boolean flag = false;
    TextView tvName, tvSchedule, tvPrice, tvCourt;
    Button btnPay, btnCancel;
    int userId, venueId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        tvName = findViewById(R.id.tvVenueName);
        tvSchedule = findViewById(R.id.tvSchedule);
        tvCourt = findViewById(R.id.tvCourt);
        tvPrice = findViewById(R.id.tvTotal);
        btnPay = findViewById(R.id.btnPay);
        btnCancel = findViewById(R.id.btnCancel);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Booking booking = (Booking) bundle.get(BookingActivity.SEND_BOOKING_DETAIL);
        venueId = booking.getVenueId();
        VenueDB venueDB = new VenueDB(this);
        Venue venue = venueDB.getVenueDetail(venueId);

        tvName.setText(venue.getVenueName());
        tvSchedule.setText(booking.getDate());
        tvCourt.setText("Lapangan " + booking.getSelectedCourse());

        int duration = BookingActivity.stop - BookingActivity.start;
        tvPrice.setText("Total Harga: Rp. " + (venue.getVenuePrice() * duration));


        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:: INSERT BOOKING TO DATABASE
                finish();
                flag = true;
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}