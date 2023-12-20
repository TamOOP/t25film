package com.huce.t25film.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huce.t25film.model.Show;

import java.util.List;

public class ShowDateResource {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("shows")
    @Expose
    private List<Show> shows;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }



    public ShowDateResource(String date, List<Show> showtimesForDate) {
        this.date = date;
        this.shows=showtimesForDate;
    }
}
