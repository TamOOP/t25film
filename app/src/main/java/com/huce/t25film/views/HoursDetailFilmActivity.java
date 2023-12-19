package com.huce.t25film.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.R;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.databinding.ActivityHoursDetailFilmBinding;
import com.huce.t25film.model.Show;
import com.huce.t25film.viewmodels.HoursDetailFilmViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HoursDetailFilmActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterHours;
    private RecyclerView recyclerViewHours;
    private int showId;
    private ActivityHoursDetailFilmBinding binding;
    HoursDetailFilmViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHoursDetailFilmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showId=getIntent().getIntExtra("showId",0);

        viewModel = new ViewModelProvider(this).get(HoursDetailFilmViewModel.class);
        viewModel.getFilmResource(showId).observe(this, filmResource -> {

        });
//        initView();

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

    private Map<String, List<Show>> convertToShowtimeDateItems(List<Show> showtimes) {
        Map<String, List<Show>> dateShowtimeMap = new HashMap<>();

        // Tạo một map để nhóm các Showtime theo ngày
        for (Show showtime : showtimes) {
            String date = showtime.getDate();
            if (!dateShowtimeMap.containsKey(date)) {
                dateShowtimeMap.put(date, new ArrayList<>());
            }
            dateShowtimeMap.get(date).add(showtime);
        }

        return dateShowtimeMap;
    }




    private void initView() {
        recyclerViewHours=findViewById(R.id.recyclerviewHour);
        //recyclerViewHours.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerViewHours.setLayoutManager(new GridLayoutManager(this,1));
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}