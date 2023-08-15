package com.example.duan1_customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterPhoneActivity extends AppCompatActivity {
    EditText edtPhone;
    ImageView ivDelete, ivRight;
    String numPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);
        edtPhone = findViewById(R.id.edtPhone);
        ivDelete = findViewById(R.id.ivDelete);
        ivRight = findViewById(R.id.ivRight);


        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPhone.setText("");
            }
        });
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numPhone = edtPhone.getText().toString();
                if (numPhone.equals("")){
                    Toast.makeText(RegisterPhoneActivity.this, "Nhập số điện thoại", Toast.LENGTH_SHORT).show();
                }else if(numPhone.length() < 10){
                    Toast.makeText(RegisterPhoneActivity.this, "Số điện thoại phải trên 10 số", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intentRegisterPass = new Intent(RegisterPhoneActivity.this, RegisterPassActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("numPhone", numPhone);
                    intentRegisterPass.putExtras(bundle);
                    startActivity(intentRegisterPass);
                    finish();
                }

            }
        });

    }
}