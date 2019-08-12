package com.example.ecommercemarvel.service;


import retrofit2.Retrofit;


import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MarvelPoolService {

    @Inject Retrofit retrofit;

    @Inject
    public MarvelPoolService() {}


    /*private MarvelPoolService() {

        Gson gsonConverter = new GsonBuilder()
                .setLenient()
                .create();

        //Aqui tb
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create(gsonConverter))
                .build();
    }

    public static MarvelPoolService getInstance() {
        return marvelPoolService;
    }*/

    public <T> T getService(Class<T> clazz) {

        return retrofit.create(clazz);

    }

}
