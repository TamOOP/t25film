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
import com.huce.t25film.model.ListFilm;
import com.huce.t25film.R;
import com.huce.t25film.views.DetailFilmActivity;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.ViewHolder> {
    ListFilm items;
    Context context;

    public FilmListAdapter(ListFilm items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FilmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmListAdapter.ViewHolder holder, int position) {
        //gọi API cho titleText với items.getData
        holder.titleText.setText(items.getData().get(position).getTitle());
        holder.timeText.setText(items.getData().get(position).getYear());
        RequestOptions requestOptions= new RequestOptions();
        requestOptions=requestOptions.transform(new CenterCrop(),new RoundedCorners(20));

        //gọi API với hoder.pic với items.getData
        Glide.with(context)
                .load(items.getData().get(position).getPoster())
                .apply(requestOptions)
                .into(holder.pic);

        //khi ấn vào ra id của film đó
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailFilmActivity.class);
            intent.putExtra("id",items.getData().get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.getData().size();
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
