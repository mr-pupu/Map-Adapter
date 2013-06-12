package com.alorma.mapadapter.data.bbdd.contract;

import android.net.Uri;
import com.alorma.mapadapter.data.provider.LocationsMinion;
import com.alorma.mapadapter.utils.DbTablesUtils;

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
public class LocationContract implements BaseContract {

    public static final String TABLE = "location";
    public static final Uri URI = Uri.parse("content://" + LocationsMinion.AUTHORITY + "/" + LocationsMinion.PATH);
    public static final Uri ITEM_URI = Uri.parse("content://" + LocationsMinion.PATH + "/#");

    public static final String name = "name";
    public static final String lat = "lat";
    public static final String lng = "lng";

    @Override
    public String create() {

        DbTablesUtils dbUtils = new DbTablesUtils(TABLE);
        dbUtils.addParam(name, "TEXT");
        dbUtils.addParam(lat, "REAL");
        dbUtils.addParam(lng, "REAL");

        return dbUtils.create();
    }
}
