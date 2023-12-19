package com.huce.t25film.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.ShowService;
import com.huce.t25film.model.Film;
import com.huce.t25film.resources.ShowResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CalendarFilm1FragmentRepository {
    private MutableLiveData<ShowResource> showLiveData = new MutableLiveData<>();
    private static CalendarFilm1FragmentRepository instance;
    private Retrofit retrofit;
    private ShowService showService;

    // instance
    public static synchronized CalendarFilm1FragmentRepository getInstance(){
        if(instance == null){
            instance = new CalendarFilm1FragmentRepository();
        }
        return instance;
    }

    // build retrofit va tao user api khi instance
    public CalendarFilm1FragmentRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        showService = retrofit.create(ShowService.class);
    }
}
