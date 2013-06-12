package com.t21.devel.MapAdapter.data.bbdd.contract;

import android.net.Uri;
import com.t21.devel.MapAdapter.data.provider.AppProvider;
import com.t21.devel.MapAdapter.data.provider.LocationsMinion;
import com.t21.devel.MapAdapter.utils.DbTablesUtils;

/**
 * Created by alorma on 25/05/13.
 */
public class LocationContract implements BaseContract {

    public static final String TABLE = "location";
    public static final Uri URI = Uri.parse("content://" + AppProvider.AUTHORITY + "/" + LocationsMinion.PATH);

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
