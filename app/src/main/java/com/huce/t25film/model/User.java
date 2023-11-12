package com.huce.t25film.model;

import java.sql.Timestamp;

public class User {
    private int id;

    private String username;

    private String pswd;
    private Timestamp created_at;
    private Timestamp updated_at;

    public User(int id, String username, String pswd, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.username = username;
        this.pswd = pswd;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Timestamp getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

}
