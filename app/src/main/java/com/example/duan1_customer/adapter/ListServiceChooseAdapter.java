package com.example.duan1_customer.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_customer.MainActivity;
import com.example.duan1_customer.R;
import com.example.duan1_customer.model.Service;

import java.util.ArrayList;

public class ListServiceChooseAdapter extends RecyclerView.Adapter<ListServiceChooseAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Service> list,listChose;
    public ListServiceChooseAdapter(Context context, ArrayList<Service> list,ArrayList<Service> listChose) {
        this.context = context;
        this.list = list;
        this.listChose = listChose;
    }

    @NonNull
    @Override
    public ListServiceChooseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_service,parent,false);

        return new ListServiceChooseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListServiceChooseAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvName.setText(list.get(position).getName());
        holder.tvPrice.setText(list.get(position).getPrice()+"");
        for(Service sv : listChose){
            if(sv.getId()==list.get(position).getId()){
                holder.btnCancel.setVisibility(View.VISIBLE);
                holder.btnChoose.setVisibility(View.GONE);
            };
        }
        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.btnChoose.setVisibility(View.VISIBLE);
                holder.btnCancel.setVisibility(View.GONE);
                for(Service sv : listChose){
                    if(sv.getId() == list.get(position).getId()){
                        ((MainActivity)context).minusService(position);
                        break;
                    }
                }
            }
        });
        holder.btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.btnCancel.setVisibility(View.VISIBLE);
                holder.btnChoose.setVisibility(View.GONE);
                ((MainActivity)context).addService(list.get(position).getId());
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvPrice,btnCancel,btnChoose;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            btnCancel = itemView.findViewById(R.id.btnCancel);
            btnChoose = itemView.findViewById(R.id.btnChoose);
        }
    }
}
