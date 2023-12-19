package com.huce.t25film.resources;

import com.google.gson.annotations.SerializedName;
import com.huce.t25film.model.Seat;

public class SeatResource {
    @SerializedName("status")
    private String status;
    @SerializedName("seat")
    private Seat seat;
    @SerializedName("message")
    private String message;

    public SeatResource(String status, Seat seat, String message) {
        this.status = status;
        this.seat = seat;
        this.message = message;
    }

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

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
