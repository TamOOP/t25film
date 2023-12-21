package com.huce.t25film.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.Adapters.CalendarFilmListAdapter;
import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.ShowService;
import com.huce.t25film.repository.CalendarFilm2FragmentRepository;
import com.huce.t25film.resources.ShowResource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class CalendarFilm2FragmentViewModel extends ViewModel {
    private MutableLiveData<ShowResource> showLiveData = new MutableLiveData<>();
    private CalendarFilm2FragmentRepository showRepository; // Tùy chọn: nơi bạn thực hiện gọi API
    private RecyclerView.Adapter adapterMovies;


    private FilmService filmService;


    public CalendarFilm2FragmentViewModel(Context context) {
        showRepository = new CalendarFilm2FragmentRepository(); // Khởi tạo repository
        //loadFilmList(); // Gọi API khi ViewModel được tạo
        fetchshows(context);
    }

    public LiveData<ShowResource> getShowLiveData() {
        return showLiveData;
    }

    private void fetchshows(Context context) {
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        ShowService showService = retrofit.create(ShowService.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());

        // Lấy ngày kia
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date tomorrow1 = calendar.getTime();

        String formattedDate2 = dateFormat.format(tomorrow1);

        // Gọi API
        Call<ShowResource> call = showService.getShowsId(formattedDate2);
        call.enqueue(new Callback<ShowResource>() {

            @Override
            public void onResponse(Call<ShowResource> call, retrofit2.Response<ShowResource> response) {
                if (response.isSuccessful()) {

                    // Lấy đối tượng ListFilm từ response.body()
                    ShowResource items = response.body();
                    showLiveData.postValue(items);


                    // Tạo Adapter và thiết lập RecyclerView
                    adapterMovies = new CalendarFilmListAdapter(context, items);
                } else {

                    Log.e("Error", "Status Code: ");
                }
            }

            @Override
            public void onFailure(Call<ShowResource> call, Throwable t) {
                Log.e("Error", t.getMessage());
                fetchshows(context);
            }

        });
    }
}
