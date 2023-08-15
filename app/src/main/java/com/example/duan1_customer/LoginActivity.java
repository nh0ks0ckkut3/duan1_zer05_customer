package com.example.duan1_customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.duan1_customer.model.Customer;

public class LoginActivity extends AppCompatActivity {
    EditText edtPhoneNumber, edtPassWord;
    CheckBox chkRemember;
    ImageButton icon_pass;
    SharedPreferences sharedPreferences;


    String phoneNumber, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_login = findViewById(R.id.btn_login);
        edtPhoneNumber = findViewById(R.id.edtUserName);
        edtPassWord = findViewById(R.id.edtPassWord);
        icon_pass = findViewById(R.id.icon_pass);
        chkRemember = findViewById(R.id.chkRemember);

        icon_pass.setBackgroundResource(R.drawable.hidepass);
        icon_pass.setOnClickListener(new View.OnClickListener() {
            boolean hidePass = false;
            @Override
            public void onClick(View v) {
                if (hidePass){
                    edtPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    icon_pass.setBackgroundResource(R.drawable.showpass);
                }else{
                    edtPassWord.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    icon_pass.setBackgroundResource(R.drawable.hidepass);
                }
                hidePass =! hidePass;
                edtPassWord.setSelection(edtPassWord.getText().length());
            }
        });

        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean("isRemember", false);
        if(check){
//            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            String phoneNumber = sharedPreferences.getString("phoneNumber","");
            String pass = sharedPreferences.getString("pass","");
            chkRemember.setChecked(check);
            edtPhoneNumber.setText(phoneNumber);
            edtPassWord.setText(pass);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                phoneNumber = edtPhoneNumber.getText().toString();
                pass = edtPassWord.getText().toString();

                boolean isCorrect = false;
                if (phoneNumber.length() > 0 && pass.length() > 0){

                    if(!isCorrect){
                        Toast.makeText(LoginActivity.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "Nhập user và pass", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
//nghiaa