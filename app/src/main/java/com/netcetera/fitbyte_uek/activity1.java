package com.netcetera.fitbyte_uek;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class activity1 extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location currentLocation;
    private TextView distanceView;
    private TextView timeView;
    private Timer timer;
    private long startTime;
    private double totalDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        distanceView = (TextView) findViewById(R.id.distance_view);
        timeView = (TextView) findViewById(R.id.time_view);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (currentLocation == null) {
                    currentLocation = location;
                } else {
                    totalDistance += location.distanceTo(currentLocation);
                    currentLocation = location;
                }
                distanceView.setText(String.format("%.2f km", totalDistance / 1000));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        startTime = System.currentTimeMillis();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        long elapsedTime = System.currentTimeMillis() - startTime;
                        timeView.setText(String.format("%d min, %d sec", elapsedTime / 60000, (elapsedTime % 60000) / 1000));
                    }
                });
            }
        }, 0, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
        timer.cancel();
    }
}
