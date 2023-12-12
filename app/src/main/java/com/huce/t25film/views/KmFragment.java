package com.huce.t25film.views;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.huce.t25film.Adapters.FilmListAdapter;
import com.huce.t25film.Adapters.KMListAdapter;
import com.huce.t25film.Adapters.PromotionListAdapter;
import com.huce.t25film.Adapters.SliderAdapters;
import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.PromotionService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.model.Film;
import com.huce.t25film.model.ListFilm;
import com.huce.t25film.model.Promotion;
import com.huce.t25film.model.SilderItems;
import com.huce.t25film.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class KmFragment extends Fragment {
    private RecyclerView.Adapter adapterKM;
    private RecyclerView recyclerViewKM;
    private ProgressBar loading;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_km, container, false);



        recyclerViewKM=view.findViewById(R.id.recyclerviewKM);
        recyclerViewKM.setLayoutManager(new GridLayoutManager(requireContext(),2));
        loading=view.findViewById(R.id.progressBarKM);


        sendRequest();


        return view;
    }
    private void sendRequest(){
        // Khởi tạo Retrofit Client
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        PromotionService promotionService = retrofit.create(PromotionService.class);

        // Gọi API
        Call<List<Promotion>> call = promotionService.getListPromotions();
        call.enqueue(new Callback<List<Promotion>>() {

            @Override
            public void onResponse(Call<List<Promotion>> call, retrofit2.Response<List<Promotion>> response) {
                if (response.isSuccessful()) {
                    // Ẩn loading khi nhận được dữ liệu
                    loading.setVisibility(View.GONE);

                    // Lấy đối tượng ListFilm từ response.body()
                    List<Promotion> items = response.body();


                    // Tạo Adapter và thiết lập RecyclerView
                    adapterKM = new PromotionListAdapter(items);
                    recyclerViewKM.setAdapter(adapterKM);
                } else {

                    // Xử lý khi phản hồi không thành công
                    //int statusCode = response.code();
                    //String errorBody = response.errorBody().string();
                    Log.e("Error", "Status Code: ");
                    // ...
                }
            }

            @Override
            public void onFailure(Call<List<Promotion>> call, Throwable t) {

            }

        });
    }

}