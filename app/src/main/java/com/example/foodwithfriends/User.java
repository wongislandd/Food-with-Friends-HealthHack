package com.example.foodwithfriends;

public class User {
    String bio;
    String major;
    String name;
    String pref;
    String status;
    public User(String bio, String major, String name, String pref, String status){
        this.bio = bio;
        this.major = major;
        this.name = name;
        this.pref = pref;
        this.status = status;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPref() {
        return pref;
    }

    public void setPref(String pref) {
        this.pref = pref;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
