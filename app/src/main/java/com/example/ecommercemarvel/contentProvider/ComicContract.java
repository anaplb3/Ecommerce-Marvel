package com.example.ecommercemarvel.contentProvider;

import android.net.Uri;
import android.provider.BaseColumns;

public class ComicContract {

    public static final String AUTHORITY = "com.example.ecommercemarvel.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY);


    public static final class ComicEntry implements BaseColumns {

        static final String DB_TABLE = "comics";
        static final String COLUMN_ID = "comicID";
        static final String COLUMN_TITLE = "title";
        static final String COLUMN_DESCRIPTION = "description";
        static final String COLUMN_PRICE = "price";
        static final String COLUMN_URL_IMAGE = "urlImage";
        static final String COLUMN_IS_RARE = "isRare";


        public static final Uri CONTENT_URI = ComicContract.CONTENT_URI.buildUpon()
                .appendPath(DB_TABLE)
                .build();

        public static Uri buildComicUriWithId(long id) {
            return CONTENT_URI.buildUpon()
                    .appendPath(Long.toString(id))
                    .build();
        }

    }
}
