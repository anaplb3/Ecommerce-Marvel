package com.example.ecommercemarvel.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarvelPoolService {
    private static final String urlBase = "https://gateway.marvel.com/";
    private static MarvelPoolService marvelPoolService = new MarvelPoolService();

    Retrofit retrofit;


    private MarvelPoolService() {

        Gson gsonConverter = new GsonBuilder()
                .setLenient()
                .create();


        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create(gsonConverter))
                .build();
    }

    public static MarvelPoolService getInstance() {
        return marvelPoolService;
    }

    public <T> T getService(Class<T> clazz) {

        return retrofit.create(clazz);

    }

}
