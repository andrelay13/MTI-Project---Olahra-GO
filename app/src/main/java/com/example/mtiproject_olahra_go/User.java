package com.example.mtiproject_olahra_go;

public class User {
    private int userId;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String userPassword;

    public User(int userId, String userName, String userPhone, String userEmail, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public User(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}

