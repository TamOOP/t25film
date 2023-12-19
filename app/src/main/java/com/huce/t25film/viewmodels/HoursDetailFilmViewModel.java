package com.huce.t25film.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.repository.DetailFilmRepository;
import com.huce.t25film.repository.HoursDetailFilmRepository;
import com.huce.t25film.resources.FilmResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class HoursDetailFilmViewModel extends ViewModel {
    private MutableLiveData<FilmResource> filmLiveData = new MutableLiveData<>();
    private HoursDetailFilmRepository filmRepository; // Tùy chọn: nơi bạn thực hiện gọi API
    private RecyclerView.Adapter adapterMovies;

    private int id;

    private FilmService filmService;


    public HoursDetailFilmViewModel(int id) {
        filmRepository = new HoursDetailFilmRepository(); // Khởi tạo repository
        //loadFilmList(); // Gọi API khi ViewModel được tạo
        fetchfilms(id);
    }

    public LiveData<FilmResource> getFilmLiveData() {
        return filmLiveData;
    }

    private void fetchfilms(int id) {
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        FilmService filmService = retrofit.create(FilmService.class);
        // Gọi API
        Call<FilmResource> call = filmService.getFilmsId(id);
        call.enqueue(new Callback<FilmResource>() {

            @Override
            public void onResponse(Call<FilmResource> call, retrofit2.Response<FilmResource> response) {
                if (response.isSuccessful()) {

                    // Lấy đối tượng ListFilm từ response.body()
                    FilmResource items = response.body();
                    filmLiveData.postValue(items);


                } else {

                    Log.e("Error", "Status Code: ");
                }
            }

            @Override
            public void onFailure(Call<FilmResource> call, Throwable t) {

            }

        });
    }
}
