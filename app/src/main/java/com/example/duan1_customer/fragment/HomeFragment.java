package com.example.duan1_customer.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.duan1_customer.MainActivity;
import com.example.duan1_customer.R;
import com.example.duan1_customer.adapter.SlideTopProductAdapter;
import com.example.duan1_customer.model.Product;
import com.example.duan1_customer.model.ServiceAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {


    RecyclerView rcViewTopProduct;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcViewTopProduct = view.findViewById(R.id.rcViewTopProduct);
        TextView name = view.findViewById(R.id.name);
        name.setText(((MainActivity)getContext()).customerCurrent.getName());

        ImageSlider imageSlider =  view.findViewById(R.id.imgSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner4, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        listProduct();


        return view;
    }

    private void listProduct(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceAPI.Service_Product)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceAPI service = retrofit.create(ServiceAPI.class);
        Call<ArrayList<Product>> getProduct = service.getProduct();
        getProduct.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                ArrayList<Product> topListProduct = response.body();
                    SlideTopProductAdapter topProductAdapter = new SlideTopProductAdapter(getContext(), topListProduct);

                    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
                    linearLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);
                    rcViewTopProduct.setLayoutManager(linearLayoutManager2);
                    rcViewTopProduct.setAdapter(topProductAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });
    }
}