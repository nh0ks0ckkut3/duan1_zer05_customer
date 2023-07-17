package com.example.duan1_customer.model;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ServiceAPI {
    String BASE_Service = "https://apis.dinhnt.com";

    @GET("edu.json")
    Observable<ModelChannel> getChannel();
}
