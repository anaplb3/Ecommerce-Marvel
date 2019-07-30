package com.example.ecommercemarvel.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ecommercemarvel.model.Comic;

import java.util.ArrayList;
import java.util.List;

public class DbComics implements ComicsDAO{
    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public DbComics(Context context) {
        DbHelper db = new DbHelper(context);
        this.write = db.getWritableDatabase();
        this.read = db.getReadableDatabase();
    }


    @Override
    public void addingToTheCart(int idComic, String title, double price, int isRare) {
        ContentValues contentValues = new ContentValues();

        try {
            contentValues.put("idComic", idComic);
            contentValues.put("title", title);
            contentValues.put("price", price);
            contentValues.put("isRare", isRare);

            write.insert(DbHelper.nameTable, null, contentValues);
            Log.i("info", "it worked!");
        } catch (Exception e) {
            Log.i("info", "error: "+e.getMessage());
        }
    }

    @Override
    public List<Comic> getComicsInTheCart() {
        List<Comic> comics = new ArrayList<>();
        Comic comic;
        int idComic;
        String title;
        double price;
        int isRare;

        String query = "SELECT idComic, title, price, isRare FROM " + DbHelper.nameTable;


        Cursor cursor = read.rawQuery(query, null);

        // Getting columns index

        // Aparentemente o erro é aqui no id, pois a função gettingIndex tá dizendo que o index do id é -1 (??)
        int indexIdComic = gettingIndex(cursor, "idComics");

        //int indexIdComic = 0;
        int indexTitle = gettingIndex(cursor, "title");
        int indexPrice = gettingIndex(cursor, "price");
        int indexIsRare = gettingIndex(cursor, "isRare");


        Log.i("id", ""+indexIdComic);
        Log.i("title", ""+indexTitle);
        Log.i("price", ""+indexPrice);
        Log.i("is rare", ""+indexIsRare);

        if(cursor != null) {
            if(cursor.moveToFirst() && cursor.getCount() >= 1) {

                do {
                    // Outra coisa, não tem como pegar só esses atributos, já que na tela do checkout precisa das imagens e etc.

                    title = cursor.getString(indexTitle);
                    System.out.println("titulo: "+ title);
                    price = cursor.getDouble(indexPrice);
                    isRare = cursor.getInt(indexIsRare);
                    idComic = cursor.getInt(indexIdComic);

                    comic = new Comic(idComic, title, price, gettingBooleanValue(isRare));

                    comics.add(comic);
                } while (cursor.moveToNext());
            }
        }



        cursor.close();
        return comics;
    }

    private boolean gettingBooleanValue(int value) {

        return value != 0;
    }

    private int gettingIndex(Cursor cursor, String columnName) {

        return cursor.getColumnIndex(columnName);
    }
}
