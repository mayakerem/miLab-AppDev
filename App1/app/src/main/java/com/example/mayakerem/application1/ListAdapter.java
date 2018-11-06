package com.example.mayakerem.application1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends RecyclerView.Adapter {
    private String[] itemData;

    public ListAdapter(String[] data) {
        itemData = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textData;
        private ImageView imageData;

        public MyViewHolder(TextView itemViewLayout, ImageView imageViewLayout) {
            super(itemViewLayout);
            textData = itemViewLayout;
            imageData = imageViewLayout;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        ImageView imageView = new ImageView(parent.getContext());
        MyViewHolder holder = new MyViewHolder(textView, imageView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String textForDisplay = itemData[position];
        ((MyViewHolder)holder).textData.setText(textForDisplay);
    }

    @Override
    public int getItemCount() {
        return itemData.length;
    }
}