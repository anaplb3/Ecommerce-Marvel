package com.example.ecommercemarvel.service;


import javax.inject.Singleton;

import retrofit2.Retrofit;

@Singleton
public class MarvelPoolService {

    private Retrofit mRetrofit;

    public MarvelPoolService(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public <T> T getService(Class<T> clazz) {

        return mRetrofit.create(clazz);

    }

}
