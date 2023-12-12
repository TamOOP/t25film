package com.huce.t25film.api;

import com.huce.t25film.model.Film;
import com.huce.t25film.resources.FilmResource;
import com.huce.t25film.resources.FilmResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FilmService {
    @GET("films")
    Call<List<Film>> getListFilms();

    // get user theo id
    @GET("films/{id}")
    Call<FilmResource> getFilmsId(@Path("id") int id);

    @GET("films/find/showing")
    Call<List<Film>> getListFilmsDC();

    @GET("films/find/upComing")
    Call<List<Film>> getListFilmsSC();

    @GET("films/find/earlyShow")
    Call<List<Film>> getListFilmsSCS();
}
