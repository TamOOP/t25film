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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.huce.t25film.R;
import com.huce.t25film.model.Film;
import com.huce.t25film.views.DetailFilmActivity;
import com.huce.t25film.views.DetailFilmSCActivity;

import java.util.List;

public class FilmListSCAdapter extends RecyclerView.Adapter<FilmListSCAdapter.ViewHolder> {
    List<Film> items;
    //ListFilm items;
    Context context;

    public FilmListSCAdapter(List<Film> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FilmListSCAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmListSCAdapter.ViewHolder holder, int position) {
        //gọi API cho titleText với items.getData
        holder.titleText.setText(items.get(position).getName());
        holder.timeText.setText(items.get(position).getRuntime()+" phút");
        RequestOptions requestOptions= new RequestOptions();
        requestOptions=requestOptions.transform(new CenterCrop(),new RoundedCorners(20));

        //gọi API với hoder.pic với items.getData
        Glide.with(context)
                .load(items.get(position).getImage())
                .apply(requestOptions)
                .into(holder.pic);

        //khi ấn vào ra id của film đó
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailFilmSCActivity.class);
            intent.putExtra("id",items.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleText,timeText;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText=itemView.findViewById(R.id.titleTxt);
            timeText=itemView.findViewById(R.id.timeTxt);
            pic=itemView.findViewById(R.id.picViewholder);
        }
    }
}
