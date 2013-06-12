package com.alorma.mapadapter.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.alorma.mapadapter.map.MapMarkersAdapter;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

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
