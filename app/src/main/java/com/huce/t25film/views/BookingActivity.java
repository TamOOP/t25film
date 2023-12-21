package com.huce.t25film.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.huce.t25film.Adapters.SeatAdapter;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.databinding.ActivityBookingBinding;
import com.huce.t25film.model.Film;
import com.huce.t25film.model.Seat;
import com.huce.t25film.model.Show;
import com.huce.t25film.viewmodels.BookingViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity{
    private int showId;
    private ActivityBookingBinding binding;

    private List<Seat> seats = new ArrayList<>();
    private SeatAdapter seatAdapter;
    private BookingViewModel bookingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("state","create");
        super.onCreate(savedInstanceState);
        getIntent().putExtra("title","Đặt vé theo phim");
        binding = ActivityBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showId = this.getIntent().getIntExtra("showId", 0);
        if( showId == 0) finish();

        bookingViewModel = new ViewModelProvider(this).get(BookingViewModel.class);
        binding.navBar.setVisibility(View.GONE);

        int uid = SharedReferenceData.getInstance().getInt(this,"uid");

        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent payment = new Intent(this, )
            }
        });

        seatAdapter = new SeatAdapter(this, this.seats, bookingViewModel);
        binding.recyclerviewViewSeats.setAdapter(seatAdapter);

        bookingViewModel.getLoad().observe(this, load ->
                binding.progressBarBooking.setVisibility(load));

        bookingViewModel.getMessage().observe(this, message ->
                Toast.makeText(this,message,Toast.LENGTH_SHORT).show());

        bookingViewModel.getCinemaInfo(this, showId).observe(this, cinemaResource->{
            if(cinemaResource == null) return;
            if(cinemaResource.getStatus().equals("success")){
                binding.progressBarBooking.setVisibility(View.GONE);
                seats.clear();
                seats.addAll(cinemaResource.getCinema().getSeats());

                binding.recyclerviewViewSeats.setLayoutManager(new GridLayoutManager(this, cinemaResource.getCinema().getSeatPerRow()));
                seatAdapter.notifyDataSetChanged();

                Film film = cinemaResource.getFilm();
                Show show = cinemaResource.getShow();
                binding.movieName.setText(film.getName());
                binding.runTime.setText(film.getRuntime() + " phút");
                binding.showTime.setText(show.getDayOfWeek()+" "
                        +show.getDate()+" "
                        +show.getTime());
            }
            else{
                Toast.makeText(this, "Không tìm thấy xuất chiếu hoặc phòng chiếu", Toast.LENGTH_SHORT).show();
            }
        });

        bookingViewModel.getSeatSelected().observe(this, seatSelected->{
            if(bookingViewModel.getTotalPrice() == 0){
                binding.navBar.setVisibility(View.GONE);
            }else{
                binding.navBar.setVisibility(View.VISIBLE);
                binding.txtSeatData.setText(bookingViewModel.getSeatNameSelected());
                binding.txtTotalData.setText(String.format("%,d", bookingViewModel.getTotalPrice()) + "đ");
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("state","start");
        // kiem tra dang nhap
        if(SharedReferenceData.getInstance().getInt(this,"uid") == 0){
            Intent login = new Intent(this, Login1Activity.class);
            startActivity(login);
        }
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    // for stop refreshing UI, running animations and other visual things.
    protected void onStop() {
        super.onStop();
        Log.e("state","stop");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("state","resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("state","pause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("state","restart");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("state","destroy");
        bookingViewModel.clearData();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("state","restoreInstance");
    }
}
