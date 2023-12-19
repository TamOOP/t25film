package com.huce.t25film.repository;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.resources.FilmResource;

import retrofit2.Retrofit;

public class HoursDetailFilmRepository {
    private MutableLiveData<FilmResource> filmLiveData = new MutableLiveData<>();
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

    // build retrofit va tao user api khi instance
    public HoursDetailFilmRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        filmService = retrofit.create(FilmService.class);
    }
}
