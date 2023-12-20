package com.huce.t25film.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.R;
import com.huce.t25film.resources.ShowDateResource;

import java.util.List;

public class DateListAdapter extends RecyclerView.Adapter<DateListAdapter.ViewHolder> {
    List<ShowDateResource> showByDates;
    Context context;
    @NonNull
    @Override
    public DateListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_date,parent,false);
        return new DateListAdapter.ViewHolder(inflate);
    }

    public DateListAdapter(Context context, List<ShowDateResource> showByDates) {
        this.showByDates = showByDates;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull DateListAdapter.ViewHolder holder, int position) {
        ShowDateResource showByDate = showByDates.get(position);
        holder.date.setText("Ngày "+showByDate.getDate());
        //GridLayoutManager layoutManager = new GridLayoutManager(holder.itemView.getContext(), GridLayoutManager.HORIZONTAL, false);
        //holder.recyclerViewTime.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(),1));
        //holder.recyclerViewTime.setLayoutManager(layoutManager);

        holder.recyclerViewTime.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL,false));
        holder.adapterTime = new ActorListAdapter(context, showByDate.getShows());

        if (holder.adapterTime != null) {
            holder.recyclerViewTime.setAdapter(holder.adapterTime);
        }
//        ActorListAdapter showtimeAdapter = new ActorListAdapter(items.get(position).getShows());
//        holder.recyclerViewTime.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
//        holder.recyclerViewTime.setAdapter(showtimeAdapter);
    }

    @Override
    public int getItemCount() {
        return showByDates.size();
    }
    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView date;
        RecyclerView recyclerViewTime;
        RecyclerView.Adapter adapterTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.txtDate);
            recyclerViewTime=itemView.findViewById(R.id.recyclerViewTime);
        }
    }
}
