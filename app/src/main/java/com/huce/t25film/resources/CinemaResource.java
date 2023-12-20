package com.huce.t25film.resources;

import com.google.gson.annotations.SerializedName;
import com.huce.t25film.model.Cinema;
import com.huce.t25film.model.Film;
import com.huce.t25film.model.Show;

public class CinemaResource {
    @SerializedName("status")
    private String status;
    @SerializedName("cinema")
    private Cinema cinema;
    @SerializedName("film")
    private Film film;
    @SerializedName("show")
    private Show show;
    @SerializedName("message")
    private String message;

    public CinemaResource(String status, Cinema cinema, Film film, Show show, String message) {
        this.status = status;
        this.cinema = cinema;
        this.film = film;
        this.show = show;
        this.message = message;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
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
