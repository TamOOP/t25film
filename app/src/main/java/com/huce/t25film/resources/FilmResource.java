
package com.huce.t25film.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huce.t25film.model.Film;

public class FilmResource {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("film")
    @Expose
    private Film film;
    @SerializedName("message")
    private String message;

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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

}
