package com.example.mtiproject_olahra_go;

public class Jadwal {
    private int id;
    private String day;
    private int startTime;
    private int closeTime;

    public Jadwal(){

    }

    public Jadwal(int id, String day, int startTime, int closeTime) {
        this.id = id;
        this.day = day;
        this.startTime = startTime;
        this.closeTime = closeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(int closeTime) {
        this.closeTime = closeTime;
    }
}
