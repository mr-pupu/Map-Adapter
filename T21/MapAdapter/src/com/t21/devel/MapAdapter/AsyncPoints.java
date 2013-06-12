package com.t21.devel.MapAdapter;

import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.maps.model.LatLng;
import com.t21.devel.MapAdapter.data.bbdd.contract.LocationContract;
import com.t21.devel.MapAdapter.data.bbdd.cursor.LocationCursor;
import com.t21.devel.MapAdapter.data.bean.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by alorma on 11/06/13.
 */
public class AsyncPoints extends AsyncTask<Void, Void, Void> {

    private final int number;
    private Context context;
    private HashMap<LatLng, String> hashPoints;

    public AsyncPoints(Context context, int number) {
        this.context = context;
        this.number = number;
        hashPoints = new HashMap<LatLng, String>();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        LocationCursor locationCursor = new LocationCursor();

        List<Location> locations = new ArrayList<Location>();

        int i = 0;

        ContentResolver cr = context.getContentResolver();

        do {

            Location loc = new Location();
            loc.setName("Point: " + i);

            double lat = Math.random() * 90d * randomizeSymbol();
            double lng = Math.random() * 180d * randomizeSymbol();

            loc.setLat(lat);
            loc.setLng(lng);

            LatLng ll = new LatLng(lat, lng);

            if (!hashPoints.containsKey(lat)) {
                hashPoints.put(ll, loc.getName());
            }

            locations.add(loc);

            cr.insert(LocationContract.URI, locationCursor.write(loc));

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {

            }

        } while (i++ < number);

        return null;
    }

    private Double randomizeSymbol() {
        Random rnd = new Random();
        if (rnd.nextInt(2) == 0) {
            return 1d;
        } else {
            return -1d;
        }
    }
}
