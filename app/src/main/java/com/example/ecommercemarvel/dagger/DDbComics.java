package com.example.ecommercemarvel.dagger;

import com.example.ecommercemarvel.dbHelper.DbComics;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = DbHelperModule.class)
public interface DDbComics {

   void inject(DbComics dbComics);
}
