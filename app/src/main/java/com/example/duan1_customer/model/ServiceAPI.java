package com.example.duan1_customer.model;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceAPI {
    //https://sub1.tridinhne.click/api_customer/login.php
    //https://sub1.tridinhne.click/api_product/GetListProduct.php
    String BASE_Service = "https://apis.dinhnt.com";
    String Service_Customer = "https://sub1.tridinhne.click/api_customer/";
    String Service_Product = "https://sub1.tridinhne.click/api_product/";

    @GET("edu.json")
    Observable<ModelChannel> getChannel();

    @POST("login.php")
    Call<Customer> checkLogin(@Body Customer customer);
    @POST("UpdatePassCustomer.php")
    Call<Customer> editPass(@Body Customer customer);

    @POST("UpdateCustomer.php")
    Call<Customer> editUser(@Body Customer customer);

    @GET("GetListProduct.php")
    Call<ArrayList<Product>> getProduct();

    @GET("GetListBill.php")
    Call<ArrayList<Bill>> showHistory(@Query("phoneNumberCustomer") String phoneNumber);




}
