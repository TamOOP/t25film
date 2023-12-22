package com.huce.t25film.viewmodels;

import android.content.Context;
import android.view.View;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huce.t25film.model.Film;
import com.huce.t25film.repository.HoursDetailFilmRepository;
import com.huce.t25film.repository.NetWorkConnection;
import com.huce.t25film.resources.FilmResource;

public class HoursDetailFilmViewModel extends ViewModel {
    private MutableLiveData<FilmResource> filmResource = new MutableLiveData<>();
    private MutableLiveData<Film> filmEarlyResource;
    private MutableLiveData<Integer> load;
    private MutableLiveData<String> message;

    public MutableLiveData<String> getMessage() {
        if (message == null){
            message = new MutableLiveData<>();
        }
        return message;
    }

    public MutableLiveData<Integer> getLoad() {
        if(load == null){
            load = new MutableLiveData<>(View.GONE);
        }
        return load;
    }

    public LiveData<Film> getFilmEarlyResource(Context context, int filmId) {
        if (filmEarlyResource == null){
            filmEarlyResource = new MutableLiveData<>();
        }
        if(!NetWorkConnection.isNetworkAvailable(context)) {
            message.setValue("Không có kết nối mạng, vui lòng thử lại");
            load.setValue(View.GONE);
            return new MutableLiveData<>();
        }
        filmEarlyResource = HoursDetailFilmRepository.getInstance().getFilmEarlyResource(filmId);
        return filmEarlyResource;
    }

    public LiveData<FilmResource> getFilmResource(Context context, int filmId){
        load.setValue(View.VISIBLE);
        if(!NetWorkConnection.isNetworkAvailable(context)) {
            message.setValue("Không có kết nối mạng, vui lòng thử lại");
            load.setValue(View.GONE);
            return new MutableLiveData<>();
        }
        filmResource = HoursDetailFilmRepository.getInstance().getFilmResource(filmId);
        return filmResource;
    }


    public void clearData(){
        filmResource.setValue(null);
        if (filmEarlyResource == null) return;
        filmEarlyResource.setValue(null);
    }
}
