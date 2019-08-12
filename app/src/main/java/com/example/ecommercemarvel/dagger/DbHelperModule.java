package com.example.ecommercemarvel.dagger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ecommercemarvel.dbHelper.DbHelper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbHelperModule {

    private final Context context;

    public DbHelperModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context context() {
        return this.context;
    }

    @Singleton
    @Provides DbHelper provideDbHelper() {

        return new DbHelper(this.context);
    }

    @Provides @Named("writer")
    SQLiteDatabase provideWriter() {
        return provideDbHelper().getWritableDatabase();
    }

    @Provides @Named("reader")
    SQLiteDatabase providerReader(Context context) {
        return provideDbHelper().getReadableDatabase();
    }

}
