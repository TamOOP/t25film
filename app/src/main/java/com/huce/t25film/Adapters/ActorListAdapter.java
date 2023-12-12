package com.huce.t25film.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.R;
import com.huce.t25film.model.Show;
import com.huce.t25film.resources.ShowDateResource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ViewHolder> {
    List<Show> items;
    Context context;
    // Hàm chuyển đổi dữ liệu và gom các ngày giống nhau lại

    @NonNull
    @Override
    public ActorListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hours_calendar_film,parent,false);
        return new ActorListAdapter.ViewHolder(inflate);
    }

    public ActorListAdapter(List<Show> items) {
        this.items = items;
    }

    @Override
    public void onBindViewHolder(@NonNull ActorListAdapter.ViewHolder holder, int position) {
        //holder.date.setText("Ngày "+items.get(position).getDate());
        holder.ten.setText(items.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView ten,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten=itemView.findViewById(R.id.txtHours);
            date=itemView.findViewById(R.id.txtDate);
        }
    }
}
