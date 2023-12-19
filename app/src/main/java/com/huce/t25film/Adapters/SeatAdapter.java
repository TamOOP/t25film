package com.huce.t25film.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.R;
import com.huce.t25film.databinding.ItemSeatBinding;
import com.huce.t25film.model.Seat;
import com.huce.t25film.viewmodels.BookingViewModel;
import com.huce.t25film.views.BookingActivity;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {
    private List<Seat> seatList;
    private BookingViewModel viewModel;
    private BookingActivity owner;

    public SeatAdapter(BookingActivity owner, List<Seat> seatList, BookingViewModel viewModel) {
        this.owner = owner;
        this.seatList = seatList;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSeatBinding binding = ItemSeatBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new SeatViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        final Seat seat = seatList.get(position);
        String seatName = seatList.get(position).getName();
        holder.bind(seat);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.toggleSeatChooseState(seatName);
                viewModel.getSeatSelected().observe(owner, seatSelectStatus->{
                    if(seatSelectStatus == null || seatSelectStatus.get(seatName) == null) return;
                    if(seatSelectStatus.get(seatName).getIsChoosed()){
                        holder.binding.seatStatusImageView.setImageResource(R.drawable.ic_seat_choosing);
                    }else {
                        holder.binding.seatStatusImageView.setImageResource(R.drawable.ic_seat_normal);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }


    public class SeatViewHolder extends RecyclerView.ViewHolder {
        private ItemSeatBinding binding;

        public SeatViewHolder(@NonNull ItemSeatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Seat seat) {
//             Hiển thị trạng thái ghế và số ghế
            if (seat.getIsBooked() == 1) {

                binding.seatStatusImageView.setImageResource(R.drawable.ic_seat_booked);
            } else if (seat.getIsChoosed()) {
                binding.seatStatusImageView.setImageResource(R.drawable.ic_seat_choosing);
            } else {
                if (seat.getIsSelected() == 1) {
                    binding.seatStatusImageView.setImageResource(R.drawable.ic_seat_selected);
                } else {
                    binding.seatStatusImageView.setImageResource(R.drawable.ic_seat_normal);
                }
            }
            binding.seatNumberTextView.setText(seat.getName());
        }
    }

}

