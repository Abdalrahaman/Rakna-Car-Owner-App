package com.example.rakna.raknagraduationproject.saeedCode;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by computer market on 10/4/2018.
 */
//my notify adapter //
public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.ViewHolder> {
    private itemData[] itemData;

    public NotifyAdapter(itemData[] itemData) {
        this.itemData = itemData;
    }

    @Override
    public NotifyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutinflter = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_view_saeed,null);
        ViewHolder viewHolder = new ViewHolder(layoutinflter);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NotifyAdapter.ViewHolder holder, int position) {
holder.imageView.setImageResource(itemData[position].imgurl);
holder.textView.setText(itemData[position].title);
    }

    @Override
    public int getItemCount() {
        return itemData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.notify_img);
            textView = itemView.findViewById(R.id.notify_text);
        }
    }
}
