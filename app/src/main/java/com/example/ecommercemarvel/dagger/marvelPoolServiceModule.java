package com.example.ecommercemarvel.dagger;

import com.example.ecommercemarvel.controller.MarvelService;
import com.example.ecommercemarvel.service.MarvelPoolService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class marvelPoolServiceModule {


    @Provides static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides static MarvelPoolService provideMarvelPoolService(Retrofit retrofit) {
        return new MarvelPoolService(retrofit);
    }

    @Provides static MarvelService providerMarvelService(MarvelPoolService marvelPoolService) {
        return marvelPoolService.getService(MarvelService.class);
    }





}
