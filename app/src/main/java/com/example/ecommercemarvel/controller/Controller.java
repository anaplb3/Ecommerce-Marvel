package com.example.ecommercemarvel.controller;

import android.util.Log;


import com.example.ecommercemarvel.model.ResponseDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Controller implements Callback<ResponseDTO> {
    private static final String URL_BASE = "https://gateway.marvel.com/";
    private static final String PUBLIC_KEY ="43de5ac9c8f743f13ca4e01040a03e69";

    private ResponseDTO responseDTO;

    /**
     * Método que conecta a API da Marvel
     */
    public void create() {
        Gson gsonConverter = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gsonConverter))
                .build();

        MarvelService marvelAPI = retrofit.create(MarvelService.class);

        String ts = "anaehumapessoaincrivelmentetop";
        String hush = "2164d8107865cd34e930ce21b2066289";

        Call<ResponseDTO> comics = marvelAPI.loadComics(ts, PUBLIC_KEY, hush);
        comics.enqueue(this);

    }

    public ResponseDTO getResponseDTO() {
        return responseDTO;
    }

    @Override
    public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
        Log.i("comic", "on response");

        Log.i("url", ""+response.raw().request().url());

        if(response.isSuccessful()) {
            Log.i("comic", "on response2");
            responseDTO = response.body();

        } else {
            Log.i("comic", ""+response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<ResponseDTO> call, Throwable t) {
        Log.i("comic", "então né, deu erro");
        Log.i("erro", t.getMessage());

    }
}
