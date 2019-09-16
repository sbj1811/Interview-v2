package com.zumepizza.interview.data.api;

import com.zumepizza.interview.model.Pizza;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndpointInterface {
    String API_ENDPOINT = "https://api.myjson.com";

    @GET("/bins/snyji")
    Call<List<Pizza>> getPizzas();
}