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
import com.bumptech.glide.request.RequestOptions;
import com.huce.t25film.model.ListFilm;
import com.huce.t25film.R;
import com.huce.t25film.views.DetailFilmActivity;

public class KMListAdapter extends RecyclerView.Adapter<KMListAdapter.ViewHolder> {
    ListFilm items;
    Context context;

    public KMListAdapter(ListFilm items) {
        this.items = items;
    }

    @NonNull
    @Override
    public KMListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_km, parent, false);
        return new ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull KMListAdapter.ViewHolder holder, int position) {
        //gọi API cho titleText với items.getData
        holder.titleText.setText(items.getData().get(position).getTitle());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new CenterCrop());

        //gọi API với hoder.pic với items.getData
        Glide.with(context)
                .load(items.getData().get(position).getPoster())
                .apply(requestOptions)
                .into(holder.pic);

        //khi ấn vào ra id của film đó
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailFilmActivity.class);
            intent.putExtra("id", items.getData().get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.txtThangCal);
            pic = itemView.findViewById(R.id.txtNgayCal);
        }
    }
}
