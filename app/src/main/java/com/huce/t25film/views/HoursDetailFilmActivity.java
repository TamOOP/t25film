package com.huce.t25film.views;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class HoursDetailFilmActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private RecyclerView.Adapter adapterHours;
    private RecyclerView recyclerViewHours;
    private ProgressBar progressBar;
    private TextView titleTxt,movieTimeTxt;
    private int id;
    private ImageView imgDetail,backImg;
    private NestedScrollView scrollView;
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
//        mRequestQueue = Volley.newRequestQueue(this);
//        progressBar.setVisibility(View.VISIBLE);
//        scrollView.setVisibility(View.GONE);
//
//        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                progressBar.setVisibility(View.GONE);
//                scrollView.setVisibility(View.VISIBLE);
//
//                FilmItem item = gson.fromJson(response,FilmItem.class);
//
//                //adapterHours = new ActorListAdapter(item.getGenres());
//                //recyclerViewHours.setAdapter(adapterHours);
//
//                //item coi như là FilmItem gọi ra
//                Glide.with(HoursDetailFilmActivity.this)
//                        .load(item.getPoster())
//                        .into(imgDetail);
//
//                titleTxt.setText(item.getTitle());
//                movieTimeTxt.setText(item.getRuntime());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressBar.setVisibility(View.GONE);
//            }
//        });
//        mRequestQueue.add(mStringRequest);
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        FilmService filmService = retrofit.create(FilmService.class);


        // Gọi API
        Call<FilmResource> call = filmService.getFilmsId(id);
        call.enqueue(new Callback<FilmResource>() {

            @Override
            public void onResponse(Call<FilmResource> call, retrofit2.Response<FilmResource> response) {
                if (response.isSuccessful()) {
                    // Ẩn loading khi nhận được dữ liệu
                    progressBar.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);


                    // Lấy đối tượng ListFilm từ response.body()
                    FilmResource item = response.body();
                    List<ShowDateResource> showtimeDateItems = convertToShowtimeDateItems(item.getFilm().getShows());

                    List<ShowDateResource> sortedShowtimeDateItems = ShowDateResourceSort.sortShowtimeDateItems(showtimeDateItems);
                    adapterHours = new DateListAdapter(sortedShowtimeDateItems);
                    //adapterHours = new DateListAdapter(item.getFilm().getShows());
                    recyclerViewHours.setAdapter(adapterHours);
                    //item coi như là FilmItem gọi ra
                    Glide.with(HoursDetailFilmActivity.this)
                            .load(item.getFilm().getImage())
                            .into(imgDetail);

                    titleTxt.setText(item.getFilm().getName());
                    movieTimeTxt.setText(item.getFilm().getRuntime()+" phút");

                } else {

                    // Xử lý khi phản hồi không thành công
                    //int statusCode = response.code();
                    //String errorBody = response.errorBody().string();
                    Log.e("Error", "Status Code: ");
                    progressBar.setVisibility(View.GONE);
                    // ...
                }
            }

            @Override
            public void onFailure(Call<FilmResource> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }

        });
    }

//    private List<ShowDateResource> convertToUniqueDateItems(List<Show> showtimes) {
//        Set<String> uniqueDates = new HashSet<>();
//        List<ShowDateResource> dateItems = new ArrayList<>();
//
//        for (Show showtime : showtimes) {
//            String date = showtime.getDate();
//
//            // Kiểm tra xem ngày đã được thêm vào danh sách chưa
//            if (!uniqueDates.contains(date)) {
//                uniqueDates.add(date);
//                List<Show> showtimesForDate = getShowtimesForDate(showtimes, date);
//                dateItems.add(new ShowDateResource(date, showtimesForDate));
//            }
//        }
//
//        return dateItems;
//    }
//
//    // Hàm lấy danh sách Showtime cho một ngày cụ thể
//    private List<Show> getShowtimesForDate(List<Show> showtimes, String date) {
//        List<Show> showtimesForDate = new ArrayList<>();
//
//        for (Show showtime : showtimes) {
//            if (date.equals(showtime.getDate())) {
//                showtimesForDate.add(showtime);
//            }
//        }
//
//        return showtimesForDate;
//    }


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