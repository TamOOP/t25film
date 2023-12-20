package com.huce.t25film.views;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.huce.t25film.Adapters.CalendarFilmListAdapter;
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
import com.huce.t25film.model.UserDataHolder;
import com.huce.t25film.resources.ShowResource;
import com.huce.t25film.viewmodels.CalendarFilm3FragmentViewModel;
import com.huce.t25film.viewmodels.KmFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class KmFragment extends Fragment {
    private RecyclerView.Adapter adapterKM;
    private RecyclerView recyclerViewKM;
    private ProgressBar loading;
    private KmFragmentViewModel KmFragmentViewModel;





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
        KmFragmentViewModel = new KmFragmentViewModel();

        // Quan sát LiveData để cập nhật UI khi có dữ liệu mới
        KmFragmentViewModel.getKmLiveData().observe(getViewLifecycleOwner(), new Observer<List<Promotion>>() {
            @Override
            public void onChanged(List<Promotion> kmResource) {
                // Cập nhật dữ liệu trong Adapter và thông báo thay đổi
                adapterKM=new PromotionListAdapter(kmResource);
                adapterKM.notifyDataSetChanged();

                loading.setVisibility(View.GONE);
                recyclerViewKM.setAdapter(adapterKM);
            }
        });
    }

}