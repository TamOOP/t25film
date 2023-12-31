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
import com.huce.t25film.model.Promotion;
import com.huce.t25film.views.DetailFilmActivity;
import com.huce.t25film.views.DetailKMActivity;

import java.util.List;

public class PromotionListAdapter extends RecyclerView.Adapter<PromotionListAdapter.ViewHolder> {
    List<Promotion> items;
    //ListFilm items;
    Context context;

    public PromotionListAdapter(List<Promotion> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PromotionListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_km,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionListAdapter.ViewHolder holder, int position) {
        //gọi API cho titleText với items.getData
        holder.titleText.setText(items.get(position).getTitle());
        RequestOptions requestOptions= new RequestOptions();
        //requestOptions=requestOptions.transform(new CenterCrop(),new RoundedCorners(20));

        //gọi API với hoder.pic với items.getData
        Glide.with(context)
                .load(items.get(position).getImage())
                .apply(requestOptions)
                .into(holder.pic);

        //khi ấn vào ra id của film đó
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailKMActivity.class);
            intent.putExtra("id",items.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleText;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText=itemView.findViewById(R.id.txtTitleKM);
            pic=itemView.findViewById(R.id.picKM);
        }
    }
}
