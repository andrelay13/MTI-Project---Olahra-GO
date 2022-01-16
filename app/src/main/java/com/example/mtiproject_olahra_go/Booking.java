package com.example.mtiproject_olahra_go;

import java.io.Serializable;

public class Booking implements Serializable {
    private int bookId;
    private int userId;
    private int venueId;
    private String date;
    private int selectedCourse;
    public Booking(int bookId, int userId, int venueId, String date, int selectedCourse) {
        this.bookId = bookId;
        this.userId = userId;
        this.venueId = venueId;
        this.date = date;
        this.selectedCourse = selectedCourse;
    }

    public Booking(){

    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(int selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
}
