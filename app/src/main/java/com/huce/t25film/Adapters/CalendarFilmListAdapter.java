package com.huce.t25film.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.huce.t25film.model.ListFilm;
import com.huce.t25film.R;
import com.huce.t25film.resources.ShowResource;
import com.huce.t25film.views.DetailFilmActivity;

public class CalendarFilmListAdapter extends RecyclerView.Adapter<CalendarFilmListAdapter.ViewHolder>{
    //ListFilm items;
    ShowResource items;
    Context context;
    //private RecyclerView.Adapter adapterMoviesHours;
    //private RecyclerView recyclerViewMoviesHours;
//    private RequestQueue mRequestQueue;
//    private StringRequest mStringRequest;

    public CalendarFilmListAdapter(ShowResource items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CalendarFilmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_calendar_film,parent,false);
        return new CalendarFilmListAdapter.ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull CalendarFilmListAdapter.ViewHolder holder, int position) {

        //gọi API cho titleText với items.getData
        holder.titleTxtCal.setText(items.getFilms().get(position).getName());
        holder.timeTxtCal.setText(items.getFilms().get(position).getRuntime()+" phút");
        holder.categoryTxtCal.setText(items.getFilms().get(position).getGenre());
        RequestOptions requestOptions= new RequestOptions();
        requestOptions=requestOptions.transform(new CenterCrop(),new RoundedCorners(20));

        //gọi API với hoder.pic với items.getData
        Glide.with(context)
                .load(items.getFilms().get(position).getImage())
                .apply(requestOptions)
                .into(holder.picViewholderCal);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
//        holder.recyclerViewMoviesHours.setLayoutManager(layoutManager);
//        holder.adapterMoviesHours = new ActorListAdapter(items.getFilms().get(position).getShows());
//        if (holder.adapterMoviesHours != null) {
//            holder.recyclerViewMoviesHours.setAdapter(holder.adapterMoviesHours);
//        }
        holder.recyclerViewMoviesHours.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL,false));
        holder.adapterMoviesHours = new ActorListAdapter(items.getFilms().get(position).getShows());

        if (holder.adapterMoviesHours != null) {
            holder.recyclerViewMoviesHours.setAdapter(holder.adapterMoviesHours);
        }

        //khi ấn vào ra id của film đó
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailFilmActivity.class);
            intent.putExtra("id",items.getFilms().get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.getFilms().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTxtCal,categoryTxtCal,timeTxtCal;
        ImageView picViewholderCal;
        RecyclerView.Adapter adapterMoviesHours;
        RecyclerView recyclerViewMoviesHours;

        //RecyclerView viewpagerCalFilm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxtCal=itemView.findViewById(R.id.titleTxtCal);
            categoryTxtCal=itemView.findViewById(R.id.categoryTxtCal);
            timeTxtCal=itemView.findViewById(R.id.timeTxtCal);
            picViewholderCal=itemView.findViewById(R.id.picViewholderCal);
            recyclerViewMoviesHours=itemView.findViewById(R.id.recyclerviewTimeCal);
            //recyclerViewMoviesHours.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));

        }
    }
}
