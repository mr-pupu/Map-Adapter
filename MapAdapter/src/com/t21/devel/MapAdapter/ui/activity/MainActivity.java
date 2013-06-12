package com.t21.devel.MapAdapter.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.t21.devel.MapAdapter.R;

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
