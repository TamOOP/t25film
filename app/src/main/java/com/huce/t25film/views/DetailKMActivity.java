package com.huce.t25film.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;

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
import com.huce.t25film.viewmodels.DetailFilmViewModel;
import com.huce.t25film.viewmodels.DetailKmViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class DetailKMActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView titleTxt,contentTxt,codeTxt,DateStartTxt,DateEndTxt;
    private int id;
    private ImageView imgDetail,backImg;
    private NestedScrollView scrollView;
    DetailKmViewModel DetailKmViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kmactivity);

        id=getIntent().getIntExtra("id",0);
        initView();
        sendRequest();
    }

    private void sendRequest() {
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        DetailKmViewModel = new DetailKmViewModel(id);

        // Quan sát LiveData để cập nhật UI khi có dữ liệu mới
        DetailKmViewModel.getKmLiveData().observe(this, new Observer<PromotionResource>() {
            @Override
            public void onChanged(PromotionResource kmResource) {
                // Cập nhật dữ liệu trong Adapter và thông báo thay đổi
                progressBar.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);

                //item coi như là FilmItem gọi ra
                Glide.with(DetailKMActivity.this)
                        .load(kmResource.getPromotion().getImage())
                        .into(imgDetail);

                titleTxt.setText(kmResource.getPromotion().getTitle());
                contentTxt.setText(kmResource.getPromotion().getDescription());
                codeTxt.setText("Mã code:"+kmResource.getPromotion().getCode());
                DateStartTxt.setText("Ngày bắt đầu: "+kmResource.getPromotion().getStartDate());
                DateEndTxt.setText("Ngày kết thúc: "+kmResource.getPromotion().getEndDate());
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