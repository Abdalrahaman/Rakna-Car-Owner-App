package com.example.rakna.raknagraduationproject.Model.AbdoModel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.rakna.raknagraduationproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context context;
    private List<historyModel> list;

    public HistoryAdapter(Context context, List<historyModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutinflter = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_item,null);
        ViewHolder viewHolder = new ViewHolder(layoutinflter);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        historyModel data = list.get(position);

        //loading image
        Picasso.get()
                .load(data.getImgurl())
                .into(holder.imageView);

        holder.name.setText(data.getName());
        holder.number.setText(data.getNumber());
        holder.date.setText(data.getDate());
        holder.time.setText(data.getFrom_time()+" : "+data.getTo_time());
        holder.location.setText(String.valueOf( data.getLocation()));
        holder.rate.setText(String.valueOf(data.getRate()));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,number,date,time,location,rate;
        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.customerpic);
            name = itemView.findViewById(R.id.tv_garage_name);
            number = itemView.findViewById(R.id.tv_garage_phone);
            date = itemView.findViewById(R.id.tv_garage_date);
            time = itemView.findViewById(R.id.tv_garage_time);
            location = itemView.findViewById(R.id.tv_garage_location);
            rate = itemView.findViewById(R.id.ratingBar);
        }
    }
}

