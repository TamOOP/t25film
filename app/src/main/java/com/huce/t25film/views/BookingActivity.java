package com.huce.t25film.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huce.t25film.Adapters.SeatAdapter;
import com.huce.t25film.model.FilmItem;
import com.huce.t25film.model.Seat;
import com.huce.t25film.R;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity implements SeatAdapter.OnSeatClickListener {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private RecyclerView recyclerViewSeats;
    private ProgressBar progressBar;
    private TextView titleTxt,movieTimeTxt,txtSeat;
    private int idFilm;
    private ImageView imgDetail,backImg;
    private NestedScrollView scrollView;

    private List<Seat> seatList;
    private SeatAdapter seatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        //idFilm=getIntent().getIntExtra("id",0);
        initView();
        sendRequest();


        // Nhận thông tin về phim từ Intent
//        Intent intent = getIntent();
//        //if (intent.hasExtra("movie")) {
//            showtimeTextView.setText("Showtime: 2:00 PM");
//
//            // Tạo danh sách ghế ngồi
//            seatList = generateSeatList();
//            seatAdapter = new SeatAdapter(seatList, this);
//            recyclerViewSeats.setLayoutManager(new GridLayoutManager(this, 5));
//            recyclerViewSeats.setAdapter(seatAdapter);
//            Toast.makeText(BookingActivity.this, "" + recyclerViewSeats, Toast.LENGTH_SHORT).show();
//
//            // Xử lý sự kiện khi người dùng xác nhận đặt vé
//            btnConfirmBooking.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Xử lý logic đặt vé
//                    // TODO: Implement booking logic
//                    Toast.makeText(BookingActivity.this, "Booking confirmed!", Toast.LENGTH_SHORT).show();
//                }
//            });
//        //}
    }

    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + 1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                progressBar.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);

                FilmItem item = gson.fromJson(response,FilmItem.class);



                //item coi như là FilmItem gọi ra
                Glide.with(BookingActivity.this)
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
        titleTxt=findViewById(R.id.movieNameSeatsTxt);
        progressBar=findViewById(R.id.progressBarSeats);
        scrollView=findViewById(R.id.scrollViewSeats);
        imgDetail=findViewById(R.id.imgFilmSeats);
        movieTimeTxt=findViewById(R.id.movieTimeSeats);
        backImg=findViewById(R.id.btnBackSeats);
        recyclerViewSeats=findViewById(R.id.recyclerviewViewSeats);
        txtSeat=findViewById(R.id.txtSeat);
        //recyclerViewSeats.setLayoutManager(new GridLayoutManager(this, 5));
        //recyclerViewSeats.setAdapter(seatAdapter);
        seatList = generateSeatList();
        seatAdapter = new SeatAdapter(seatList, this);
        recyclerViewSeats.setLayoutManager(new GridLayoutManager(this, 10));
        recyclerViewSeats.setAdapter(seatAdapter);


        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onSeatClick(Seat seat) {
        // Xử lý sự kiện khi người dùng chọn một ghế ngồi
        // TODO: Implement seat selection logic
        if (seat.isBooked()){
            Toast.makeText(this, "Seat đã được chọn: ", Toast.LENGTH_SHORT).show();
        }else{
            seat.setSelected(!seat.isSelected());
            seatAdapter.notifyDataSetChanged();
            if (seat.isSelected()){
                //Toast.makeText(this, "Seat selected: " + seat.getSeatNumber(), Toast.LENGTH_SHORT).show();
                txtSeat.setText(seat.getSeatNumber());
            } else if (!seat.isSelected()) {
                //Toast.makeText(this, "Seat bi huy: " + seat.getSeatNumber(), Toast.LENGTH_SHORT).show();
                txtSeat.setText("Trống");
            }
        }
    }

    private List<Seat> generateSeatList() {
        // Logic để tạo danh sách ghế ngồi
        List<Seat> seatList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            seatList.add(new Seat("A" + i, i % 5 == 0)); // Mỗi ghế thứ 5 được đặt chỗ
        }
        return seatList;
    }
}
