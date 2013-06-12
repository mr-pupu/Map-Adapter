package com.t21.devel.MapAdapter.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.t21.devel.MapAdapter.R;
import com.t21.devel.MapAdapter.data.bbdd.contract.LocationContract;
import com.t21.devel.MapAdapter.map.MapMarkersAdapter;

public class MainActivity extends Activity {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        if (map != null) {
            String[] locationFields = {LocationContract.lat, LocationContract.lng};
            new MapMarkersAdapter(this, map, LocationContract.URI, locationFields);
        }
    }

}
