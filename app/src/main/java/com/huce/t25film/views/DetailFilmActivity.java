package com.huce.t25film.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huce.t25film.model.FilmItem;
import com.huce.t25film.R;

public class DetailFilmActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView titleTxt,movieTimeTxt,movieSummaryInfo,movieActorsInfo;
    private int idFilm;
    private ImageView imgDetail,backImg;
    private NestedScrollView scrollView;
    private Button btnHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        idFilm=getIntent().getIntExtra("id",0);
        initView();
        sendRequest();
    }

    //gọi yêu cầu lên API
    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                progressBar.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);

                FilmItem item = gson.fromJson(response,FilmItem.class);

                //item coi như là FilmItem gọi ra
                Glide.with(DetailFilmActivity.this)
                        .load(item.getPoster())
                        .into(imgDetail);

                titleTxt.setText(item.getTitle());
                movieTimeTxt.setText(item.getRuntime());
                movieSummaryInfo.setText(item.getPlot());
                movieActorsInfo.setText(item.getActors());

                btnHour.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DetailFilmActivity.this, HoursDetailFilmActivity.class);
                        intent.putExtra("id",item.getId());
                        startActivity(intent);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
            }
        });
        mRequestQueue.add(mStringRequest);
    }

    private void initView() {
        titleTxt=findViewById(R.id.movieNameSeatsTxt);
        progressBar=findViewById(R.id.progressBarDetail);
        scrollView=findViewById(R.id.scrollViewSeats);
        imgDetail=findViewById(R.id.imgFilmSeats);
        movieTimeTxt=findViewById(R.id.movieTimeSeats);
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