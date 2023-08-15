package com.example.duan1_customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duan1_customer.adapter.ListServiceChooseAdapter;
import com.example.duan1_customer.database.CustomerDAO;
import com.example.duan1_customer.database.ServiceDAO;
import com.example.duan1_customer.fragment.ProfileFragment;
import com.example.duan1_customer.fragment.BookFragment;
import com.example.duan1_customer.fragment.HomeFragment;
import com.example.duan1_customer.fragment.NewsFragment;
import com.example.duan1_customer.fragment.ShopFragment;
import com.example.duan1_customer.model.Customer;
import com.example.duan1_customer.model.Service;
import com.example.duan1_customer.model.ServiceAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    Fragment fragment;
    FrameLayout fl_main;
    public Customer customerCurrent;
    CustomerDAO customerDAO;
    ServiceDAO serviceDAO;
    ArrayList<Service> listServiceChose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceDAO = new ServiceDAO(MainActivity.this);
        fl_main = findViewById(R.id.fl_main);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
//        customerCurrent = (Customer) bundle.getSerializable("customerCurrent");
        String phoneNumber =  bundle.getString("phoneNumberCustomer");
        String passWord = bundle.getString("passWord");
        String name = bundle.getString("name");
        int age = bundle.getInt("age");
        String gender = bundle.getString("gender");
        int totalSpend = bundle.getInt("totalSpend");
        String address = bundle.getString("address");
        String email = bundle.getString("email");
        String job = bundle.getString("job");
        String date = bundle.getString("date");
        customerCurrent = new Customer(phoneNumber, passWord, name, age, gender, totalSpend, address, email, job, date);

        if(listServiceChose == null || listServiceChose.size()<1){
            listServiceChose = new ArrayList<>();
            listServiceChose.add(serviceDAO.chooseOne(1));
        }



        //ánh xạ
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottomNavigationView) ;
        fragmentManager = getSupportFragmentManager();
        fragment = new HomeFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fl_main, fragment)
                .commit();

        // click item bottomNavigation

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    default:
                    case R.id.menu_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.menu_shop:
                        fragment = new ShopFragment();
                        break;

                    case R.id.menu_book:
                        fragment = new BookFragment(listServiceChose);
                        break;
                    case R.id.menu_news:
                        fragment = new NewsFragment();
                        break;
                    case R.id.menu_profile:
                        fragment = new ProfileFragment();
                        break;
                }
                fragmentManager.beginTransaction()
                        .replace(R.id.fl_main, fragment)
                        .commit();
                return true;
            }
        });

    }
    public void addFragment(Fragment fragmentAdd){
        fragmentManager.beginTransaction()
                .replace(R.id.fl_main, fragmentAdd)
                .commit();
    };
    public void showDialogChangePass() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_password, null);
        EditText edtOldPass = view.findViewById(R.id.edtOldPassword);
        ImageView ivOut = view.findViewById(R.id.ivOut);
        EditText edtNewPass = view.findViewById(R.id.edtNewPassword);
        EditText edtRePass = view.findViewById(R.id.edtRePassword);
        AppCompatButton btnChange = view.findViewById(R.id.btnChange);

        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        ivOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String olpPass = edtOldPass.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String rePass = edtRePass.getText().toString();

                if (olpPass.length() == 0 || newPass.length() == 0 || rePass.length() == 0) {
                    Toast.makeText(MainActivity.this, "vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if ((newPass.equals(rePass))) {
                        
                        Customer customer = new Customer(customerCurrent.getPhoneNumber(), newPass);
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(ServiceAPI.Service_Customer)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        ServiceAPI service = retrofit.create(ServiceAPI.class);
                        Call<Customer> editPass = service.editPass(customer);
                        editPass.enqueue(new Callback<Customer>() {
                            @Override
                            public void onResponse(Call<Customer> call, Response<Customer> response) {
                                Toast.makeText(MainActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                                alertDialog.cancel();
                            }

                            @Override
                            public void onFailure(Call<Customer> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "Lỗi API", Toast.LENGTH_SHORT).show();
                            }

                        });
                    }
                }
            }
        });
    }
    public void showDialogConfirm () {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_confirm, null);
        AppCompatButton btnCancel = findViewById(R.id.btnCancel);
        AppCompatButton btnChange = findViewById(R.id.btnChange);

        builder.setView(view);


        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginRegisterActivity.class));
                finish();
            }
        });
    }
    public void showDialogChooseService(ArrayList<Service> listService,ArrayList<Service> listServiceChose){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_choose_service, null);
        RecyclerView rcView = view.findViewById(R.id.rcView);
        ImageView btnBack = view.findViewById(R.id.btnBack);

        serviceDAO = new ServiceDAO(MainActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
        rcView.setLayoutManager(gridLayoutManager);
        ListServiceChooseAdapter listServiceChooseAdapter = new ListServiceChooseAdapter(MainActivity.this, listService, listServiceChose);
        rcView.setAdapter(listServiceChooseAdapter);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new BookFragment(listServiceChose));
                alertDialog.dismiss();
            }
        });




    }
    public void addService(int id){
        listServiceChose.add(serviceDAO.chooseOne(id));
    }
    public void minusService(int index){
        listServiceChose.remove(index);
    }
}