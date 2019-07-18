package com.example.ecommercemarvel.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
    private static int VERSION = 1;
    private static String NAME = "MarvelEcommerce";
    public static String nameTable = "checkoutItems";

    public DbHelper(Context context) {
        super(context, NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String criandoTabela = "CREATE TABLE IF NOT EXISTS " + nameTable + "(indice INTEGER PRIMARY KEY AUTOINCREMENT, idComic INTEGER, title VARCHAR, price REAL, isRare INTEGER DEFAULT 0)";

        try {
            db.execSQL(criandoTabela);
            Log.i("info db", "it worked!");
        } catch(Exception e) {
            Log.i("info db", "error: " + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
