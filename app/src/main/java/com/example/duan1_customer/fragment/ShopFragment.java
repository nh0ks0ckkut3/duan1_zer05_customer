package com.example.duan1_customer.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1_customer.R;
import com.example.duan1_customer.adapter.ProductAdapter;
import com.example.duan1_customer.model.Product;
import com.example.duan1_customer.model.ServiceAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopFragment extends Fragment {

    RecyclerView rcViewProduct;
    GridLayoutManager gridLayoutManager;
    ProductAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        rcViewProduct = view.findViewById(R.id.rcViewProduct);
        gridLayoutManager = new GridLayoutManager(getContext(),2);

        uploadProduct();


        ;

        return view;
    }

    private void uploadProduct(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceAPI.Service_Product)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceAPI service = retrofit.create(ServiceAPI.class);
        Call<ArrayList<Product>> getProduct = service.getProduct();
        getProduct.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                ArrayList<Product> getListProduct = response.body();
                adapter = new ProductAdapter(getContext(), getListProduct);
                rcViewProduct.setLayoutManager(gridLayoutManager);
                rcViewProduct.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });
    }
}