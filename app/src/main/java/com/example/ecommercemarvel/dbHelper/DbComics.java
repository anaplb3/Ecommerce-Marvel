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
        int indexIdComic = gettingIndex(cursor, "idComics");
        int indexTitle = gettingIndex(cursor, "title");
        int indexprice = gettingIndex(cursor, "price");
        int indexIsRare = gettingIndex(cursor, "isRare");

        if(cursor != null) {
            if(cursor.moveToFirst() && cursor.getCount() >= 1) {

                do {
                    idComic = cursor.getInt(indexIdComic);
                    title = cursor.getString(indexTitle);
                    price = cursor.getDouble(indexprice);
                    isRare = cursor.getInt(indexIsRare);

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
