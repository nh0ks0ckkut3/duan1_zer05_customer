package com.example.duan1_customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_customer.model.Customer;
import com.example.duan1_customer.model.ServiceAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterPassActivity extends AppCompatActivity {
    TextView tvSetPhone;
    EditText edtPass;
    ImageView ivOut, ivDelete, ivRight;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pass);
        tvSetPhone = findViewById(R.id.tvSetPhone);
        edtPass = findViewById(R.id.edtPass);
        ivOut = findViewById(R.id.ivOut);
        ivDelete = findViewById(R.id.ivDelete);
        ivRight = findViewById(R.id.ivRight);


        Bundle bundle = getIntent().getExtras();
        String numPhone = bundle.getString("numPhone");
        tvSetPhone.setText(numPhone);
        ivOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPass.setText("");
            }
        });

        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pass = edtPass.getText().toString();
                if (pass.equals("")){
                    Toast.makeText(RegisterPassActivity.this, "Nhập password", Toast.LENGTH_SHORT).show();
                }else if(pass.length() < 5){
                    Toast.makeText(RegisterPassActivity.this, "Nhập password trên 5 ký tự", Toast.LENGTH_SHORT).show();
                }else{
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ServiceAPI.Service_Customer)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ServiceAPI service = retrofit.create(ServiceAPI.class);
                    Customer customer = new Customer(numPhone, pass);
                    Call<Customer> checkLogin = service.checkLogin(customer);
                    checkLogin.enqueue(new Callback<Customer>() {
                        @Override
                        public void onResponse(Call<Customer> call, Response<Customer> response) {
                            Customer listCustomer = response.body();
                            if(listCustomer == null){
                                Intent intentMain = new Intent(RegisterPassActivity.this, RegisterNewPassActivity.class);
                                startActivity(intentMain);
                            }else{
                                Toast.makeText(RegisterPassActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent intentMain = new Intent(RegisterPassActivity.this, MainActivity.class);
                                Bundle bundleListCustomer = new Bundle();
                                bundleListCustomer.putString("phoneNumberCustomer", listCustomer.getPhoneNumber());
                                bundleListCustomer.putString("passWord", pass);
                                bundleListCustomer.putString("name", listCustomer.getName());
                                bundleListCustomer.putInt("age", listCustomer.getAge());
                                bundleListCustomer.putString("gender", listCustomer.getGender());
                                bundleListCustomer.putInt("totalSpend", listCustomer.getTotalSpend());
                                bundleListCustomer.putString("address", listCustomer.getAddress());
                                bundleListCustomer.putString("email", listCustomer.getEmail());
                                bundleListCustomer.putString("job", listCustomer.getJob());
                                bundleListCustomer.putString("date", listCustomer.getDate());
                                intentMain.putExtras(bundleListCustomer);
                                startActivity(intentMain);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Customer> call, Throwable t) {
                            Toast.makeText(RegisterPassActivity.this, "Lỗi API", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });




    }
}