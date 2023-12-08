package com.huce.t25film.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.model.ListFilm;
import com.huce.t25film.R;

public class CalendarFilmListHourAdapter extends RecyclerView.Adapter<CalendarFilmListHourAdapter.ViewHolder>{
    ListFilm items;
    Context context;

    public CalendarFilmListHourAdapter(ListFilm items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CalendarFilmListHourAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hours_calendar_film,parent,false);
        return new CalendarFilmListHourAdapter.ViewHolder(inflate);
    }



    @Override
    public void onBindViewHolder(@NonNull CalendarFilmListHourAdapter.ViewHolder holder, int position) {
        //gọi API cho titleText với items.getData
        holder.titleTxtHours.setText(items.getData().get(position).getId());


        //khi ấn vào ra id của film đó
//        holder.itemView.setOnClickListener(view -> {
//            Intent intent = new Intent(holder.itemView.getContext(), DetailFilmActivity.class);
//            intent.putExtra("id",items.getData().get(position).getId());
//            context.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return items.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTxtHours;
        //RecyclerView viewpagerCalFilm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxtHours=itemView.findViewById(R.id.txtHours);
        }
    }
}
