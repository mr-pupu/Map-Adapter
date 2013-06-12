package com.alorma.mapdemo.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.alorma.mapadapter.data.bbdd.contract.LocationContract;
import com.alorma.mapadapter.data.bbdd.cursor.LocationCursor;
import com.alorma.mapadapter.data.bean.Location;
import com.alorma.mapadapter.ui.fragment.AbsMapedFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by alorma on 12/06/13.
 */
public class MapedFragment extends AbsMapedFragment implements GoogleMap.OnMapClickListener {

    @Override
    protected String getLngField() {
        return LocationContract.lng;
    }

    @Override
    protected String getLatField() {
        return LocationContract.lat;
    }

    @Override
    protected Uri getUri() {
        return LocationContract.URI;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getMap().setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Location loc = new Location();
        loc.setLat(latLng.latitude);
        loc.setLng(latLng.longitude);

        getMap().addMarker(new MarkerOptions().position(latLng));

        getActivity().getContentResolver().insert(LocationContract.URI, new LocationCursor().write(loc));
    }
}
