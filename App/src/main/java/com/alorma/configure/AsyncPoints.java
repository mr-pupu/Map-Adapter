package com.alorma.configure;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.alorma.data.bbdd.contract.LocationContract;
import com.alorma.data.bbdd.cursor.LocationCursor;
import com.alorma.data.bean.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by alorma on 11/06/13.
 */
public class AsyncPoints extends AsyncTask<Void, Void, Void> {

    private final int number;
    private Context context;

    public AsyncPoints(Context context, int number) {
        this.context = context;
        this.number = number;
    }


    @Override
    protected Void doInBackground(Void... voids) {

        LocationCursor locationCursor = new LocationCursor();

        List<Location> locations = new ArrayList<Location>();

        for (int i = 0; i < number; i++) {
            Location loc = new Location();
            loc.setName("Point: " + i);

            double lat = Math.random() * 90d * randomizeSymbol();
            double lng = Math.random() * 180d * randomizeSymbol();

            loc.setLat(lat);
            loc.setLng(lng);

            locations.add(loc);
        }

        ContentValues[] values = locationCursor.write(locations);

        ContentResolver cr = context.getContentResolver();

        cr.bulkInsert(LocationContract.URI, values);

        Log.i("TAG", "Fet");

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
