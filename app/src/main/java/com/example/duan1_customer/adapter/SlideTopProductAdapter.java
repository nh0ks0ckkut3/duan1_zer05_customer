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
import com.example.duan1_customer.model.Product;

import java.util.ArrayList;

public class SlideTopProductAdapter extends RecyclerView.Adapter<SlideTopProductAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Product> list;
    public SlideTopProductAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SlideTopProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_slide_top_product,parent,false);

        return new SlideTopProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideTopProductAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvPrice.setText(list.get(position).getPrice()+"");
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
