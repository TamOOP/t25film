
package com.huce.t25film.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seat {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("seat")
    @Expose
    private Seat__1 seat;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Seat__1 getSeat() {
        return seat;
    }

    public void setSeat(Seat__1 seat) {
        this.seat = seat;
    }

}
