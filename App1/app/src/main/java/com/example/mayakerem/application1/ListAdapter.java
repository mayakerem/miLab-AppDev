package com.example.mayakerem.application1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends RecyclerView.Adapter {
    public String[] mDataSet;
    public Drawable[] mImageSet;

    public ListAdapter(String[] data, Drawable[] images) {
        mDataSet = data;
        mImageSet = images;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
            private View myView;

        public MyViewHolder(View view) {
            super(view);
            myView = view;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutmanager, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String textForDisplay = mDataSet[position];
        TextView text = ((MyViewHolder)holder).myView.findViewById(R.id.textView1);
        text.setText(textForDisplay);
        Drawable imagesToDisplay = mImageSet[position];
        ImageView img = ((MyViewHolder)holder).myView.findViewById(R.id.imageView1);
        img.setImageDrawable(imagesToDisplay);

    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
