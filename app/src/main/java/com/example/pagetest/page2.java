package com.example.pagetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class page2 extends AppCompatActivity {
    private LocationManager lm;
    private LocationListener ll;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        LocationManager mylocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lokasiListener mylocationListener = new lokasiListener();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }
        mylocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, mylocationListener);
    }
    private class lokasiListener implements LocationListener{
    private TextView txtLat, txtLong;
        @Override
        public void onLocationChanged(@NonNull Location location) {
            txtLat = (TextView) findViewById(R.id.latitude);
            txtLong = (TextView) findViewById(R.id.longitude);

            txtLat.setText(String.valueOf(location.getLatitude()));
            txtLong.setText(String.valueOf(location.getLongitude()));
//            txtLong = setText(String.valueOf(location.getLongitude()));
            Toast.makeText(getBaseContext(), "GPS captured", Toast.LENGTH_LONG).show();
        }
    }
}