package com.example.duan1_customer.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1_customer.MainActivity;
import com.example.duan1_customer.R;
import com.example.duan1_customer.adapter.HistoryAdapter;
import com.example.duan1_customer.database.BillDAO;
import com.example.duan1_customer.model.Bill;
import com.example.duan1_customer.model.ServiceAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryFragment extends Fragment {

    RecyclerView rcViewHistory;
    LinearLayoutManager linearLayoutManager;
    HistoryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        rcViewHistory = view.findViewById(R.id.rcViewHistory);

        showHistory();
        return view;
    }
    private void showHistory(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceAPI.Service_Customer)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceAPI service = retrofit.create(ServiceAPI.class);
        Call<ArrayList<Bill>> billHistory = service.showHistory(((MainActivity)getContext()).customerCurrent.getPhoneNumber());
        billHistory.enqueue(new Callback<ArrayList<Bill>>() {
            @Override
            public void onResponse(Call<ArrayList<Bill>> call, Response<ArrayList<Bill>> response) {
                ArrayList<Bill> historyBill = response.body();
                for(Bill listHistory: historyBill){
                    if (listHistory.getStatus().equals("da thanh toan")){
                        linearLayoutManager = new LinearLayoutManager(getContext());
                        adapter = new HistoryAdapter(getContext(),historyBill);
                        rcViewHistory.setLayoutManager(linearLayoutManager);
                        rcViewHistory.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Bill>> call, Throwable t) {

            }
        });
    }
}