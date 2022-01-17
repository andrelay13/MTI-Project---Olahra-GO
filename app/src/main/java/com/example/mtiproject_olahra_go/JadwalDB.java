package com.example.mtiproject_olahra_go;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class JadwalDB {
    public DBHelper dbHelper;

    public JadwalDB(Context ctx){
        dbHelper = new DBHelper(ctx);
    }

    public void insertJadwal(Jadwal jadwal){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_SCHEDULE_DAY, jadwal.getDay());
        cv.put(DBHelper.FIELD_SCHEDULE_START_TIME, jadwal.getStartTime());
        cv.put(DBHelper.FIELD_SCHEDULE_CLOSE_TIME, jadwal.getCloseTime());

        db.insert(DBHelper.TABLE_SCHEDULES, null, cv);

        db.close();
    }

    public Vector<Jadwal> getJadwal(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Vector<Jadwal> vecJadwal = new Vector<>();

        Cursor cursor = db.query(DBHelper.TABLE_SCHEDULES, null, null, null, null, null, null);

        while(cursor.moveToNext()){
            int jadwalId = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_SCHEDULE_ID));
            String day = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_SCHEDULE_DAY));
            int start = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_SCHEDULE_START_TIME));
            int close = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_SCHEDULE_CLOSE_TIME));

            Jadwal jadwal = new Jadwal(jadwalId, day, start, close);
            vecJadwal.add(jadwal);
        }

        cursor.close();
        db.close();
        dbHelper.close();
        return vecJadwal;

    }
}
