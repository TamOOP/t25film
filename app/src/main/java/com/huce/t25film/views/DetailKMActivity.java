package com.huce.t25film.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.huce.t25film.R;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.Utils.NetworkUtils;
import com.huce.t25film.resources.PromotionResource;
import com.huce.t25film.viewmodels.DetailKmViewModel;

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
//        initView();
//        sendRequest();
        initView();

        if (NetworkUtils.isNetworkAvailable(this)) {
            sendRequest();
        } else {
            Toast.makeText(this, "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("state","start");
        // kiem tra dang nhap
        if(SharedReferenceData.getInstance().getInt(this,"uid") == 0){
            Intent login = new Intent(this, Login1Activity.class);
            startActivity(login);
            finish();
        }
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