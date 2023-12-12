package com.huce.t25film.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huce.t25film.R;
import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.PromotionService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.model.Promotion;
import com.huce.t25film.resources.FilmResource;
import com.huce.t25film.resources.PromotionResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class DetailKMActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView titleTxt,contentTxt,codeTxt,DateStartTxt,DateEndTxt;
    private int id;
    private ImageView imgDetail,backImg;
    private NestedScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kmactivity);

        id=getIntent().getIntExtra("id",0);
        initView();
        sendRequest();
    }

    private void sendRequest() {
        //mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        // Khởi tạo Retrofit Client
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        PromotionService filmService = retrofit.create(PromotionService.class);

        // Gọi API
        Call<PromotionResource> call = filmService.getPromotionsId(id);
        call.enqueue(new Callback<PromotionResource>() {

            @Override
            public void onResponse(Call<PromotionResource> call, retrofit2.Response<PromotionResource> response) {
                if (response.isSuccessful()) {
                    // Ẩn loading khi nhận được dữ liệu
                    progressBar.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);


                    // Lấy đối tượng ListFilm từ response.body()
                    PromotionResource item = response.body();

                    //item coi như là FilmItem gọi ra
                    Glide.with(DetailKMActivity.this)
                            .load(item.getPromotion().getImage())
                            .into(imgDetail);

                    titleTxt.setText(item.getPromotion().getTitle());
                    contentTxt.setText(item.getPromotion().getDescription());
                    codeTxt.setText("Mã code:"+item.getPromotion().getCode());
                    DateStartTxt.setText("Ngày bắt đầu: "+item.getPromotion().getStartDate());
                    DateEndTxt.setText("Ngày kết thúc: "+item.getPromotion().getEndDate());

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
            public void onFailure(Call<PromotionResource> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }

        });
    }

    private void initView() {
        titleTxt=findViewById(R.id.KMNameDetailsTxt);
        progressBar=findViewById(R.id.progressBarDetail);
        scrollView=findViewById(R.id.scrollViewDetails);
        imgDetail=findViewById(R.id.imgFilmDetails);
        contentTxt=findViewById(R.id.txtContentDetailKM);
        codeTxt=findViewById(R.id.txtCodeKM);
        DateStartTxt=findViewById(R.id.txtNgayBDKM);
        DateEndTxt=findViewById(R.id.txtNgayKTKM);
        backImg=findViewById(R.id.btnBack);



        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}