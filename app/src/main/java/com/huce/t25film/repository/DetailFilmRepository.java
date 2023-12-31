package com.huce.t25film.repository;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.ShowService;
import com.huce.t25film.resources.FilmResource;
import com.huce.t25film.resources.ShowResource;

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
}
