package com.huce.t25film.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.huce.t25film.Adapters.DateListAdapter;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.databinding.ActivityHoursDetailFilmBinding;
import com.huce.t25film.model.Film;
import com.huce.t25film.model.Show;
import com.huce.t25film.resources.ShowDateResource;
import com.huce.t25film.resources.ShowDateResourceSort;
import com.huce.t25film.viewmodels.HoursDetailFilmViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HoursDetailFilmActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterHours;
    private ActivityHoursDetailFilmBinding binding;
    private HoursDetailFilmViewModel viewModel;
    private List<ShowDateResource> showByDates;
    private Film film;
    private int filmId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntent().putExtra("title","Xuất chiếu phim");
        binding = ActivityHoursDetailFilmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();

        filmId = getIntent().getIntExtra("filmId",0);

        viewModel = new ViewModelProvider(this).get(HoursDetailFilmViewModel.class);
        viewModel.getLoad().observe(this, load->
                binding.progressBarDetail.setVisibility(load));
        viewModel.getMessage().observe(this, message ->
                Toast.makeText(this,message,Toast.LENGTH_SHORT).show());

        viewModel.getFilmResource(this, filmId).observe(this, filmResource -> {
            if (filmResource == null) return;
            binding.progressBarDetail.setVisibility(View.GONE);
            binding.showTime.setVisibility(View.VISIBLE);
            binding.txtManhinh.setVisibility(View.VISIBLE);
            this.film = filmResource.getFilm();
            if (filmResource.getStatus().equals("success")){
                if(filmResource.getFilm().getShows() == null){
                    Toast.makeText(this,"Các ngày gần nhất không có xuất chiếu", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
                binding.movieNameDetailsTxt.setText(filmResource.getFilm().getName());
                binding.showTime.setText(filmResource.getFilm().getRuntime()+" phút");
            }else{
                Toast.makeText(this,filmResource.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // kiem tra dang nhap
        Log.e("state","start");
        if(SharedReferenceData.getInstance().getInt(this,"uid") == 0){
            Intent login = new Intent(this, Login1Activity.class);
            startActivity(login);
        }
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e("focus",hasFocus+"");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("state","destroy");
        viewModel.clearData();
    }

    private List<ShowDateResource> convertToShowtimeDateItems(List<Show> showtimes) {
        Map<String, List<Show>> dateShowtimeMap = new HashMap<>();

        // Tạo một map để nhóm các Showtime theo ngày
        for (Show showtime : showtimes) {
            String date = showtime.getDate();
            if (!dateShowtimeMap.containsKey(date)) {
                dateShowtimeMap.put(date, new ArrayList<>());
            }
            dateShowtimeMap.get(date).add(showtime);
        }

        // Tạo danh sách ShowtimeDateItem từ map
        List<ShowDateResource> showtimeDateItems = new ArrayList<>();
        for (Map.Entry<String, List<Show>> entry : dateShowtimeMap.entrySet()) {
            String date = entry.getKey();
            List<Show> showtimeList = entry.getValue();
            showtimeDateItems.add(new ShowDateResource(date, showtimeList));
        }

        return showtimeDateItems;
    }
    private void initView() {
        showByDates = new ArrayList<>();
        adapterHours = new DateListAdapter(this, this.showByDates);
        //recyclerViewHours.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        binding.recyclerviewHour.setLayoutManager(new GridLayoutManager(this,1));
        binding.recyclerviewHour.setAdapter(adapterHours);
        binding.showTime.setVisibility(View.GONE);
        binding.txtManhinh.setVisibility(View.GONE);
    }



    @Override
    // for stop refreshing UI, running animations and other visual things.
    protected void onStop() {
        super.onStop();
        Log.e("state","stop");

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("state","resume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("state","pause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("state","restart");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e("state","saveInstanceState");
    }

}