package com.example.mtiproject_olahra_go;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Vector;

public class BookingActivity extends AppCompatActivity {

    public static final String SEND_BOOKING_DETAIL = "com.example.mtiproject.SEND_BOOKING_DETAIL";

    DatePicker datePicker;
    TextView tvPicker, txtStart, txtStop, tvError;
    Button btnCheck;
    private int date, month, year, currDate, currMonth, currYear, venueId, userId, court;
    public static int start, stop;
    private String strDate, strStart, strStop, dateTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        tvPicker = findViewById(R.id.numberPicker);
        txtStart = findViewById(R.id.txtStart);
        txtStop = findViewById(R.id.txtStop);
        btnCheck = findViewById(R.id.btnCheck);
        tvError = findViewById(R.id.tvError);
        start = 0;
        stop = 0;
        court = 0;
        strDate = "";
        //get VenueId
        Intent intent = getIntent();
        venueId = intent.getIntExtra(VenueDetailActivity.SEND_VENUEID, 0);
        VenueDB venueDB = new VenueDB(this);
        Venue venue = venueDB.getVenueDetail(venueId);

        //get Jadwal
        JadwalDB jadwalDB = new JadwalDB(this);
        Vector<Jadwal> vecJadwal = jadwalDB.getJadwal();

        //DATE PICKER
        final Calendar cal = Calendar.getInstance();
        currDate = cal.get(Calendar.DAY_OF_MONTH);
        currMonth = cal.get(Calendar.MONTH);
        currYear = cal.get(Calendar.YEAR);
        datePicker.updateDate(currYear, currMonth, currDate);
        datePicker.setMinDate(cal.getTimeInMillis());


        tvPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog d = new Dialog(BookingActivity.this);
                d.setTitle("Pilih Lapangan");
                d.setContentView(R.layout.number_picker_dialog);
                tvPicker.setText("" + 1);
                court = 1;
                NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPickerPopup);
                np.setMinValue(1);
                np.setMaxValue(venue.getVenueCourt());
                np.setWrapSelectorWheel(false);
                d.show();
                np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        tvPicker.setText("" + i1);
                        court = i1;
                    }
                });

                Button btnOk = d.findViewById(R.id.btnOk);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        d.dismiss();
                    }
                });
            }
        });

        txtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvError.setText("");
                Dialog d = new Dialog(BookingActivity.this);
                d.setTitle("Pilih waktu mulai");
                d.setContentView(R.layout.number_picker_dialog);
                NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPickerPopup);
                np.setMinValue(vecJadwal.get(0).getStartTime());
                np.setMaxValue(vecJadwal.get(0).getCloseTime()-1);
                strStart = "0" + vecJadwal.get(0).getStartTime() + ":00";
                txtStart.setText(strStart);
                start = vecJadwal.get(0).getStartTime();
                d.show();
                np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        if(i1 < 10) {
                            strStart = "0" + i1 + ":00";
                            txtStart.setText(strStart);
                        }
                        else{
                            strStart = "" + i1 + ":00";
                            txtStart.setText(strStart);
                        }
                        start = i1;
                    }
                });

                Button btnOk = d.findViewById(R.id.btnOk);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        d.dismiss();
                    }
                });

            }
        });

        txtStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(start !=0){
                        tvError.setText("");
                        Dialog d = new Dialog(BookingActivity.this);
                        d.setTitle("Pilih waktu selesai");
                        d.setContentView(R.layout.number_picker_dialog);
                        NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPickerPopup);
                        np.setMinValue(start+1);
                        np.setMaxValue(vecJadwal.get(0).getCloseTime());
                        if(start+1 < 10) strStop = "0" + (start + 1) + ":00";
                        else strStop = "" + (start + 1) + ":00";
                        txtStop.setText(strStop);
                        stop = vecJadwal.get(0).getCloseTime();
                        d.show();
                        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                            @Override
                            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                                if(i1 < 10){
                                    strStop = "0" + i1 + ":00";
                                    txtStop.setText(strStop);
                                }
                                else{
                                    strStop = "" + i1 + ":00";
                                    txtStop.setText(strStop);
                                }
                                stop = i1;
                            }
                        });

                        Button btnOk = d.findViewById(R.id.btnOk);
                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                d.dismiss();
                            }
                        });
                    }else{
                        tvError.setText("Pilih waktu mulai!");
                    }
                }
            });


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(court == 0)  tvError.setText("Pilih lapangan!");
                else if(start == 0)  tvError.setText("Pilih waktu mulai!");
                else if(stop == 0)   tvError.setText("Pilih waktu selesai!");
                else {
                    tvError.setText("");
                    //DATEPICKER
                    date = datePicker.getDayOfMonth();
                    month = datePicker.getMonth() + 1;
                    year = datePicker.getYear();
                    strDate = "" + date + "-" + month + "-" + year;
                    dateTime = strDate + ", " + strStart + "-" + strStop;
                    BookingDB db = new BookingDB(getApplicationContext());
                    Vector<Booking> vecBooking = db.getBookings();
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    userId = sp.getInt(LoginActivity.SEND_LOGIN, 0);

                    if(vecBooking.isEmpty()){
                        toPaymentpage();
                    }else {
                        for (Booking booking : vecBooking) {
                            if ((booking.getDate().equals(dateTime) || booking.getDate().contains(strStart) || booking.getDate().contains(strStop)) && booking.getSelectedCourse() == court && booking.getUserId() == userId && booking.getVenueId() == venueId) {
                                tvError.setText("Sudah dibooking");
                            } else {
                                toPaymentpage();
                                break;
                            }
                        }
                    }
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PaymentActivity.flag == true) {
            finish();
        }
    }

    public void toPaymentpage(){
        Intent intent1 = new Intent(BookingActivity.this, PaymentActivity.class);
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setVenueId(venueId);
        booking.setSelectedCourse(court);
        booking.setDate(dateTime);
        intent1.putExtra(SEND_BOOKING_DETAIL, booking);
        startActivity(intent1);
    }
}