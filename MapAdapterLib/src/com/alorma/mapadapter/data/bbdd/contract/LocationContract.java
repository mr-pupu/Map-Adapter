package com.alorma.mapadapter.data.bbdd.contract;

import android.net.Uri;
import com.alorma.mapadapter.data.provider.LocationsMinion;
import com.alorma.mapadapter.utils.DbTablesUtils;

/**
 * Created by alorma on 25/05/13.
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
