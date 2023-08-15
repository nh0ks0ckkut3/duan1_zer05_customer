package com.example.duan1_customer.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_customer.MainActivity;
import com.example.duan1_customer.R;
import com.example.duan1_customer.model.Service;

import java.text.NumberFormat;
import java.util.ArrayList;

public class ListSelectServiceAdapter extends RecyclerView.Adapter<ListSelectServiceAdapter.ViewHolder> {
    ArrayList<Service> list;
    Context context;
    ArrayList<Service> listServiceSelectedTarget;

    public ListSelectServiceAdapter(ArrayList<Service> list, Context context){
        this.list = list;
        this.context = context;
        this.listServiceSelectedTarget = ((MainActivity)context).getListServiceSelectedAdd();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_select_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getImageService()).into(holder.ivAvatar);
        NumberFormat currentLocale = NumberFormat.getInstance();
        holder.tvPrice.setText(currentLocale.format(list.get(position).getPrice()));
        holder.tvNameService.setText(list.get(position).getName());
        for(Service service : listServiceSelectedTarget){
            if(service.getIdService() == list.get(position).getIdService()){
                holder.btnCancel.setVisibility(View.VISIBLE);
                holder.btnOk.setVisibility(View.GONE);
            }
        }
        holder.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listServiceSelectedTarget.add(list.get(position));
                ((MainActivity)context).setListServiceSelectedAdd(listServiceSelectedTarget);
                holder.btnCancel.setVisibility(View.VISIBLE);
                holder.btnOk.setVisibility(View.GONE);
            }
        });
        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listServiceSelectedTarget.remove(list.get(position));
                ((MainActivity)context).setListServiceSelectedAdd(listServiceSelectedTarget);
                holder.btnCancel.setVisibility(View.GONE);
                holder.btnOk.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameService, tvPrice, btnOk;
        LinearLayout btnCancel;
        ImageView ivAvatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameService = itemView.findViewById(R.id.tvNameService);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            btnOk = itemView.findViewById(R.id.btnOke);
            btnCancel = itemView.findViewById(R.id.btnCancel);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
        }
    }

}