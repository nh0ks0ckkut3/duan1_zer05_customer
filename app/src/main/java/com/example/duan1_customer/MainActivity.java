package com.example.duan1_customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.duan1_customer.fragment.ProfileFragment;
import com.example.duan1_customer.fragment.BookFragment;
import com.example.duan1_customer.fragment.HomeFragment;
import com.example.duan1_customer.fragment.NewsFragment;
import com.example.duan1_customer.fragment.ShopFragment;
import com.example.duan1_customer.model.Bill;
import com.example.duan1_customer.model.Customer;
import com.example.duan1_customer.model.Product;
import com.example.duan1_customer.model.Service;
import com.example.duan1_customer.model.ServiceAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Calendar;

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
    public String dateCurrent;
    private ArrayList<Service> listServiceSelectedAdd;
    private ArrayList<Product> listProductSelectedAdd;
    private Bill billAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
        Calendar calendar = Calendar.getInstance();
        String day,month;
        if((calendar.get(Calendar.MONTH)+1) < 10){
            month = "0" + (calendar.get(Calendar.MONTH)+1)+"/";
        }else{
            month = (calendar.get(Calendar.MONTH)+1)+"/";
        }
        if(calendar.get(Calendar.DAY_OF_MONTH) < 10){
            day = "0" + calendar.get(Calendar.DAY_OF_MONTH)+"/";
        }else{
            day = calendar.get(Calendar.DAY_OF_MONTH)+"/";
        }
        dateCurrent = day+month+calendar.get(Calendar.YEAR);

        if(!(billAdd != null)){
            billAdd = new Bill(phoneNumber,name,"","","",dateCurrent);
            listServiceSelectedAdd = new ArrayList<>();
            listProductSelectedAdd = new ArrayList<>();
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
                        fragment = new BookFragment();
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


    public Bill getBillAdd() {
        return billAdd;
    }

    public void setBillAdd(Bill billAdd) {
        this.billAdd = billAdd;
    }
    public ArrayList<Service> getListServiceSelectedAdd() {
        return listServiceSelectedAdd;
    }

    public void setListServiceSelectedAdd(ArrayList<Service> listServiceSelectedAdd) {
        this.listServiceSelectedAdd = listServiceSelectedAdd;
    }
    public ArrayList<Product> getListProductSelectedAdd() {
        return listProductSelectedAdd;
    }

    public void setListProductSelectedAdd(ArrayList<Product> listProductSelectedAdd) {
        this.listProductSelectedAdd = listProductSelectedAdd;
    }
}