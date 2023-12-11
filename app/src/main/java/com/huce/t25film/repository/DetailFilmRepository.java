package com.huce.t25film.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.resources.FilmResource;
import com.huce.t25film.resources.FilmResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailFilmRepository {
    private MutableLiveData<FilmResource> filmLiveData = new MutableLiveData<>();
    private static DetailFilmRepository instance;
    private Retrofit retrofit;
    private FilmService filmService;

    // instance
    public static synchronized DetailFilmRepository getInstance(){
        if(instance == null){
            instance = new DetailFilmRepository();
        }
        return instance;
    }

    // build retrofit va tao user api khi instance
    public DetailFilmRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        filmService = retrofit.create(FilmService.class);
    }

    // fetch api va xu ly
    public MutableLiveData<FilmResource> getFilm(Integer filmId, OnFilmCallback onFilmCallback){
        Call<FilmResource> _filmCall = filmService.getFilmsId(filmId);

        // call async api
        _filmCall.enqueue(new Callback<FilmResource>() {
            @Override
            public void onResponse(Call<FilmResource> call, Response<FilmResource> response) {
                // parse json sang POJO object
                FilmResource film = response.body();

                // thay doi du lieu cua live data
                filmLiveData.setValue(film);
            }

            @Override
            public void onFailure(Call<FilmResource> call, Throwable t) {
                Log.e("error", t.getMessage());
            }
        });

        return filmLiveData;
    }
    public interface OnFilmCallback {
        void onSuccess(FilmResource film);
        void onError(String errorMessage);
    }
}
