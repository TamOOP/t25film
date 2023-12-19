package com.huce.t25film.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.huce.t25film.R;
import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.resources.FilmResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class DetailFilmActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView titleTxt,movieTimeTxt,movieSummaryInfo,movieActorsInfo;
    private int id;
    private ImageView imgDetail,backImg;
    private NestedScrollView scrollView;
    private Button btnHour;
    private com.huce.t25film.viewmodels.DetailViewModel DetailViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        id=getIntent().getIntExtra("id",0);
        initView();
        sendRequest();
    }

    //gọi yêu cầu lên API
    private void sendRequest() {
        //mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

//        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                progressBar.setVisibility(View.GONE);
//                scrollView.setVisibility(View.VISIBLE);
//
//                FilmItem item = gson.fromJson(response,FilmItem.class);
//
//                //item coi như là FilmItem gọi ra
//                Glide.with(DetailFilmActivity.this)
//                        .load(item.getPoster())
//                        .into(imgDetail);
//
//                titleTxt.setText(item.getTitle());
//                movieTimeTxt.setText(item.getRuntime());
//                movieSummaryInfo.setText(item.getPlot());
//                movieActorsInfo.setText(item.getActors());
//
//                btnHour.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(DetailFilmActivity.this, HoursDetailFilmActivity.class);
//                        intent.putExtra("id",item.getId());
//                        startActivity(intent);
//                    }
//                });
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressBar.setVisibility(View.GONE);
//            }
//        });
//        mRequestQueue.add(mStringRequest);
        // Khởi tạo Retrofit Client
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

                    //item coi như là FilmItem gọi ra
                    Glide.with(DetailFilmActivity.this)
                            .load(item.getFilm().getImage())
                            .into(imgDetail);

                    titleTxt.setText(item.getFilm().getName());
                    movieTimeTxt.setText(item.getFilm().getRuntime()+" phút");
                    movieSummaryInfo.setText(item.getFilm().getDescription());
                    movieActorsInfo.setText(item.getFilm().getActor());

//                    Integer filmId = getIntent().getIntExtra("id",0);
//                    DetailViewModel.loadFilm(filmId);
                    btnHour.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(DetailFilmActivity.this, HoursDetailFilmActivity.class);
                            intent.putExtra("id",item.getFilm().getId());
                            startActivity(intent);
                        }
                    });
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
//        DetailViewModel = new DetailViewModel();
//
//        // Quan sát LiveData để cập nhật UI khi có dữ liệu mới
//        DetailViewModel.getFilmLiveData().observe(getViewLifecycleOwner(), new Observer<Film>() {
//            @Override
//            public void onChanged(Film film) {
//                // Cập nhật dữ liệu trong Adapter và thông báo thay đổi
//                adapterMovies=new FilmListAdapter(film);
//                adapterMovies.notifyDataSetChanged();
//            }
//        });
    }

    private void initView() {
        titleTxt=findViewById(R.id.movieNameDetailsTxt);
        progressBar=findViewById(R.id.progressBarDetail);
        scrollView=findViewById(R.id.scrollViewDetails);
        imgDetail=findViewById(R.id.imgFilmDetails);
        movieTimeTxt=findViewById(R.id.showTime);
        movieSummaryInfo=findViewById(R.id.movieSummery);
        movieActorsInfo=findViewById(R.id.movieActorInfo);
        backImg=findViewById(R.id.btnBack);
        btnHour=findViewById(R.id.button2);



        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}