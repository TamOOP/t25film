package com.huce.t25film.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.huce.t25film.Adapters.PromotionListAdapter;
import com.huce.t25film.Adapters.TicketListAdapter;
import com.huce.t25film.R;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.model.Promotion;
import com.huce.t25film.model.Ticket;
import com.huce.t25film.viewmodels.KmFragmentViewModel;
import com.huce.t25film.viewmodels.TicketViewModel;

import java.util.List;

public class LSGDActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterticket;
    private RecyclerView recyclerViewticket;
    private ProgressBar loading;
    private TicketViewModel ticketViewModel;
    private int uid;
    ConstraintLayout constraintLayoutBackLSGD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsgdactivity);

        constraintLayoutBackLSGD=findViewById(R.id.constraintLayoutBackLSGD);
        recyclerViewticket=findViewById(R.id.recyclerviewLSGD);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); // this là Context
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewticket.setLayoutManager(layoutManager);

        loading=findViewById(R.id.progressBarLSGD);
        uid = SharedReferenceData.getInstance().getInt(this,"uid");

        constraintLayoutBackLSGD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(LSGDActivity.this, HomeActivity.class);
                startActivity(intent1);
            }
        });
        sendRequest();
    }

    private void sendRequest() {
        loading.setVisibility(View.VISIBLE);

        ticketViewModel = new TicketViewModel(uid);

        // Quan sát LiveData để cập nhật UI khi có dữ liệu mới
        ticketViewModel.getticketLiveData().observe(this, new Observer<List<Ticket>>() {
            @Override
            public void onChanged(List<Ticket> ticketResource) {
                // Cập nhật dữ liệu trong Adapter và thông báo thay đổi
                adapterticket=new TicketListAdapter(ticketResource);
                adapterticket.notifyDataSetChanged();

                loading.setVisibility(View.GONE);
                recyclerViewticket.setAdapter(adapterticket);
            }
        });
    }
}