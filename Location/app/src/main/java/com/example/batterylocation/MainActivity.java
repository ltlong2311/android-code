package com.example.batterylocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import android.os.Bundle;

@SuppressLint("SetTextI18n")

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LOCATION";
    TextView textLat, textLongt;
    LocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textLat = findViewById(R.id.lat);
        textLongt = findViewById(R.id.longt);
        requestLocationUpdates();
    }

    @SuppressLint("MissingPermission")
    private void requestLocationUpdates() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getAllProviders();
        if (providers != null && !providers.isEmpty()) {
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    Log.i(TAG, location.toString());
                    double lat = location.getLatitude();
                    double longt = location.getLongitude();
                    textLat.setText("latitude:" + lat);
                    textLongt.setText("latitude:" + longt);
//                    lm.removeUpdates(locationListener);
//                    locationListener = null;
                }

                @Override
                public void onProviderDisabled(String provider) {
                    Log.i(TAG, provider + " location provider disabled");
                }

                @Override
                public void onProviderEnabled(String provider) {
                    Log.i(TAG, provider + " location provider enabled");
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    Log.i(TAG, provider + " location provider status changed to " + status);
                }
            };

            for (String name : providers) {
                Log.i(TAG, "Requesting location updates on " + name);
                Toast.makeText(this, "Requesting location updates on:" + name, Toast.LENGTH_LONG).show();
//                lm.requestLocationUpdates(name, DateUtils.MINUTE_IN_MILLIS * 2, 10, locationListener);
                lm.requestLocationUpdates(name, DateUtils.SECOND_IN_MILLIS * 20, 0, locationListener);
            }
        }
    }

    private void disableLocationListener(LocationListener listener) {
        LocationManager lm = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        lm.removeUpdates(listener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        disableLocationListener(locationListener);
    }
}