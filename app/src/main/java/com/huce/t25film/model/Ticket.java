
package com.huce.t25film.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticket {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("seat")
    @Expose
    private String seat;
    @SerializedName("show")
    @Expose
    private Show show;
    @SerializedName("cost")
    @Expose
    private String cost;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }


}
