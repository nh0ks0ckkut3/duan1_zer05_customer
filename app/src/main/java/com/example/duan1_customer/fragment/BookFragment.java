package com.example.duan1_customer.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_customer.ChooseServiceActivity;
import com.example.duan1_customer.MainActivity;
import com.example.duan1_customer.R;
import com.example.duan1_customer.adapter.ListServiceChoseAdapter;
import com.example.duan1_customer.adapter.SlotAdapter;
import com.example.duan1_customer.database.ServiceDAO;
import com.example.duan1_customer.model.Service;
import com.example.duan1_customer.model.Slot;

import java.util.ArrayList;

public class BookFragment extends Fragment {

    RecyclerView rcView,rcViewListServiceChoose;
    TextView btnBook,btnChooseService;
    ServiceDAO serviceDAO;
    ArrayList<Service> listServiceChose,listService;
    LinearLayoutManager linearLayout;
    ListServiceChoseAdapter listServiceChoseAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookFragment(ArrayList<Service> listServiceChose) {
       this.listServiceChose = listServiceChose;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        rcView = view.findViewById(R.id.rcView);
        rcViewListServiceChoose = view.findViewById(R.id.rcViewListServiceChoose);
        btnChooseService = view.findViewById(R.id.btnChooseService);
        serviceDAO = new ServiceDAO(getContext());
        listService = serviceDAO.getListService();
        btnBook = view.findViewById(R.id.btnBook);
        linearLayout = new LinearLayoutManager(getContext());
        rcViewListServiceChoose.setLayoutManager(linearLayout);
        listServiceChoseAdapter = new ListServiceChoseAdapter(getContext(), listServiceChose);
        rcViewListServiceChoose.setAdapter(listServiceChoseAdapter);

        btnChooseService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getContext()).showDialogChooseService(listService,listServiceChose);
            }
        });


        ArrayList<Slot> listSlot = new ArrayList<>();
        listSlot.add(new Slot("07h00"));
        listSlot.add(new Slot("07h30"));
        listSlot.add(new Slot("08h00"));
        listSlot.add(new Slot("08h30"));
        listSlot.add(new Slot("09h00"));
        listSlot.add(new Slot("09h30"));
        listSlot.add(new Slot("10h00"));
        listSlot.add(new Slot("10h30"));
        listSlot.add(new Slot("11h00"));
        listSlot.add(new Slot("11h30"));
        listSlot.add(new Slot("12h00"));
        listSlot.add(new Slot("12h30"));
        listSlot.add(new Slot("13h00"));
        listSlot.add(new Slot("13h30"));
        listSlot.add(new Slot("14h00"));
        listSlot.add(new Slot("14h30"));
        listSlot.add(new Slot("15h00"));
        listSlot.add(new Slot("15h30"));
        listSlot.add(new Slot("16h00"));
        listSlot.add(new Slot("16h30"));
        listSlot.add(new Slot("17h00"));
        listSlot.add(new Slot("17h30"));
        listSlot.add(new Slot("18h00"));
        listSlot.add(new Slot("18h30"));
        listSlot.add(new Slot("19h00"));
        listSlot.add(new Slot("19h30"));
        listSlot.add(new Slot("20h00"));
        listSlot.add(new Slot("20h30"));
        listSlot.add(new Slot("21h00"));
        listSlot.add(new Slot("21h30"));
        SlotAdapter adapter = new SlotAdapter(getContext(), listSlot, btnBook);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),10);
        rcView.setLayoutManager(gridLayoutManager);
        rcView.setAdapter(adapter);

        return view;
    }
}