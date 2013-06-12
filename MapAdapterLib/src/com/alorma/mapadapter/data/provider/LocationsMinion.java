package com.alorma.mapadapter.data.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.alorma.mapadapter.data.bbdd.DbHelper;
import com.alorma.mapadapter.data.bbdd.contract.LocationContract;

/**
 * This file is part of Map Adapter Library.
 * <p/>
 * Map Adapter Library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * Map Adapter Library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with Map Adapter Library.  If not, see <http://www.gnu.org/licenses/>.
 * <p/>
 *
 * Created by alorma on 11/06/13.
 */
public class LocationsMinion extends ContentProvider {


    public static final String PATH = "points";
    public static final String AUTHORITY = "com.alorma.mapadapter.provider";

    private SQLiteDatabase db;

    public SQLiteDatabase getDb() {
        if (db == null) {
            db = new DbHelper(getContext()).getWritableDatabase();
        }
        return db;
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return getDb().query(LocationContract.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        return ContentResolver.CURSOR_DIR_BASE_TYPE + "/com.alorma.points";
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Long newId = getDb().insert(LocationContract.TABLE, null, values);

        Uri newUri = Uri.withAppendedPath(LocationContract.ITEM_URI, newId.toString());

        return newUri;
    }

    @Override
    public int delete(Uri uri, String where, String[] selectionArgs) {
        return getDb().delete(LocationContract.TABLE, where, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] selectionArgs) {
        return getDb().update(LocationContract.TABLE, values, where, selectionArgs);
    }
}
