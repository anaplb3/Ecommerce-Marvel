package com.example.ecommercemarvel.controller;

import android.util.Log;


import com.example.ecommercemarvel.model.ResponseDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Controller implements Callback<ResponseDTO> {
    private static final String urlBase = "https://gateway.marvel.com/";
    private static final String publicKey ="43de5ac9c8f743f13ca4e01040a03e69";
    private boolean loading;

    private ResponseDTO responseDTO;


    public Controller() {
        this.loading = true;
    }

    /**
     * Método que conecta a API da Marvel
     */
    public void create() {
        Gson gsonConverter = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create(gsonConverter))
                .build();

        MarvelService marvelAPI = retrofit.create(MarvelService.class);

        String ts = "anaehumapessoaincrivelmentetop";
        String hash = "2164d8107865cd34e930ce21b2066289";

        Call<ResponseDTO> comics = marvelAPI.loadComics(ts, publicKey, hash);
        comics.enqueue(this);

    }

    public ResponseDTO getResponseDTO() {
        return this.responseDTO;
    }

    @Override
    public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {

        Log.i("conexao", "on response");

        if(response.isSuccessful()) {

            Log.i("conexao", "succesful");

            System.out.println("conectou");

            this.loading = !this.loading;


            this.responseDTO =  response.body(); //new ResponseDTO(response.body().getCode(), response.body().getStatus(), response.body().getData());

            System.out.println(response.code());

            for(int i = 0; i < 5; i++) {
                Log.i("hq", responseDTO.getData().getComics().get(i).getTitle());
            }


        } else {

            Log.i("conexao", "not succesful");
            System.out.println("conectou mas deu errado");
            responseDTO = null;
        }
    }

    public boolean isLoading() {
        return loading;
    }

    @Override
    public void onFailure(Call<ResponseDTO> call, Throwable t) {
        responseDTO = null;
        Log.i("conexao", "nao conectou");
        System.out.println("não conectou");
        System.out.println(t.getMessage());
    }
}
