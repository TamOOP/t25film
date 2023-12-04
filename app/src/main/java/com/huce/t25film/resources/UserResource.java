package com.huce.t25film.resources;

import com.google.gson.annotations.SerializedName;
import com.huce.t25film.model.User;

public class UserResource {
    @SerializedName("status")
    private String status;
    @SerializedName("user")
    private User user;
    @SerializedName("message")
    private String message;

    public UserResource() {
    }

    public UserResource(String status, User user, String message) {
        this.status = status;
        this.user = user;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
