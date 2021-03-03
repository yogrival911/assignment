package com.yogdroidtech.assignment;

import com.yogdroidtech.assignment.response.ItemsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("test-api")
    Call<ItemsResponse> getItems();
}
