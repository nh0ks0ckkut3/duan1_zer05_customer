package com.example.duan1_customer.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.duan1_customer.MainActivity;
import com.example.duan1_customer.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileFragment extends Fragment {

    LinearLayout btnDetailProfile;
    LinearLayout btnHistory;
    LinearLayout btnChangePass;
    LinearLayout btnLogOut;
    Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btnDetailProfile = view.findViewById(R.id.btnDetailProfile);
        btnHistory = view.findViewById(R.id.btnHistory);
        btnChangePass = view.findViewById(R.id.btnChangePass);
        btnLogOut = view.findViewById(R.id.btnLogOut);
        btnDetailProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new DetailProfileFragment();
                ((MainActivity)getContext()).addFragment(fragment);
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new HistoryFragment();
                ((MainActivity)getContext()).addFragment(fragment);
            }
        });
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getContext()).showDialogChangePass();
            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getContext()).showDialogConfirm();
            }
        });

        return view;
    }
}