package com.huce.t25film.model;

import com.google.gson.annotations.SerializedName;

public class User {
    private String status;
    @SerializedName("idtk")
    private int uid;
    private String email;
    private String name;
    private String phone;
    private String password;

    public User(String status, int uid, String email, String name, String phone, String password) {
        this.status = status;
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
