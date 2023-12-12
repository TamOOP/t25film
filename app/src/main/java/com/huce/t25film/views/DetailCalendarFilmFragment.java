package com.huce.t25film.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.huce.t25film.Adapters.CalendarFilmListAdapter;
import com.huce.t25film.Adapters.FilmListAdapter;
import com.huce.t25film.Adapters.SliderAdapters;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.ShowService;
import com.huce.t25film.model.ListFilm;
import com.huce.t25film.model.SilderItems;
import com.huce.t25film.R;
import com.huce.t25film.resources.ShowResource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class DetailCalendarFilmFragment extends Fragment {
    private RecyclerView.Adapter adapterMovies;
    private RecyclerView recyclerViewMovies;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar loading;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_calendar_film, container, false);



        //recyclerView Movies

        recyclerViewMovies=view.findViewById(R.id.recyclerviewFilmCal);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(requireContext(),1));
        loading=view.findViewById(R.id.progressBarFilmCal);

        sendRequest();


        return view;
    }
    private void sendRequest(){
        // Khởi tạo Retrofit Client
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        ShowService showService = retrofit.create(ShowService.class);


        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());

        // Lấy ngày hôm nay
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 0);
        Date tomorrow = calendar.getTime();

        String formattedDate2 = dateFormat.format(tomorrow);

        // Gọi API
        Call<ShowResource> call = showService.getShowsId(formattedDate2);
        call.enqueue(new Callback<ShowResource>() {

            @Override
            public void onResponse(Call<ShowResource> call, retrofit2.Response<ShowResource> response) {
                if (response.isSuccessful()) {
                    // Ẩn loading khi nhận được dữ liệu
                    loading.setVisibility(View.GONE);

                    // Lấy đối tượng ListFilm từ response.body()
                    ShowResource item = response.body();


                    // Tạo Adapter và thiết lập RecyclerView
                    adapterMovies = new CalendarFilmListAdapter(item);
                    recyclerViewMovies.setAdapter(adapterMovies);
                } else {

                    // Xử lý khi phản hồi không thành công
                    //int statusCode = response.code();
                    //String errorBody = response.errorBody().string();
                    Log.e("Error", "Status Code: ");
                    // ...
                }
            }

            @Override
            public void onFailure(Call<ShowResource> call, Throwable t) {

            }

        });
    }
}