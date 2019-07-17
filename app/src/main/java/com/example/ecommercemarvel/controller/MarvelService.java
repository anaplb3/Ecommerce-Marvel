package com.example.ecommercemarvel.controller;


import com.example.ecommercemarvel.model.ResponseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelService {



    @GET("v1/public/comics")
    Call<ResponseDTO> loadComics(@Query("ts") String ts, @Query("apikey") String apikey, @Query("hash") String hash);



}
