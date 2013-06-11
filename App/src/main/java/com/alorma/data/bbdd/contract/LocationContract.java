package com.alorma.data.bbdd.contract;

import android.net.Uri;
import com.alorma.data.provider.AppProvider;
import com.alorma.data.provider.LocationsMinion;
import com.alorma.utils.DbTablesUtils;

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
        dbUtils.addParam(name, "text");
        dbUtils.addParam(lat, "text");
        dbUtils.addParam(lng, "text");

        return dbUtils.create();
    }
}
