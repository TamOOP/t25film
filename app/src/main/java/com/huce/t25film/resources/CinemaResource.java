package com.huce.t25film.resources;

import com.google.gson.annotations.SerializedName;
import com.huce.t25film.model.Cinema;

public class CinemaResource {
    @SerializedName("status")
    private String status;
    @SerializedName("cinema")
    private Cinema cinema;

    @SerializedName("message")
    private String message;

    public CinemaResource(String status, Cinema cinema, String message) {
        this.status = status;
        this.cinema = cinema;
        this.message = message;
    }

    public CinemaResource() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
}
