package com.example.duan1_customer.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_customer.R;

import java.util.ArrayList;

public class SlideBannerAdapter extends RecyclerView.Adapter<SlideBannerAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Integer> list;
    public SlideBannerAdapter(Context context, ArrayList<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SlideBannerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_slide_banner,parent,false);

        return new SlideBannerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgShow.setImageResource(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgShow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgShow = itemView.findViewById(R.id.imgShow);
        }
    }
}
