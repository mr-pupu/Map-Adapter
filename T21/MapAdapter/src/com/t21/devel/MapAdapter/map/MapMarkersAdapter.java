package com.t21.devel.MapAdapter.map;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.*;
import com.t21.devel.MapAdapter.data.bbdd.contract.LocationContract;
import com.t21.devel.MapAdapter.utils.CursorUtils;
import com.t21.devel.MapAdapter.utils.LoaderId;

/**
 * Created with IntelliJ IDEA.
 * User: A557114
 * Date: 12/06/13
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class MapMarkersAdapter implements LoaderManager.LoaderCallbacks<Cursor>, GoogleMap.OnCameraChangeListener {

    private final GoogleMap map;
    private final int loaderId;
    private Activity context;
    private Uri uri;
    private String[] fields;
    private VisibleRegion visibleRegion;

    public MapMarkersAdapter(Activity context, GoogleMap map, Uri uri, String[] fields) {
        this.context = context;
        this.map = map;
        this.uri = uri;
        this.fields = fields;

        this.visibleRegion = map.getProjection().getVisibleRegion();

        map.setOnCameraChangeListener(this);

        loaderId = LoaderId.getId();

        context.getLoaderManager().initLoader(loaderId, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(context, uri, null, createWhereClause(), null, null);
    }

    private String createWhereClause() {

        LatLng ne = visibleRegion.latLngBounds.northeast;
        LatLng sw = visibleRegion.latLngBounds.southwest;

        String latField = fields[0];
        String lngField = fields[1];

        StringBuffer buf = new StringBuffer();
        buf.append(latField + "<" + ne.latitude);
        buf.append(" AND ");
        buf.append(latField + ">" + sw.latitude);
        buf.append(" AND ");
        buf.append(lngField + "<" + ne.longitude);
        buf.append(" AND ");
        buf.append(lngField + ">" + sw.longitude);

        Log.i("TAG", buf.toString());

        return buf.toString();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        if (cursor.moveToFirst()) {
            Toast.makeText(context, "Showing " + cursor.getCount() + " points", Toast.LENGTH_SHORT).show();
            do {
                MarkerOptions opt = cursorToMarker(cursor);

                map.addMarker(opt);
            } while (cursor.moveToNext());
        }
    }

    private MarkerOptions cursorToMarker(Cursor cursor) {

        CursorUtils cu = new CursorUtils(cursor);

        LatLng latLng = new LatLng(cu.getDouble(fields[0]), cu.getDouble(fields[1]));

        Log.i("TAG", "Point: " + cu.getId() + " LAT: " + latLng.latitude + " LNG: " + latLng.longitude);

        return new MarkerOptions().position(latLng);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        map.clear();
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        map.clear();
        this.visibleRegion = map.getProjection().getVisibleRegion();
        context.getLoaderManager().restartLoader(loaderId, null, this);
    }
}
