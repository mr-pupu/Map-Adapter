package com.alorma.mapadapter.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.alorma.mapadapter.map.MapMarkersAdapter;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by alorma on 12/06/13.
 */
public abstract class AbsMapedFragment extends MapFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getMap() != null) {
            MapMarkersAdapter mapAdapter = new MapMarkersAdapter();

            mapAdapter.setMap(getMap());
            mapAdapter.setContext(getActivity());
            mapAdapter.setUri(getUri());
            mapAdapter.setLatField(getLatField());
            mapAdapter.setLngField(getLngField());

            mapAdapter.start();
        }
    }

    protected abstract String getLngField();

    protected abstract String getLatField();

    protected abstract Uri getUri();


}
