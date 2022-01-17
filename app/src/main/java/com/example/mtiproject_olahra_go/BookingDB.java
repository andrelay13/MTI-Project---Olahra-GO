package com.example.mtiproject_olahra_go;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class BookingDB {
    public DBHelper dbHelper;

    public BookingDB(Context ctx){
        dbHelper = new DBHelper(ctx);
    }

    public void insertBooking(Booking booking){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_USER_ID, booking.getUserId());
        cv.put(DBHelper.FIELD_VENUE_ID, booking.getVenueId());
        cv.put(DBHelper.FIELD_BOOKING_DATE, booking.getDate());
        cv.put(DBHelper.FIELD_BOOKING_COURT, booking.getSelectedCourse());
        db.insert(DBHelper.TABLE_BOOKINGS, null, cv);
        db.close();
    }

    public Vector<Booking> getBookings(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Vector<Booking> vecBook = new Vector<>();

        Cursor cursor = db.query(DBHelper.TABLE_BOOKINGS,null, null,null, null, null, null);
        while (cursor.moveToNext()){
            int bookingId = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_BOOKING_ID));
            int userId = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_USER_ID));
            int venueId = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_VENUE_ID));
            String date = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_BOOKING_DATE));
            int court = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_BOOKING_COURT));

            Booking booking = new Booking(bookingId, userId, venueId, date, court);
            vecBook.add(booking);
        }
        cursor.close();
        db.close();
        dbHelper.close();
        return vecBook;

    }

    public Vector<Booking> getUserBookings(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Vector<Booking> vecBook = new Vector<>();

        String selection = "userId=?";
        String[] selectionargs = {"" + id};

        Cursor cursor = db.query(DBHelper.TABLE_BOOKINGS, null, selection, selectionargs, null, null, null);

        while(cursor.moveToNext()){
            int bookingId = cursor.getInt(0);
            int userId = cursor.getInt(1);
            int venueId = cursor.getInt(2);
            String date = cursor.getString(3);
            int court = cursor.getInt(4);

            Booking booking = new Booking(bookingId, userId, venueId, date, court);
            vecBook.add(booking);
        }
        cursor.close();
        db.close();
        dbHelper.close();
        return vecBook;
    }

}
