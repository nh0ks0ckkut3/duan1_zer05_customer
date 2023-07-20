package com.example.duan1_customer.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_customer.R;
import com.example.duan1_customer.model.Bill;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Bill> list;
    public HistoryAdapter(Context context, ArrayList<Bill> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_history,parent,false);

        return new HistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        holder.tvStylist.setText(list.get(position).getUserNameEmployee());
        holder.tvService.setText(list.get(position).getNameService());
        holder.tvDate.setText(list.get(position).getTime());
        holder.tvTotalPrice.setText(list.get(position).getTotalPrice()+"");
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivImage;
        TextView tvStylist, tvSkinner, tvService, tvPayMethod, tvTotalPrice, tvDate, tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStylist = itemView.findViewById(R.id.tvStylist);
            tvSkinner = itemView.findViewById(R.id.tvSkinner);
            tvService = itemView.findViewById(R.id.tvService);
            tvPayMethod = itemView.findViewById(R.id.tvPayMethod);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
