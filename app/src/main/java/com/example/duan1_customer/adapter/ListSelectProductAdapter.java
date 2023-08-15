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
import com.example.duan1_customer.model.Product;

import java.text.NumberFormat;
import java.util.ArrayList;

public class ListSelectProductAdapter extends RecyclerView.Adapter<ListSelectProductAdapter.ViewHolder> {
    ArrayList<Product> list;
    Context context;
    ArrayList<Product> listProductSelectedTarget;

    public ListSelectProductAdapter(ArrayList<Product> list, Context context){
        this.list = list;
        this.context = context;
        this.listProductSelectedTarget = ((MainActivity)context).getListProductSelectedAdd();

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
        Glide.with(context).load(list.get(position).getImage()).into(holder.ivAvatar);
        NumberFormat currentLocale = NumberFormat.getInstance();
        holder.tvPrice.setText(currentLocale.format(list.get(position).getPrice()));
        holder.tvNameService.setText(list.get(position).getName());
        for(Product product : listProductSelectedTarget){
            if(product.getId() == list.get(position).getId()){
                holder.btnCancel.setVisibility(View.VISIBLE);
                holder.btnOk.setVisibility(View.GONE);
            }
        }
        holder.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listProductSelectedTarget.add(list.get(position));
                ((MainActivity)context).setListProductSelectedAdd(listProductSelectedTarget);
                holder.btnCancel.setVisibility(View.VISIBLE);
                holder.btnOk.setVisibility(View.GONE);
            }
        });
        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listProductSelectedTarget.remove(list.get(position));
                ((MainActivity)context).setListProductSelectedAdd(listProductSelectedTarget);
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