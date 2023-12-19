package com.huce.t25film.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.ShowService;
import com.huce.t25film.model.ListFilm;
import com.huce.t25film.R;
import com.huce.t25film.resources.ShowResource;
import com.huce.t25film.viewmodels.CalendarFilm1FragmentViewModel;
import com.huce.t25film.viewmodels.CalendarFilm2FragmentViewModel;
import com.huce.t25film.viewmodels.CalendarFilm3FragmentViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;


public class CalendarFilm3Fragment extends Fragment {

    private RecyclerView.Adapter adapterMovies;
    private RecyclerView recyclerViewMovies;
    private ProgressBar loading;
    private CalendarFilm3FragmentViewModel CalendarFilm3ViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_film3, container, false);



        //recyclerView Movies

        recyclerViewMovies=view.findViewById(R.id.recyclerviewFilmCal);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(requireContext(),1));
        loading=view.findViewById(R.id.progressBarFilmCal);

        sendRequest();


        return view;
    }
    private void sendRequest(){
        CalendarFilm3ViewModel = new CalendarFilm3FragmentViewModel();

        // Quan sát LiveData để cập nhật UI khi có dữ liệu mới
        CalendarFilm3ViewModel.getShowLiveData().observe(getViewLifecycleOwner(), new Observer<ShowResource>() {
            @Override
            public void onChanged(ShowResource showResource) {
                // Cập nhật dữ liệu trong Adapter và thông báo thay đổi
                adapterMovies=new CalendarFilmListAdapter(showResource);
                adapterMovies.notifyDataSetChanged();

                loading.setVisibility(View.GONE);
                recyclerViewMovies.setAdapter(adapterMovies);
            }
        });
    }
}