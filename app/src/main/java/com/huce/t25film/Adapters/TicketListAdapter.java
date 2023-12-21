package com.huce.t25film.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.huce.t25film.R;
import com.huce.t25film.model.Promotion;
import com.huce.t25film.model.Ticket;
import com.huce.t25film.views.DetailKMActivity;
import com.huce.t25film.views.DetailTicketActivity;

import java.util.List;

public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.ViewHolder> {
    List<Ticket> items;
    //ListFilm items;
    Context context;

    public TicketListAdapter(List<Ticket> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public TicketListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_lsgd,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketListAdapter.ViewHolder holder, int position) {
        //gọi API cho titleText với items.getData
        holder.nameTxtLSGD.setText(items.get(position).getShow().getCinema().getName());
        holder.dateTxtLSGD.setText("Ngày: "+items.get(position).getShow().getDate());
        holder.timeTxtLSGD.setText("Giờ đặt: "+items.get(position).getShow().getTime());
        holder.seatTxtLSGD.setText("Ghế: "+items.get(position).getSeat());
        holder.priceTxtLSGD.setText("Giá: "+items.get(position).getCost()+" VNĐ");


        //khi ấn vào ra id của film đó
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailTicketActivity.class);
            intent.putExtra("id",items.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTxtLSGD,dateTxtLSGD,timeTxtLSGD,seatTxtLSGD,priceTxtLSGD;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxtLSGD=itemView.findViewById(R.id.txtNameFilmLSGD);
            dateTxtLSGD=itemView.findViewById(R.id.txtDateLSGD);
            timeTxtLSGD=itemView.findViewById(R.id.txtTimeLSGD);
            seatTxtLSGD=itemView.findViewById(R.id.txtSeatLSGD);
            priceTxtLSGD=itemView.findViewById(R.id.txtPriceLSGD);

        }
    }
}
