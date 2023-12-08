package com.huce.t25film.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.model.Seat;
import com.huce.t25film.R;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {
    private List<Seat> seatList;
    private OnSeatClickListener onSeatClickListener;

    public SeatAdapter(List<Seat> seatList, OnSeatClickListener onSeatClickListener) {
        this.seatList = seatList;
        this.onSeatClickListener = onSeatClickListener;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seat, parent, false);
        return new SeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        final Seat seat = seatList.get(position);
        holder.bind(seat);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSeatClickListener.onSeatClick(seat);
            }
        });
    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }

    public interface OnSeatClickListener {
        void onSeatClick(Seat seat);
    }

    public class SeatViewHolder extends RecyclerView.ViewHolder {
        private ImageView seatStatusImageView;
        private TextView seatNumberTextView;

        public SeatViewHolder(@NonNull View itemView) {
            super(itemView);
            seatStatusImageView = itemView.findViewById(R.id.seatStatusImageView);
            seatNumberTextView = itemView.findViewById(R.id.seatNumberTextView);
        }

        public void bind(Seat seat) {
            // Hiển thị trạng thái ghế và số ghế
            if (seat.isBooked()) {
                seatStatusImageView.setImageResource(R.drawable.seated);
            } else {
                if (seat.isSelected()) {
                    seatStatusImageView.setImageResource(R.drawable.seating);
                } else {
                    seatStatusImageView.setImageResource(R.drawable.seat);
                }
            }
            seatNumberTextView.setText(seat.getSeatNumber());
        }
    }
}

