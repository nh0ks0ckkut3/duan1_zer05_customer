package com.example.duan1_customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterNewPassActivity extends AppCompatActivity {
    TextView tvSetPhone;
    EditText edtPass;
    ImageView ivOut, ivDelete, ivRight;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_pass);
        tvSetPhone = findViewById(R.id.tvSetPhone);
        edtPass = findViewById(R.id.edtPass);
        ivOut = findViewById(R.id.ivOut);
        ivDelete = findViewById(R.id.ivDelete);
        ivRight = findViewById(R.id.ivRight);
    }
}