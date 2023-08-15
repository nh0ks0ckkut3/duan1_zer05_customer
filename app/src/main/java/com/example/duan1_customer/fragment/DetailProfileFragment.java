package com.example.duan1_customer.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_customer.MainActivity;
import com.example.duan1_customer.R;
import com.example.duan1_customer.model.Customer;
import com.example.duan1_customer.model.ServiceAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailProfileFragment extends Fragment {
    TextView tvName, tvPhone, tvEmail, tvDate, tvDress, tvGender, tvJob;
    ImageView ivHome, ivEditUser;
    AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_profile, container, false);
        tvName = view.findViewById(R.id.tvName);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvDate = view.findViewById(R.id.tvDate);
        tvDress = view.findViewById(R.id.tvDress);
        tvGender = view.findViewById(R.id.tvGender);
        tvJob = view.findViewById(R.id.tvJob);
        ivHome = view.findViewById(R.id.ivHome);
        ivEditUser = view.findViewById(R.id.ivEditUser);

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getContext()).finish();
            }
        });
        ivEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();

                view = inflater.inflate(R.layout.dialog_edit_user, null);
                EditText edtName = view.findViewById(R.id.edtName);
                EditText edtEmail = view.findViewById(R.id.edtEmail);
                EditText edtDate = view.findViewById(R.id.edtDate);
                EditText edtDress = view.findViewById(R.id.edtDress);
                EditText edtGender = view.findViewById(R.id.edtGender);
                EditText edtJob = view.findViewById(R.id.edtJob);
                ImageView ivHome = view.findViewById(R.id.ivHome);
                AppCompatButton btnChange = view.findViewById(R.id.btnChange);

                edtName.setText(((MainActivity)getContext()).customerCurrent.getName());
                edtEmail.setText(((MainActivity)getContext()).customerCurrent.getEmail());
                edtDate.setText(((MainActivity)getContext()).customerCurrent.getDate());
                edtDress.setText(((MainActivity)getContext()).customerCurrent.getAddress());
                edtGender.setText(((MainActivity)getContext()).customerCurrent.getGender());
                edtJob.setText(((MainActivity)getContext()).customerCurrent.getJob());
                ivHome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
                btnChange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = edtName.getText().toString();
                        String email = edtEmail.getText().toString();
                        String date = edtDate.getText().toString();
                        String address = edtDress.getText().toString();
                        String gender = edtGender.getText().toString();
                        String job = edtJob.getText().toString();

                        Customer customer = new Customer(((MainActivity)getContext()).customerCurrent.getPhoneNumber(), name, address, email, job, date, gender);

                        if (name.equals("") && email.equals("") && date.equals("") && address.equals("") && gender.equals("")){
                            Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }else{
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(ServiceAPI.Service_Customer)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            ServiceAPI service = retrofit.create(ServiceAPI.class);
                            Call<Customer> editUser = service.editUser(customer);
                            editUser.enqueue(new Callback<Customer>() {
                                @Override
                                public void onResponse(Call<Customer> call, Response<Customer> response) {
                                    Toast.makeText(getContext(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                                    ((MainActivity)getContext()).addFragment(new DetailProfileFragment());
                                    alertDialog.cancel();
                                }

                                @Override
                                public void onFailure(Call<Customer> call, Throwable t) {

                                }
                            });
                        }
                    }
                });




                builder.setView(view);
                alertDialog = builder.create();
                alertDialog.setCancelable(false);
                alertDialog.show();
            }
        });

        //lấy ra danh sách customer
        showInfoCustomer();

        return view;
    }
    private void showInfoCustomer(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceAPI.Service_Customer)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Customer customer = new Customer(((MainActivity)getContext()).customerCurrent.getPhoneNumber(), ((MainActivity)getContext()).customerCurrent.getPassWord());
        ServiceAPI service = retrofit.create(ServiceAPI.class);
        Call<Customer> listCustomer = service.checkLogin(customer);
        listCustomer.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Customer detailUser = response.body();
                tvName.setText(detailUser.getName());
                tvPhone.setText(detailUser.getPhoneNumber());
                tvEmail.setText(detailUser.getEmail());
                tvDate.setText(detailUser.getDate());
                tvDress.setText(detailUser.getAddress());
                tvGender.setText(detailUser.getGender());
                tvJob.setText(detailUser.getJob());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });


    }
}