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

import com.huce.t25film.R;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.Utils.NetworkUtils;
import com.huce.t25film.resources.TicketResource;
import com.huce.t25film.viewmodels.DetailTicketViewModel;

public class DetailTicketActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView TicketNameDetailsTxt,txtNameUserTicket,txtDateTicket,txtTimeTicket,txtSeatTicket,txtCinemaTicket,txtCodeTicket;
    private int id,uid;
    private ImageView backImg;
    private NestedScrollView scrollView;
    DetailTicketViewModel DetailticketViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ticket);

        uid = SharedReferenceData.getInstance().getInt(this,"uid");
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

        DetailticketViewModel = new DetailTicketViewModel(id,uid);

        // Quan sát LiveData để cập nhật UI khi có dữ liệu mới
        DetailticketViewModel.getticketLiveData().observe(this, new Observer<TicketResource>() {
            @Override
            public void onChanged(TicketResource ticketResource) {
                // Cập nhật dữ liệu trong Adapter và thông báo thay đổi
                progressBar.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);


                TicketNameDetailsTxt.setText(ticketResource.getTicket().getShow().getCinema().getName());
                txtNameUserTicket.setText("Tên khách hàng: "+ticketResource.getTicket().getUser().getName());
                txtDateTicket.setText("Ngày: "+ticketResource.getTicket().getShow().getDate() + ", "+ticketResource.getTicket().getShow().getDayOfWeek());
                txtTimeTicket.setText("Giờ: "+ticketResource.getTicket().getShow().getTime());
                txtSeatTicket.setText("Ghế: "+ticketResource.getTicket().getSeat());
                txtCinemaTicket.setText("Phòng: "+ticketResource.getTicket().getShow().getCinema().getIdphong());
                txtCodeTicket.setText("Mã vé phim: "+ticketResource.getTicket().getShow().getId());
            }
        });
    }

    private void initView() {
        TicketNameDetailsTxt=findViewById(R.id.TicketNameDetailsTxt);
        progressBar=findViewById(R.id.progressBarDetail);
        scrollView=findViewById(R.id.scrollViewDetails);
        txtNameUserTicket=findViewById(R.id.txtNameUserTicket);
        txtTimeTicket=findViewById(R.id.txtTimeTicket);
        txtSeatTicket=findViewById(R.id.txtSeatTicket);
        txtCinemaTicket=findViewById(R.id.txtCinemaTicket);
        txtCodeTicket=findViewById(R.id.txtCodeTicket);
        txtDateTicket=findViewById(R.id.txtDateTicket);
        backImg=findViewById(R.id.btnBack);



        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}