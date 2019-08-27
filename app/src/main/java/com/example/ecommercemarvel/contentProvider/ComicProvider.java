package com.example.ecommercemarvel.contentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class ComicProvider extends ContentProvider {
    private ComicDbHelper comicDbHelper;

    public static final int CODE_COMIC = 1;
    public static final int CODE_COMIC_WITH_ID = 2;
    public static final String DB_TABLE = ComicContract.ComicEntry.DB_TABLE;

    private static final UriMatcher uriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = ComicContract.AUTHORITY;

        matcher.addURI(authority, DB_TABLE, CODE_COMIC);
        matcher.addURI(authority, DB_TABLE+"/#", CODE_COMIC_WITH_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        comicDbHelper = new ComicDbHelper(getContext());

        return comicDbHelper != null;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortColumn) {
        Log.i("query", "began");
        Cursor cursor = null;

        Log.i("uri:", uri.toString());


        switch (uriMatcher.match(uri)) {

            case CODE_COMIC:
                Log.i("switch", "code comic");
                cursor = comicDbHelper.getReadableDatabase().query(
                        DB_TABLE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortColumn);

            break;

            case CODE_COMIC_WITH_ID:
                Log.i("switch", "code comic with id");
                String _ID = uri.getLastPathSegment();

                String[] args = new String[]{_ID};

                cursor = comicDbHelper.getReadableDatabase().query(
                        DB_TABLE,
                        projection,
                        ComicContract.ComicEntry._ID + " = ? ",
                        args,
                        null,
                        null,
                        sortColumn
                );
            break;
        }

        return cursor;
    }


    @Override
    public String getType( Uri uri) {
        return null;
    }


    @Override
    public Uri insert( Uri uri,  ContentValues contentValues) {
        Log.i("insert", "began");
        final SQLiteDatabase db = comicDbHelper.getWritableDatabase();

        long rowID = db.insert(DB_TABLE, null, contentValues);

        if(rowID > 0) {
            Log.i("if", "entrou no if");
            Uri _uri = ContentUris.withAppendedId(ComicContract.CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);

            Log.i("uri", _uri.toString());

            return _uri;
        }

        return null;

    }

    @Override
    public int delete( Uri uri,  String selection,  String[] selectionArgs) {

        int rows = 0;
        Uri _uri = null;

        SQLiteDatabase db = comicDbHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {

            case CODE_COMIC:
                Log.i("switch delete", "code comic");
                rows = db.delete(DB_TABLE, selection, selectionArgs);

            break;

            case CODE_COMIC_WITH_ID:
                Log.i("switch delete", "code comic with id");
                String comicID = uri.getLastPathSegment();
                String where = ComicContract.ComicEntry.COLUMN_ID+" = ?";
                String args[] = {comicID};

                rows = db.delete(DB_TABLE, where, args);

                _uri = ContentUris.withAppendedId(ComicContract.CONTENT_URI, rows);

                break;
        }

        getContext().getContentResolver().notifyChange(_uri, null);

        Log.i("rows", ""+rows);

        return rows;
    }

    @Override
    public int update( Uri uri,  ContentValues contentValues,  String selection,  String[] args) {

        int rows = 0;
        Uri uri1 = null;

        SQLiteDatabase db = comicDbHelper.getWritableDatabase();

        switch(uriMatcher.match(uri)) {

            case CODE_COMIC:
                Log.i("switch", "code comic");
                rows = db.update(DB_TABLE, contentValues, selection, args);

            break;

            case CODE_COMIC_WITH_ID:
                Log.i("switch", "code comic with id");
                String comicId = uri.getLastPathSegment();
                String where = ComicContract.ComicEntry.COLUMN_ID+" = ?";
                String arguments[] = {comicId};
                rows = db.update(DB_TABLE, contentValues, where, arguments );

            break;
        }

        uri1 = ContentUris.withAppendedId(ComicContract.CONTENT_URI, rows);

        getContext().getContentResolver().notifyChange(uri1, null);

        return rows;
    }
}
