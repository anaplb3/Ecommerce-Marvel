package com.example.ecommercemarvel.dagger;


import com.example.ecommercemarvel.service.MarvelPoolService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = marvelPoolServiceModule.class)
public interface DMarvelService {

    MarvelPoolService getMarvelPoolService();


}
