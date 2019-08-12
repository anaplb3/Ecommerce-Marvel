package com.example.ecommercemarvel.dagger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ecommercemarvel.dbHelper.DbComics;
import com.example.ecommercemarvel.dbHelper.DbHelper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = DbHelperModule.class)
public interface DDbComics {

    DbHelper getDbHelper();
    @Named("writer") SQLiteDatabase getWriter();
    @Named("reader") SQLiteDatabase getReader();

    Context context();

   // void inject(DbComics dbComics);
}
