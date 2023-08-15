package com.example.duan1_customer.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_customer.MainActivity;
import com.example.duan1_customer.R;
import com.example.duan1_customer.model.Product;

import java.util.ArrayList;

public class SlideTopProductAdapter extends RecyclerView.Adapter<SlideTopProductAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Product> list;
    private AlertDialog alertDialog;
    public SlideTopProductAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SlideTopProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_slide_top_product, parent,false);

        return new SlideTopProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideTopProductAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvPrice.setText(list.get(position).getPrice()+"");
        Glide.with(context)
                .load(list.get(position).getImage())
                .into(holder.ivProduct);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDetailProduct(list.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvPrice;
        ImageView ivProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivProduct = itemView.findViewById(R.id.ivProduct);
        }
    }

    private void showDialogDetailProduct(Product product){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((MainActivity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_detail_product, null);


        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvPrice = view.findViewById(R.id.tvPrice);
        TextView tvUnit = view.findViewById(R.id.tvUnit);
        TextView tvBrand = view.findViewById(R.id.tvBrand);
        TextView tvClassify = view.findViewById(R.id.tvClassify);
        ImageView ivHome = view.findViewById(R.id.ivHome);
        ImageView ivProduct = view.findViewById(R.id.ivProduct);
        AppCompatButton btnBuy = view.findViewById(R.id.btnBuy);

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });

        tvName.setText(product.getName());
        tvPrice.setText(product.getPrice()+"");
        tvUnit.setText(product.getUnit());
        tvBrand.setText(product.getBrand());
        tvClassify.setText(product.getClassify());
        Glide.with(context)
                .load(product.getImage())
                .into(ivProduct);


        builder.setView(view);

        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}
