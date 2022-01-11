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

            Venue venue = new Venue(venueId, venueName, venuePhone, venueAddress, venueSport);
            vecVenues.add(venue);
        }

        cursor.close();
        db.close();
        dbHelper.close();
        return vecVenues;
    }
}
