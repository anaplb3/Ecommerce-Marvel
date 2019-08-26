package com.example.ecommercemarvel.controller;


import com.example.ecommercemarvel.model.ResponseDTO;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelService {


    @GET("v1/public/comics")
    Observable<ResponseDTO> getComics(@Query("ts") String ts, @Query("apikey") String apikey, @Query("hash") String hash);

}
