package com.example.mtiproject_olahra_go;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class VenueDB {
    public DBHelper dbHelper;

    public VenueDB(Context ctx) {
        dbHelper = new DBHelper(ctx);
    }

    public void insertVenue(Venue venue){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_VENUE_NAME, venue.getVenueName());
        cv.put(DBHelper.FIELD_VENUE_PHONE, venue.getVenuePhone());
        cv.put(DBHelper.FIELD_VENUE_ADDRESS, venue.getVenueAddress());
        cv.put(DBHelper.FIELD_VENUE_SPORT, venue.getVenueSport());
        cv.put(DBHelper.FIELD_VENUE_COURTS, venue.getVenueCourt());

        db.insert(DBHelper.TABLE_VENUES, null, cv);
        db.close();
    }

    public Vector<Venue> getVenue(String sport){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Vector<Venue> vecVenues = new Vector<>();

        String selection = "venueSport=?";
        String[] selectionargs = {"" + sport};
        Cursor cursor = db.query(DBHelper.TABLE_VENUES, null, selection, selectionargs, null, null, null);

        while(cursor.moveToNext()){
            int venueId = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_VENUE_ID));
            String venueName = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_VENUE_NAME));
            String venuePhone = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_VENUE_PHONE));
            String venueAddress = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_VENUE_ADDRESS));
            String venueSport = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_VENUE_SPORT));
            int venueCourt = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_VENUE_COURTS));
            Venue venue = new Venue(venueId, venueName, venuePhone, venueAddress, venueSport, venueCourt);
            vecVenues.add(venue);
        }

        cursor.close();
        db.close();
        dbHelper.close();
        return vecVenues;
    }

    public Venue getVenueDetail(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "venueId=?";
        String[] selectionargs = {"" + id};
        Cursor cursor = db.query(DBHelper.TABLE_VENUES, null, selection, selectionargs, null, null, null);

        while(cursor.moveToFirst()){
            int venueId = cursor.getInt(0);
            String venueName = cursor.getString(1);
            String venuePhone = cursor.getString(2);
            String venueAddress = cursor.getString(3);
            String venueSport = cursor.getString(4);
            int venueCourt = cursor.getInt(5);

            Venue venue = new Venue(venueId, venueName, venuePhone, venueAddress, venueSport, venueCourt);
            cursor.close();
            db.close();
            dbHelper.close();
            return  venue;
        }
        cursor.close();
        db.close();
        dbHelper.close();
        return  null;
    }
}
