package com.huce.t25film.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.Adapters.CalendarFilmListAdapter;
import com.huce.t25film.Adapters.PromotionListAdapter;
import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.PromotionService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.ShowService;
import com.huce.t25film.model.Promotion;
import com.huce.t25film.repository.CalendarFilm1FragmentRepository;
import com.huce.t25film.repository.KmFragmentRepository;
import com.huce.t25film.resources.ShowResource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class KmFragmentViewModel extends ViewModel {
    private MutableLiveData<List<Promotion>> KMLiveData = new MutableLiveData<>();
    private KmFragmentRepository kmRepository; // Tùy chọn: nơi bạn thực hiện gọi API
    private RecyclerView.Adapter adapterKm;


    private PromotionService promotionService;


    public KmFragmentViewModel() {
        kmRepository = new KmFragmentRepository(); // Khởi tạo repository
        //loadFilmList(); // Gọi API khi ViewModel được tạo
        fetchpromotion();
    }

    public LiveData<List<Promotion>> getKmLiveData() {
        return KMLiveData;
    }

    private void fetchpromotion() {
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        PromotionService KmService = retrofit.create(PromotionService.class);

        // Gọi API
        Call<List<Promotion>> call = KmService.getListPromotions();
        call.enqueue(new Callback<List<Promotion>>() {

            @Override
            public void onResponse(Call<List<Promotion>> call, retrofit2.Response<List<Promotion>> response) {
                if (response.isSuccessful()) {

                    // Lấy đối tượng ListFilm từ response.body()
                    List<Promotion> items = response.body();
                    KMLiveData.postValue(items);
                    // Tạo Adapter và thiết lập RecyclerView
                    adapterKm = new PromotionListAdapter(items);
                } else {

                    Log.e("Error", "Status Code: ");
                }
            }

            @Override
            public void onFailure(Call<List<Promotion>> call, Throwable t) {

            }

        });
    }
}
