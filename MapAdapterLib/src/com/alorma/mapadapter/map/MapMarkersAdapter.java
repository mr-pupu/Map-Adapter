package com.alorma.mapadapter.map;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.alorma.mapadapter.data.bbdd.contract.LocationContract;
import com.alorma.mapadapter.utils.CursorUtils;
import com.alorma.mapadapter.utils.LoaderId;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;

/**
 * Created with IntelliJ IDEA.
 * User: A557114
 * Date: 12/06/13
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class MapMarkersAdapter implements LoaderManager.LoaderCallbacks<Cursor>, GoogleMap.OnCameraChangeListener {

    public String getLatField() {
        return latField;
    }

    public void setLatField(String latField) {
        this.latField = latField;
    }

    public String getLngField() {
        return lngField;
    }

    public void setLngField(String lngField) {
        this.lngField = lngField;
    }

    private String latField;
    private String lngField;

    public void setMap(GoogleMap map) {
        this.map = map;
    }

    private GoogleMap map;
    private int loaderId;
    private Activity context;
    private Uri uri;
    private VisibleRegion visibleRegion;

    public MapMarkersAdapter() {

    }

    public MapMarkersAdapter(Activity context, GoogleMap map, Uri uri, String lat, String lng) {
        this.context = context;
        this.map = map;
        this.uri = uri;
        this.latField = lat;
        this.lngField = lng;
    }

    public void start() throws NullPointerException {
        if (map == null) {
            throw new NullPointerException();
        }

        this.visibleRegion = map.getProjection().getVisibleRegion();

        map.setOnCameraChangeListener(this);

        if (context == null) {
            throw new NullPointerException();
        }

        loaderId = LoaderId.getId();

        context.getLoaderManager().initLoader(loaderId, null, this);
    }

    public GoogleMap getMap() {
        return map;
    }

    public int getLoaderId() {
        return loaderId;
    }

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(context, uri, null, createWhereClause(), null, null);
    }

    private String createWhereClause() {

        StringBuffer buf = new StringBuffer();

        if (latField != null || lngField != null) {
            LatLng ne = visibleRegion.latLngBounds.northeast;
            LatLng sw = visibleRegion.latLngBounds.southwest;

            buf.append(latField + "<" + ne.latitude);
            buf.append(" AND ");
            buf.append(latField + ">" + sw.latitude);
            buf.append(" AND ");
            buf.append(lngField + "<" + ne.longitude);
            buf.append(" AND ");
            buf.append(lngField + ">" + sw.longitude);

        }
        return buf.toString();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    MarkerOptions opt = cursorToMarker(cursor);

                    map.addMarker(opt);
                } while (cursor.moveToNext());
            }
        }
    }

    private MarkerOptions cursorToMarker(Cursor cursor) {

        CursorUtils cu = new CursorUtils(cursor);

        LatLng latLng = new LatLng(cu.getDouble(latField), cu.getDouble(lngField));

        MarkerOptions mo = new MarkerOptions().position(latLng);

        if (cu.getString(LocationContract.name) != null && !cu.getString(LocationContract.name).equals("")) {
            mo.title(cu.getString(LocationContract.name));
        }

        return mo;
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
