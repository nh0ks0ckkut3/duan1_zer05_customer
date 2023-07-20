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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    Fragment fragment;
    FrameLayout fl_main;
    Customer customerCurrent;
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
        customerCurrent = (Customer) bundle.getSerializable("customerCurrent");
        if(listServiceChose == null || listServiceChose.size()<1){
            listServiceChose = new ArrayList<>();
            listServiceChose.add(serviceDAO.chooseOne(1));
        }

        Toast.makeText(this, customerCurrent.getName(), Toast.LENGTH_SHORT).show();

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
        EditText edtNewPass = view.findViewById(R.id.edtNewPassword);
        EditText edtRePass = view.findViewById(R.id.edtRePassword);
        AppCompatButton btnCancel = view.findViewById(R.id.btnCancel);
        AppCompatButton btnChange = view.findViewById(R.id.btnChange);

        if(!(customerDAO instanceof CustomerDAO)){
            customerDAO = new CustomerDAO(MainActivity.this);
        }


        builder.setView(view);


        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

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
                        int check = customerDAO.changePassword(customerCurrent.getPhoneNumber(), olpPass, newPass);
                        if (check == 1) {
                            Toast.makeText(MainActivity.this, "đã lưu", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            customerCurrent = null;
                            finish();
                            startActivity(intent1);
                        } else if (check == 0) {
                            Toast.makeText(MainActivity.this, "mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "có lỗi, thử lại sau", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "nhập mật khẩu mới không trùng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    public void showDialogConfirm () {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this).setNegativeButton("có", null).setPositiveButton("hủy", null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_confirm, null);
        if(!(customerDAO instanceof CustomerDAO)){
            customerDAO = new CustomerDAO(MainActivity.this);
        }
        builder.setView(view);


        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        alertDialog.getButton(alertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                customerCurrent = null;
                finish();
                startActivity(intent1);
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