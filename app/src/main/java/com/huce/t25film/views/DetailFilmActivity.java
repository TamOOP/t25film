package com.huce.t25film.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.huce.t25film.R;
import com.huce.t25film.Utils.NetworkUtils;
import com.huce.t25film.resources.FilmResource;
import com.huce.t25film.viewmodels.DetailFilmViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailFilmActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView titleTxt,movieTimeTxt,movieSummaryInfo,movieActorsInfo;
    private int id;
    private ImageView imgDetail,backImg;
    private NestedScrollView scrollView;
    private Button btnHour;
    private DetailFilmViewModel DetailFilmViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);
        initView();

        id=getIntent().getIntExtra("id",0);
        if (NetworkUtils.isNetworkAvailable(this)) {
            sendRequest();
        } else {
            Toast.makeText(this, "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
        }
    }

    //gọi yêu cầu lên API
    private void sendRequest() {
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        DetailFilmViewModel = new DetailFilmViewModel(id);

        // Quan sát LiveData để cập nhật UI khi có dữ liệu mới
        DetailFilmViewModel.getFilmLiveData().observe(this, new Observer<FilmResource>() {
            @Override
            public void onChanged(FilmResource showResource) {
                // Cập nhật dữ liệu trong Adapter và thông báo thay đổi
                    progressBar.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);


                    //item coi như là FilmItem gọi ra
                    Glide.with(DetailFilmActivity.this)
                            .load(showResource.getFilm().getImage())
                            .into(imgDetail);

                    titleTxt.setText(showResource.getFilm().getName());
                    movieTimeTxt.setText(showResource.getFilm().getRuntime()+" phút");
                    movieSummaryInfo.setText(showResource.getFilm().getDescription());
                    movieActorsInfo.setText(showResource.getFilm().getActor());

                    btnHour.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(DetailFilmActivity.this, HoursDetailFilmActivity.class);
                            intent.putExtra("filmId",showResource.getFilm().getId());
                            startActivity(intent);
                        }
                    });
            }
        });
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