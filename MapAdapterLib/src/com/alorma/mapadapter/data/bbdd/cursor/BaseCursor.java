package com.alorma.mapadapter.data.bbdd.cursor;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

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
public abstract class BaseCursor<K> {

    public abstract ContentValues write(K k);

    public abstract K read(Cursor cursor);

    public ContentValues[] write(List<K> ks) {

        ContentValues[] values = new ContentValues[ks.size()];

        int i = 0;
        for (K k : ks) {
            values[i++] = write(k);
        }

        return values;
    }

}
