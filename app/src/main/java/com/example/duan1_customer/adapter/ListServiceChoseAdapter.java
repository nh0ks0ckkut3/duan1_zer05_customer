package com.example.duan1_customer.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_customer.model.Service;

import java.util.ArrayList;

public class ListServiceChoseAdapter extends RecyclerView.Adapter<ListServiceChoseAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Service> list;
    public ListServiceChoseAdapter(Context context, ArrayList<Service> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListServiceChoseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,parent,false);

        return new ListServiceChoseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListServiceChoseAdapter.ViewHolder holder, int position) {
        holder.tvNameService.setText(list.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameService;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameService = itemView.findViewById(android.R.id.text1);
        }
    }
}
