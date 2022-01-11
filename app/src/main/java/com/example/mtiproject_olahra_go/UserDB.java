package com.example.mtiproject_olahra_go;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class UserDB {
    public DBHelper dbHelper;

    public UserDB(Context ctx){
        dbHelper = new DBHelper(ctx);
    }

    public void insertUser(User user){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_USER_NAME, user.getUserName());
        cv.put(DBHelper.FIELD_USER_PHONE, user.getUserPhone());
        cv.put(DBHelper.FIELD_USER_EMAIL, user.getUserEmail());
        cv.put(DBHelper.FIELD_USER_PASSWORD, user.getUserPassword());

        db.insert(DBHelper.TABLE_USERS, null, cv);

        db.close();
    }

    public Vector<User> getUsers(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Vector<User> vecUser = new Vector<>();

        Cursor cursor = db.query(DBHelper.TABLE_USERS, null,null,null,null,null,null);

        while(cursor.moveToNext()){
            int userId = cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_USER_ID));
            String userName = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_USER_NAME));
            String userPhone = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_USER_PHONE));
            String userEmail = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_USER_EMAIL));
            String userPassword = cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_USER_PASSWORD));

            User user = new User(userId, userName, userPhone, userEmail, userPassword);
            vecUser.add(user);
        }

        cursor.close();
        db.close();
        dbHelper.close();
        return vecUser;
    }

    public boolean checkUsers(String username, String password){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "userName=? AND userPasword=?";
        String[] selectionargs = {"" + username, "" + password};
        int check = 0;
        Cursor cursor = db.query(DBHelper.TABLE_USERS, null, selection, selectionargs,null, null ,null);

        if (cursor.moveToFirst()){
            return true;
        }
        else return false;
    }

    public Integer getId(String username){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String get_user_id = "SELECT " + DBHelper.FIELD_USER_ID + " FROM " + DBHelper.TABLE_USERS +
                " WHERE " + DBHelper.FIELD_USER_NAME + " = " + "'" + username + "'";

        Cursor cursor = db.rawQuery(get_user_id, null);

        if(cursor.moveToFirst()){
            Integer id = cursor.getInt(0);

            db.close();
            cursor.close();
            return id;
        }

        db.close();
        cursor.close();
        return null;
    }

    public User getUserDetail(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String get_user_detail = "SELECT * FROM " + DBHelper.TABLE_USERS +
                " WHERE " +  DBHelper.FIELD_USER_ID + " = " + id;

        Cursor cursor = db.rawQuery(get_user_detail,null);

        if(cursor.moveToFirst()){
            User user = new User();
            user.setUserId(cursor.getInt(0));
            user.setUserName(cursor.getString(1));
            user.setUserPhone(cursor.getString(2));
            user.setUserEmail(cursor.getString(3));
            user.setUserPassword(cursor.getString(4));
            cursor.close();
            db.close();
            return user;
        }
        cursor.close();
        db.close();
        return null;
    }

}
