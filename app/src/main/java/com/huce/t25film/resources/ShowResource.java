package com.huce.t25film.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huce.t25film.model.Film;

import java.util.List;

public class ShowResource {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("films")
    @Expose
    private List<Film> films;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
