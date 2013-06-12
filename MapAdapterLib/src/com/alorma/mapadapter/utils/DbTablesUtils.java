package com.alorma.mapadapter.utils;

import android.provider.BaseColumns;

import java.util.HashMap;

/**
 * Created by alorma on 25/05/13.
 */
public class DbTablesUtils {

    private String table;
    private HashMap<String, String> params;

    public DbTablesUtils(String table) {
        this.table = table;
        params = new HashMap<String, String>();
    }

    public void addParam(String col, String type) {
        if (params == null) {
            params = new HashMap<String, String>();
        }
        params.put(col, type);
    }

    public String create() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("CREATE TABLE ");
        buffer.append(table);
        buffer.append(" ");
        buffer.append("(");
        buffer.append(BaseColumns._ID + " INTEGER PRIMARY KEY");
        for (String key : params.keySet()) {
            buffer.append(", ");
            buffer.append(key);
            buffer.append(" ");
            buffer.append(params.get(key));
        }
        buffer.append(");");

        return buffer.toString();
    }


}
