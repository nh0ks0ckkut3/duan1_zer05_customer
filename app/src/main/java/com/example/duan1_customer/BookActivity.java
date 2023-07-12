package com.example.duan1_customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.duan1_customer.adapter.SlotAdapter;
import com.example.duan1_customer.model.Slot;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    RecyclerView rcView;
    TextView btnBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        rcView = findViewById(R.id.rcView);
        btnBook = findViewById(R.id.btnBook);

        ArrayList<Slot> listSlot = new ArrayList<>();
        listSlot.add(new Slot("7h00"));
        listSlot.add(new Slot("7h30"));
        listSlot.add(new Slot("8h00"));
        listSlot.add(new Slot("8h30"));
        listSlot.add(new Slot("9h00"));
        listSlot.add(new Slot("9h30"));
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
        SlotAdapter adapter = new SlotAdapter(this, listSlot, btnBook);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(BookActivity.this,10);
        rcView.setLayoutManager(gridLayoutManager);
        rcView.setAdapter(adapter);
    }
}