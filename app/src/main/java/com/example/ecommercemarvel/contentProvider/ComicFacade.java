package com.example.ecommercemarvel.contentProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.ecommercemarvel.model.Comic;

import java.util.ArrayList;
import java.util.List;

public class ComicFacade {
    Context context;

    public ComicFacade(Context context) {
        this.context = context;
    }

    public void addingToCart(Comic comic) {

        ContentValues cv = new ContentValues();

        cv.put(ComicContract.ComicEntry.COLUMN_ID, comic.getId());
        cv.put(ComicContract.ComicEntry.COLUMN_TITLE, comic.getTitle());
        cv.put(ComicContract.ComicEntry.COLUMN_DESCRIPTION, comic.getDescription());
        cv.put(ComicContract.ComicEntry.COLUMN_PRICE, comic.getPrice());
        cv.put(ComicContract.ComicEntry.COLUMN_URL_IMAGE, comic.getUrlImage());
        cv.put(ComicContract.ComicEntry.COLUMN_IS_RARE, castingBooleanToInt(comic.isRare()));

        context.getContentResolver().insert(ComicContract.CONTENT_URI, cv);

        Toast.makeText(context, "Quadrinho adicionado no carrinho!", Toast.LENGTH_LONG).show();
    }

    private int castingBooleanToInt(boolean value) {
        return value ? 1 : 0;
    }

    private boolean castingIntToBoolean(int value) {
        return value == 1;
    }

    public Comic getComic(Uri uri) {

        String[] colunas = new String[] {ComicContract.ComicEntry.COLUMN_TITLE,
                ComicContract.ComicEntry.COLUMN_URL_IMAGE,
                ComicContract.ComicEntry.COLUMN_PRICE,
                ComicContract.ComicEntry.COLUMN_IS_RARE,
                ComicContract.ComicEntry.COLUMN_ID};


        Cursor cursor = context.getContentResolver().query(uri,
                colunas, null, null,null);

        int id = cursor.getInt(cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_ID));
        String title = cursor.getString(cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_TITLE));
        String url_image = cursor.getString(cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_URL_IMAGE));
        double price = cursor.getDouble(cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_PRICE));
        int isRare = cursor.getInt(cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_IS_RARE));
        boolean rare = castingIntToBoolean(isRare);

        return new Comic(id,title, price, rare, url_image);

    }

    public List<Comic> getComicsInTheCart() {

        Log.i("status", "entrou no get comics");

        String[] colunas = new String[] {ComicContract.ComicEntry.COLUMN_TITLE,
                ComicContract.ComicEntry.COLUMN_URL_IMAGE,
                ComicContract.ComicEntry.COLUMN_PRICE,
                ComicContract.ComicEntry.COLUMN_IS_RARE,
                ComicContract.ComicEntry.COLUMN_ID};


        List<Comic> comics = new ArrayList<>();
        String uriString;
        Uri _uri = Uri.parse(ComicContract.CONTENT_URI.toString()+"/comics");
        Cursor cursor = context.getContentResolver().query(_uri,
                colunas, null, null,null);

        if(cursor == null) {
            Log.i("Cursor status", "cursor tá null hein");
        } else if(cursor.getCount() < 1) {
            Log.i("Cursor status", "A busca não retornou nada hein");
        } else {
            Log.i("Cursor status count", ""+cursor.getCount());

            if(cursor.moveToFirst()) {

                while(cursor.moveToNext()) {

                    Log.i("while", "entrou no while");

                    Log.i("title index: ", ""+cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_TITLE));
                    Log.i("description index: ", ""+cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_DESCRIPTION));
                    Log.i("url image index: ", ""+cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_URL_IMAGE));
                    Log.i("price index: ", ""+cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_PRICE));
                    Log.i("is rare index: ", ""+cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_IS_RARE));


                    int id = cursor.getInt(cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_ID));
                    String title = cursor.getString(cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_TITLE));
                    String url_image = cursor.getString(cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_URL_IMAGE));
                    double price = cursor.getDouble(cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_PRICE));
                    int isRare = cursor.getInt(cursor.getColumnIndex(ComicContract.ComicEntry.COLUMN_IS_RARE));
                    boolean rare = castingIntToBoolean(isRare);

                    comics.add(new Comic(id,title, price, rare, url_image));
                }
            } else {
                Log.i("Cursor status:", "Não moveu pra o next");
            }



        }

        cursor.close();

        return comics;
    }

    public void deleteComic(int index) {

        Uri uri = ComicContract.ComicEntry.buildComicUriWithId(index);
        try {
            context.getContentResolver().delete(uri, null, null);
        } catch(Exception e) {
            Log.i("facade error", e.getMessage());
        }

    }

    public void updateTitleComic(Comic comic, String newTitle) {

        Uri uri = ComicContract.ComicEntry.buildComicUriWithId(comic.getId());

        ContentValues contentValues = new ContentValues();
        contentValues.put(ComicContract.ComicEntry.COLUMN_TITLE, newTitle);

        context.getContentResolver().update(uri, contentValues, null, null);
    }
}
