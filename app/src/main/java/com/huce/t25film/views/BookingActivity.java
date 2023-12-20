package com.huce.t25film.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.Adapters.SeatAdapter;
import com.huce.t25film.R;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.databinding.ActivityBookingBinding;
import com.huce.t25film.model.Seat;
import com.huce.t25film.viewmodels.BookingViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity{
    private int showId;
    private ActivityBookingBinding binding;
    private RecyclerView recyclerViewSeats;

    private List<Seat> seats = new ArrayList<>();
    private SeatAdapter seatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        showId = this.getIntent().getIntExtra("showId", 0);
        binding.movieName.setText(getIntent().getStringExtra("name"));
        binding.runTime.setText(getIntent().getStringExtra("runtime"));
        binding.showTime.setText(getIntent().getStringExtra("day_of_week")+" "
                +getIntent().getStringExtra("date")+" "
                +getIntent().getStringExtra("time"));
        showId = 3;
        if( showId == 0) finish();

        BookingViewModel bookingViewModel = new BookingViewModel();
        binding.navBar.setVisibility(View.GONE);

        int uid = SharedReferenceData.getInstance().getInt(this,"uid");
        Log.e("uid",uid+"");

        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.btnBackSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bookingViewModel.getCinemaInfo(showId).observe(this, cinemaResource->{
            if(cinemaResource.getStatus().equals("success")){
                binding.progressBarBooking.setVisibility(View.GONE);
                seats.addAll(cinemaResource.getCinema().getSeats());
                seatAdapter = new SeatAdapter(this, this.seats, bookingViewModel);
                recyclerViewSeats = findViewById(R.id.recyclerviewViewSeats);
                recyclerViewSeats.setLayoutManager(new GridLayoutManager(this, cinemaResource.getCinema().getSeatPerRow()));
                recyclerViewSeats.setAdapter(seatAdapter);

            }
            else{
                Toast.makeText(this, "Không tìm thấy xuất chiếu hoặc phòng chiếu", Toast.LENGTH_SHORT);
            }
        });

        bookingViewModel.getSeatSelected().observe(this, seatSelected->{
            if(bookingViewModel.getTotalPrice() == 0){
                binding.navBar.setVisibility(View.GONE);
            }else{
                binding.navBar.setVisibility(View.VISIBLE);
                binding.txtSeatData.setText(bookingViewModel.getSeatNameSelected());
                binding.txtTotalData.setText(String.format("%,d", bookingViewModel.getTotalPrice()));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // kiem tra dang nhap
        if(SharedReferenceData.getInstance().getInt(this,"uid") == 0){
            Intent login = new Intent(this, Login1Activity.class);
            startActivity(login);
        }
    }
}
