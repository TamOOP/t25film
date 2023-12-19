package com.huce.t25film.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.model.Film;
import com.huce.t25film.resources.FilmResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SCSFragmentRepository {
    private MutableLiveData<List<Film>> listfilmsLiveData = new MutableLiveData<>();
    private static SCSFragmentRepository instance;
    private Retrofit retrofit;
    private FilmService filmService;

    // instance
    public static synchronized SCSFragmentRepository getInstance(){
        if(instance == null){
            instance = new SCSFragmentRepository();
        }
        return instance;
    }

    // build retrofit va tao user api khi instance
    public SCSFragmentRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        filmService = retrofit.create(FilmService.class);
    }

    // fetch api va xu ly
    public MutableLiveData<List<Film>> getAllFilms(SCSFragmentRepository.OnFilmListCallback onFilmListCallback){

        Call<List<Film>> _filmsCall = filmService.getListFilmsSCS();

        // call async api
        _filmsCall.enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                // parse json sang POJO object
                List<Film> films = response.body();

                // thay doi du lieu cua live data
                listfilmsLiveData.setValue(films);
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                Log.e("error", t.getMessage());
            }
        });

        return listfilmsLiveData;
    }
    public interface OnFilmListCallback {
        void onSuccess(List<Film> filmList);
        void onError(String errorMessage);
    }
}
