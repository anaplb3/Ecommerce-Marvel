package com.example.ecommercemarvel.contentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.ecommercemarvel.contentProvider.ComicContract.ComicEntry.DB_TABLE;

public class ComicDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Comics.db";
    private static final int DB_VERSION = 1;

    public ComicDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, comicID INTEGER, title TEXT, description TEXT, " +
                "price REAL, urlImage TEXT, isRare INTEGER DEFAULT 0);";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        onCreate(sqLiteDatabase);

    }
}
