package com.huce.t25film.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.resources.FilmResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HoursDetailFilmRepository {
    private MutableLiveData<FilmResource> filmResource = new MutableLiveData<>();
    private static HoursDetailFilmRepository instance;
    private Retrofit retrofit;
    private FilmService filmService;

    // instance
    public static synchronized HoursDetailFilmRepository getInstance(){
        if(instance == null){
            instance = new HoursDetailFilmRepository();
        }
        return instance;
    }

    // build retrofit
    public HoursDetailFilmRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        filmService = retrofit.create(FilmService.class);
    }

    public MutableLiveData<FilmResource> getFilmResource(int filmId) {
        Call<FilmResource> call = filmService.getFilmsId(filmId);
        call.enqueue(new Callback<FilmResource>() {
            @Override
            public void onResponse(Call<FilmResource> call, Response<FilmResource> response) {
                filmResource.setValue(response.body());
            }

            @Override
            public void onFailure(Call<FilmResource> call, Throwable t) {
                Log.e("error", t.getMessage());
                getFilmResource(filmId);
            }
        });
        return filmResource;
    }
}
