package com.huce.t25film.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.huce.t25film.R;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.databinding.ActivityPaymentBinding;
import com.huce.t25film.viewmodels.PaymentViewModel;

public class PaymentActivity extends AppCompatActivity {
    private ActivityPaymentBinding binding;
    private PaymentViewModel viewModel;
    private int showId, cost;
    private int[] seatIds;
    private String seatNames;
    private int discountPrice = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntent().putExtra("title", "Thanh toán");
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

        viewModel = new ViewModelProvider(this).get(PaymentViewModel.class);
        viewModel.getMessage().observe(this, message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show());

        viewModel.getLoad().observe(this, load ->
                binding.progressBarBooking.setVisibility(load));

        binding.checkDiscount.setOnClickListener(view -> {
            String code = binding.txtCode.getText().toString();
            if(code.isEmpty()) {
                Toast.makeText(this, "Bạn chưa nhập mã", Toast.LENGTH_SHORT).show();
            }
            viewModel.getPromotion(code).observe(this, promotion ->{
                if (promotion == null) return;
                int discount = viewModel.getDiscountPrice(code);
                if (discount == 0) return;
                discountPrice = discount * cost / 100;
                binding.discount.setText(String.format("%,d", discountPrice) + "đ");
                binding.totalPayment.setText(String.format("%,d", cost - discountPrice) + "đ");
            });
        });
        StringBuilder stringSeatIds = new StringBuilder();
        for (int i = 0; i < seatIds.length; i++) {
            stringSeatIds.append(seatIds[i]);
            if (i < seatIds.length - 1) {
                stringSeatIds.append(",");
            }
        }

        binding.btnPayment.setOnClickListener(view -> {
            Log.e("seatId", stringSeatIds.toString());
            Log.e("uid",SharedReferenceData.getInstance().getInt(this,"uid")+"");
            Log.e("showId", showId+"");
            Log.e("cost", cost+"");
            viewModel.createTicket(SharedReferenceData.getInstance().getInt(this,"uid"),
                    stringSeatIds.toString(), showId, cost - discountPrice).observe(this, ticketResource -> {

                        if (ticketResource == null) return;
                        if (ticketResource.getStatus().equals("success")){
                            Toast.makeText(this,"Thanh toán thành công", Toast.LENGTH_SHORT).show();
                            Intent home = new Intent(this, HomeActivity.class);
                            finish();
                            viewModel.clearData();
                            startActivity(home);
                        }else{
                            Toast.makeText(this,"Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                            Intent home = new Intent(this, HomeActivity.class);
                            finish();
                            viewModel.clearData();
                            startActivity(home);
                        }
            });
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        viewModel.clearSeatHold(seatIds, showId);
        viewModel.clearData();
    }

    public void initView(){
        Bundle data = getIntent().getExtras();

        cost =  data.getInt("cost");
        seatIds = data.getIntArray("seatIds");
        seatNames = data.getString("seats");
        showId = data.getInt("showId");
        binding.movieName.setText(data.getString("name"));
        binding.runTime.setText(data.getString("runtime")+" phút");
        binding.cinemaName.setText(data.getString("cinemaName"));
        binding.date.setText(data.getString("date"));
        binding.time.setText(data.getString("time"));
        binding.seat.setText(seatNames);
        binding.totalPayment.setText(String.format("%,d",cost) + "đ");
        binding.totalPrice.setText(String.format("%,d", cost) + "đ");
        binding.discount.setText("0đ");
        binding.cardBar.setOnClickListener(view -> {
            if (binding.cardBar.getBackground() == null){
                binding.cardBar.setBackground(getDrawable(R.drawable.border_blue));
                binding.iconCard.setImageResource(R.drawable.ic_credit_card_blue);
                binding.txtCard.setTextColor(getColor(R.color.blue));
                binding.momoBar.setBackground(null);
                binding.iconMomo.setImageResource(R.drawable.momo_gray);
                binding.txtMomo.setTextColor(getColor(R.color.gray));
            }
        });
        binding.momoBar.setOnClickListener(view -> {
            if (binding.momoBar.getBackground() == null){
                binding.momoBar.setBackground(getDrawable(R.drawable.border_blue));
                binding.iconMomo.setImageResource(R.drawable.momo);
                binding.txtMomo.setTextColor(getColor(R.color.momo));
                binding.cardBar.setBackground(null);
                binding.iconCard.setImageResource(R.drawable.ic_credit_card_gray);
                binding.txtCard.setTextColor(getColor(R.color.gray));
            }
        });

    }
}
