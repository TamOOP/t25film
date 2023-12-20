package com.huce.t25film.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.Adapters.DateListAdapter;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.databinding.ActivityHoursDetailFilmBinding;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHoursDetailFilmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();

        int filmId = getIntent().getIntExtra("filmId",0);
        filmId = 2;

        viewModel = new ViewModelProvider(this).get(HoursDetailFilmViewModel.class);
        viewModel.getFilmResource(filmId).observe(this, filmResource -> {
            if (filmResource == null) return;
            if (filmResource.getStatus().equals("success")){
                List<ShowDateResource> showtimeDateItems = convertToShowtimeDateItems(filmResource.getFilm().getShows());
                List<ShowDateResource> sortedShowtimeDateItems = ShowDateResourceSort.sortShowtimeDateItems(showtimeDateItems);

                this.showByDates.clear();
                this.showByDates.addAll(sortedShowtimeDateItems);
                adapterHours.notifyDataSetChanged();

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
        if(SharedReferenceData.getInstance().getInt(this,"uid") == 0){
            Intent login = new Intent(this, Login1Activity.class);
            startActivity(login);
        }
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
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}