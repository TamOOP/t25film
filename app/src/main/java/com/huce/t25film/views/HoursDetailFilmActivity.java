package com.huce.t25film.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
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
import com.huce.t25film.Adapters.ActorListAdapter;
import com.huce.t25film.model.FilmItem;
import com.huce.t25film.R;

public class HoursDetailFilmActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private RecyclerView.Adapter adapterHours;
    private RecyclerView recyclerViewHours;
    private ProgressBar progressBar;
    private TextView titleTxt,movieTimeTxt;
    private int idFilm;
    private ImageView imgDetail,backImg;
    private NestedScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours_detail_film);

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

                adapterHours = new ActorListAdapter(item.getGenres());
                recyclerViewHours.setAdapter(adapterHours);

                //item coi như là FilmItem gọi ra
                Glide.with(HoursDetailFilmActivity.this)
                        .load(item.getPoster())
                        .into(imgDetail);

                titleTxt.setText(item.getTitle());
                movieTimeTxt.setText(item.getRuntime());
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
        titleTxt=findViewById(R.id.movieNameDetailsTxt);
        progressBar=findViewById(R.id.progressBarDetail);
        scrollView=findViewById(R.id.scrollViewDetails);
        imgDetail=findViewById(R.id.imgFilmDetails);
        movieTimeTxt=findViewById(R.id.movieTimeDetails);
        backImg=findViewById(R.id.btnBack);
        recyclerViewHours=findViewById(R.id.recyclerviewHour);
        recyclerViewHours.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}