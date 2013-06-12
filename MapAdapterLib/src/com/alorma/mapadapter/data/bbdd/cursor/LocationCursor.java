package com.alorma.mapadapter.data.bbdd.cursor;

import android.content.ContentValues;
import android.database.Cursor;
import com.alorma.mapadapter.data.bbdd.contract.LocationContract;
import com.alorma.mapadapter.data.bean.Location;
import com.alorma.mapadapter.utils.CursorUtils;

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
public class LocationCursor extends BaseCursor<Location> {

    @Override
    public ContentValues write(Location loc) {

        ContentValues values = new ContentValues();

        values.put(LocationContract.name, loc.getName());
        values.put(LocationContract.lat, loc.getLat());
        values.put(LocationContract.lng, loc.getLng());

        return values;
    }

    @Override
    public Location read(Cursor cursor) {

        Location loc = new Location();

        CursorUtils cUtils = new CursorUtils(cursor);

        loc.setName(cUtils.getString(LocationContract.name));
        loc.setLat(cUtils.getDouble(LocationContract.lat));
        loc.setLng(cUtils.getDouble(LocationContract.lng));

        return loc;
    }
}
