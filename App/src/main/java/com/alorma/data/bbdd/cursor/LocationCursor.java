package com.alorma.data.bbdd.cursor;

import android.content.ContentValues;
import android.database.Cursor;
import com.alorma.data.bbdd.contract.LocationContract;
import com.alorma.data.bean.Location;
import com.alorma.utils.CursorUtils;

/**
 * Created by alorma on 25/05/13.
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
