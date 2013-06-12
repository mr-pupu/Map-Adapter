package com.alorma.mapadapter.utils;

import android.database.Cursor;
import android.provider.BaseColumns;

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
public class CursorUtils {

    private final Cursor cursor;

    public CursorUtils(Cursor cursor) {
        this.cursor = cursor;
    }

    public long getId(Cursor c) {
        return c.getLong(c.getColumnIndex(BaseColumns._ID));
    }

    public long getId() {
        return getLong(BaseColumns._ID);
    }

    public String getString(String column) {
        return cursor.getString(cursor.getColumnIndex(column));
    }

    public int getInt(String column) {
        return cursor.getInt(cursor.getColumnIndex(column));
    }

    public long getLong(String column) {
        return cursor.getLong(cursor.getColumnIndex(column));
    }

    public double getDouble(String column) {
        return cursor.getDouble(cursor.getColumnIndex(column));
    }

    public boolean getBoolean(String col) {
        return Boolean.parseBoolean(getString(col));
    }

    public static String getString(Cursor c, String column) {
        return c.getString(c.getColumnIndex(column));
    }

    public static int getInt(Cursor c, String column) {
        return c.getInt(c.getColumnIndex(column));
    }

    public static long getLong(Cursor c, String column) {
        return c.getLong(c.getColumnIndex(column));
    }

    public Double getDouble(Cursor c, String column) {
        return c.getDouble(c.getColumnIndex(column));
    }

    public static boolean getBoolean(Cursor c, String col) {
        return Boolean.parseBoolean(getString(c, col));
    }
}
