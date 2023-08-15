package com.example.duan1_customer.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duan1_customer.LoginRegisterActivity;
import com.example.duan1_customer.MainActivity;
import com.example.duan1_customer.R;
import com.example.duan1_customer.RegisterPhoneActivity;

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
                showDialogConfirm();
            }
        });

        return view;
    }
    private void showDialogConfirm(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_confirm_delete,null);
        TextView btnCancel = view.findViewById(R.id.btnCancel);
        TextView btnOke = view.findViewById(R.id.btnOke);
        TextView title = view.findViewById(R.id.tvContent);
        title.setText("Bạn muốn đăng xuất?");

        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("numPhone", "");
                editor.putString("pass", "");
                editor.apply();
                ((MainActivity)getContext()).finish();
                Intent intent = new Intent(getContext(), LoginRegisterActivity.class);
                ((MainActivity)getContext()).startActivity(intent);
            }
        });
    }
}