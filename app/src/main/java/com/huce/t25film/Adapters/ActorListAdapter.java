package com.huce.t25film.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.R;

import java.util.List;

public class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ViewHolder> {
    List<String> items;
    Context context;
    @NonNull
    @Override
    public ActorListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hours_calendar_film,parent,false);
        return new ActorListAdapter.ViewHolder(inflate);
    }

    public ActorListAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public void onBindViewHolder(@NonNull ActorListAdapter.ViewHolder holder, int position) {
        holder.ten.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView ten;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten=itemView.findViewById(R.id.txtHours);
        }
    }
}
