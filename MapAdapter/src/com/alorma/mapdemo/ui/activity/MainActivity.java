package com.alorma.mapdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import com.alorma.mapdemo.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (status != ConnectionResult.SUCCESS) {
            GooglePlayServicesUtil.getErrorDialog(status, this, 1234).show();
        } else {
            setContentView(R.layout.main);
        }
    }

}
