package com.huce.t25film.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.R;
import com.huce.t25film.model.Show;
import com.huce.t25film.views.BookingActivity;

import java.util.List;

public class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ViewHolder> {
    List<Show> items;
    Context context;
    // Hàm chuyển đổi dữ liệu và gom các ngày giống nhau lại

    @NonNull
    @Override
    public ActorListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hours_calendar_film,parent,false);
        return new ActorListAdapter.ViewHolder(inflate);
    }

    public ActorListAdapter(Context context, List<Show> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull ActorListAdapter.ViewHolder holder, int position) {
        //holder.date.setText("Ngày "+items.get(position).getDate());
        Show show = items.get(position);
        holder.time.setText(show.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bookSeat = new Intent(context, BookingActivity.class);
                bookSeat.putExtra("showId", show.getId());
                context.startActivity(bookSeat);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView time,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.txtHours);
            date=itemView.findViewById(R.id.txtDate);
        }
    }
}
