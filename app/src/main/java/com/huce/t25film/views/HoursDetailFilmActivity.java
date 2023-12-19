package com.huce.t25film.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.huce.t25film.Adapters.DateListAdapter;
import com.huce.t25film.R;
import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.model.Show;
import com.huce.t25film.resources.FilmResource;
import com.huce.t25film.resources.ShowDateResource;
import com.huce.t25film.resources.ShowDateResourceSort;
import com.huce.t25film.viewmodels.DetailFilmViewModel;
import com.huce.t25film.viewmodels.HoursDetailFilmViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class HoursDetailFilmActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterHours;
    private RecyclerView recyclerViewHours;
    private ProgressBar progressBar;
    private TextView titleTxt,movieTimeTxt;
    private int id;
    private ImageView imgDetail,backImg;
    private NestedScrollView scrollView;
    HoursDetailFilmViewModel hoursDetailFilmViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours_detail_film);

        id=getIntent().getIntExtra("id",0);
        initView();
        sendRequest();
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
    //gọi yêu cầu lên API
    private void sendRequest() {

        hoursDetailFilmViewModel = new HoursDetailFilmViewModel(id);

        // Quan sát LiveData để cập nhật UI khi có dữ liệu mới
        hoursDetailFilmViewModel.getFilmLiveData().observe(this, new Observer<FilmResource>() {
            @Override
            public void onChanged(FilmResource showResource) {
                // Cập nhật dữ liệu trong Adapter và thông báo thay đổi
                // Ẩn loading khi nhận được dữ liệu
                    progressBar.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);

                    List<ShowDateResource> showtimeDateItems = convertToShowtimeDateItems(showResource.getFilm().getShows());

                    List<ShowDateResource> sortedShowtimeDateItems = ShowDateResourceSort.sortShowtimeDateItems(showtimeDateItems);
                    adapterHours = new DateListAdapter(sortedShowtimeDateItems);
                    //adapterHours = new DateListAdapter(item.getFilm().getShows());
                    recyclerViewHours.setAdapter(adapterHours);
                    //item coi như là FilmItem gọi ra
                    Glide.with(HoursDetailFilmActivity.this)
                            .load(showResource.getFilm().getImage())
                            .into(imgDetail);

                    titleTxt.setText(showResource.getFilm().getName());
                    movieTimeTxt.setText(showResource.getFilm().getRuntime()+" phút");
            }
        });
    }




    private void initView() {
        titleTxt=findViewById(R.id.movieNameDetailsTxt);
        progressBar=findViewById(R.id.progressBarDetail);
        scrollView=findViewById(R.id.scrollViewDetails);
        imgDetail=findViewById(R.id.imgFilmDetails);
        movieTimeTxt=findViewById(R.id.showTime);
        backImg=findViewById(R.id.btnBack);
        recyclerViewHours=findViewById(R.id.recyclerviewHour);
        //recyclerViewHours.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerViewHours.setLayoutManager(new GridLayoutManager(this,1));
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}