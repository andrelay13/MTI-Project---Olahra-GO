package com.example.mtiproject_olahra_go;

public class Venue {

    private int venueId;
    private String venueName;
    private String venuePhone;
    private String venueAddress;
    private String venueSport;

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenuePhone() {
        return venuePhone;
    }

    public void setVenuePhone(String venuePhone) {
        this.venuePhone = venuePhone;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public String getVenueSport() {
        return venueSport;
    }

    public void setVenueSport(String venueSport) {
        this.venueSport = venueSport;
    }

    public Venue(int venueId, String venueName, String venuePhone, String venueAddress, String venueSport) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.venuePhone = venuePhone;
        this.venueAddress = venueAddress;
        this.venueSport = venueSport;
    }

    public Venue(){

    }
}
