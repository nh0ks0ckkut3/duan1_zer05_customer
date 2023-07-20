package com.example.duan1_customer.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_customer.R;
import com.example.duan1_customer.database.BillDAO;
import com.example.duan1_customer.model.Slot;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Slot> list;
    private String slotPick = "";
    private TextView btnFinish;
    public SlotAdapter(Context context, ArrayList<Slot> list, TextView btnFinish) {
        this.context = context;
        this.list = list;
        this.btnFinish = btnFinish;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_slot,parent,false);

        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            java.util.Date date = new java.util.Date();
            holder.tvTime.setText(list.get(position).getTime());
            if(list.get(position).getTime().equals(slotPick)){
                holder.tvTime.setBackgroundColor(R.color.yellow);
            }
            if(Integer.parseInt(list.get(position).getTime().substring(0,2))<date.getHours()){
                holder.tvTime.setBackgroundColor(R.color.black);
            }else if(Integer.parseInt(list.get(position).getTime().substring(0,2))==date.getHours() &&
                    Integer.parseInt(list.get(position).getTime().substring(3,4))<date.getMinutes()){
                holder.tvTime.setBackgroundColor(R.color.black);
            }

        holder.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slotPick = holder.tvTime.getText().toString();
                btnFinish.setBackgroundColor(R.color.yellow);
                loadData();
                btnFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
    private void loadData(){
        this.notifyDataSetChanged();
    }
}