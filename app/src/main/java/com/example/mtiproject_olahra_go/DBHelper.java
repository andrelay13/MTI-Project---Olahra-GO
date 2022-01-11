package com.example.mtiproject_olahra_go;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "OlahragoDB";
    public static final int DB_VERSION = 1;

    //User Table
    public static final String TABLE_USERS = "User";
    public static final String FIELD_USER_ID = "UserId";
    public static final String FIELD_USER_NAME = "UserName";
    public static final String FIELD_USER_PHONE = "UserPhone";
    public static final String FIELD_USER_EMAIL = "UserEmail";
    public static final String FIELD_USER_PASSWORD = "UserPasword";


    //Venue Table
    public static final String TABLE_VENUES = "Venue";
    public static final String FIELD_VENUE_ID =  "VenueId";
    public static final String FIELD_VENUE_NAME = "VenueName";
    public static final String FIELD_VENUE_PHONE = "VenuePhone";
    public static final String FIELD_VENUE_ADDRESS = "VenueAddress";
    public static final String FIELD_VENUE_SPORT = "VenueSport";


    //Booking Table
    public static final String TABLE_BOOKINGS = "Booking";
    public static final String FIELD_BOOKING_ID =  "BookingId";
    public static final String FIELD_BOOKING_DATE =  "BookingDate";

    //Schedule Table
    public static final String TABLE_SCHEDULES = "Schedule";
    public static final String FIELD_SCHEDULE_ID =   "ScheduleId";
    public static final String FIELD_SCHEDULE_DAY = "ScheduleDay";
    public static final String FIELD_SCHEDULE_TIME = "ScheduleTime";

    public static final String CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_USERS +
                    " (" +
                    FIELD_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FIELD_USER_NAME + " TEXT," +
                    FIELD_USER_PHONE + " TEXT," +
                    FIELD_USER_EMAIL + " TEXT, "+
                    FIELD_USER_PASSWORD + " TEXT" +
                    " )";

    public static final String CREATE_VENUE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_VENUES +
                    " (" +
                    FIELD_VENUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FIELD_VENUE_NAME + " TEXT," +
                    FIELD_VENUE_PHONE + " TEXT," +
                    FIELD_VENUE_ADDRESS + " TEXT"+
                    " )";

    public static final String CREATE_BOOKING_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKINGS +
                    " (" +
                    FIELD_BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FIELD_USER_ID + " INTEGER REFERENCES " + TABLE_USERS + "(" + FIELD_USER_ID + ") ON UPDATE CASCADE ON DELETE CASCADE, " +
                    FIELD_VENUE_ID + " INTEGER REFERENCES " + TABLE_VENUES + "(" + FIELD_VENUE_ID + ") ON UPDATE CASCADE ON DELETE CASCADE, " +
                    FIELD_BOOKING_DATE + " TEXT" +
                    " )";

    public static final String CREATE_SCHEDULE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_SCHEDULES +
                    " (" +
                    FIELD_SCHEDULE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FIELD_VENUE_ID + " INTEGER REFERENCES " + TABLE_VENUES + "(" + TABLE_VENUES + ") ON UPDATE CASCADE ON DELETE CASCADE, " +
                    FIELD_SCHEDULE_DAY + " TEXT," +
                    FIELD_SCHEDULE_TIME + " TEXT" +
                    " )";


    public static final String DROP_USER = "DROP TABLE IF EXISTS " + TABLE_USERS;
    public static final String DROP_VENUE = "DROP TABLE IF EXISTS " + TABLE_VENUES;
    public static final String DROP_BOOKING= "DROP TABLE IF EXISTS " + TABLE_BOOKINGS;
    public static final String DROP_SCHEDULE= "DROP TABLE IF EXISTS " + TABLE_SCHEDULES;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_VENUE_TABLE);
        db.execSQL(CREATE_BOOKING_TABLE);
        db.execSQL(CREATE_SCHEDULE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_USER);
        db.execSQL(DROP_VENUE);
        db.execSQL(DROP_BOOKING);
        db.execSQL(DROP_SCHEDULE);
        onCreate(db);
    }
}
