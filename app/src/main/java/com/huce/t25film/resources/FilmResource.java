
package com.huce.t25film.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huce.t25film.model.Film__1;

public class FilmResource {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("film")
    @Expose
    private Film__1 film;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Film__1 getFilm() {
        return film;
    }

    public void setFilm(Film__1 film) {
        this.film = film;
    }

}
