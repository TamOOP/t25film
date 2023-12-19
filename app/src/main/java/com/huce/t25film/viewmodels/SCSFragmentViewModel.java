package com.huce.t25film.viewmodels;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.Adapters.FilmListAdapter;
import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.model.Film;
import com.huce.t25film.repository.SCFragmentRepository;
import com.huce.t25film.resources.FilmResource;
import com.huce.t25film.repository.SCSFragmentRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class SCSFragmentViewModel extends ViewModel {
    private MutableLiveData<List<Film>> filmListLiveData = new MutableLiveData<>();
    private SCSFragmentRepository filmRepository; // Tùy chọn: nơi bạn thực hiện gọi API
    private RecyclerView.Adapter adapterMovies;


    private FilmService filmService;


    public SCSFragmentViewModel() {
        filmRepository = new SCSFragmentRepository(); // Khởi tạo repository
        //loadFilmList(); // Gọi API khi ViewModel được tạo
        fetchFilms();
    }

    public LiveData<List<Film>> getFilmListLiveData() {
        return filmListLiveData;
    }

    private void fetchFilms() {
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        FilmService filmService = retrofit.create(FilmService.class);

        // Gọi API
        Call<List<Film>> call = filmService.getListFilmsSCS();
        call.enqueue(new Callback<List<Film>>() {

            @Override
            public void onResponse(Call<List<Film>> call, retrofit2.Response<List<Film>> response) {
                if (response.isSuccessful()) {

                    // Lấy đối tượng ListFilm từ response.body()
                    List<Film> items = response.body();
                    filmListLiveData.postValue(items);


                    // Tạo Adapter và thiết lập RecyclerView
                    adapterMovies = new FilmListAdapter(items);
                } else {

                    Log.e("Error", "Status Code: ");
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {

            }

        });
    }
}
