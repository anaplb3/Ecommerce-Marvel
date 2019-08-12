package com.example.ecommercemarvel.dagger;


import com.example.ecommercemarvel.view.ComicsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = marvelPoolServiceModule.class)
public interface DMarvelService {


    void inject(ComicsActivity activity);


}
