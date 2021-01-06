package com.example.warehouseinventoryapp.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.net.URI;

public class ItemContentProvider extends ContentProvider {
    public static final String CONTENT_AUTHORITY = "fit2081.app.KE";

    public static final Uri CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    ItemDatabase db;
    private static final UriMatcher sUriMatcher = createUriMatcher();

    private static final int SINGLE_ROW_ITEMS = 1;
    private static final int MULTIPLE_ROWS_ITEMS = 2;

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        int deletionCount = 0;
        int uriType = sUriMatcher.match(uri);

        switch (uriType){
            case MULTIPLE_ROWS_ITEMS:
                deletionCount = db
                        .getOpenHelper()
                        .getWritableDatabase()
                        .delete(Item.TABLE_NAME,selection,selectionArgs);
                break;
            case SINGLE_ROW_ITEMS:
                String id = uri.getLastPathSegment();
                String selectionId = Item.COLUMN_ID + "=" + id;

                deletionCount = db
                        .getOpenHelper()
                        .getWritableDatabase()
                        .delete(Item.TABLE_NAME, selectionId, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        return deletionCount;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.

        long rowId = db
                .getOpenHelper()
                .getWritableDatabase()
                .insert(Item.TABLE_NAME, 0, values);

        return ContentUris.withAppendedId(CONTENT_URI,rowId);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        db = ItemDatabase.getDatabase(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(Item.TABLE_NAME);
        String query = builder.buildQuery(projection,selection,null,null,sortOrder,null);

        final Cursor cursor = db
                .getOpenHelper()
                .getReadableDatabase()
                .query(query, selectionArgs);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        int updateCount;
        updateCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .update(Item.TABLE_NAME,0,values,selection,selectionArgs);

        return updateCount;
    }

    private static UriMatcher createUriMatcher(){

        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CONTENT_AUTHORITY;

        uriMatcher.addURI(authority,Item.TABLE_NAME + "/#", SINGLE_ROW_ITEMS);
        uriMatcher.addURI(authority, Item.TABLE_NAME, MULTIPLE_ROWS_ITEMS);

        return uriMatcher;
    }
}