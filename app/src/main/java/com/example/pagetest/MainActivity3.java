package com.example.pagetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.pagetest.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity3 extends AppCompatActivity {
    private GoogleMap mMap;
//    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        FrameLayout mapContainer = findViewById(R.id.mapContainer);
        SupportMapFragment mapFragment = new SupportMapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.mapContainer,mapFragment).commit();
        Button go = (Button) findViewById(R.id.cariTitik);
        go.setOnClickListener(op);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // Mengakses objek GoogleMap
                mMap = googleMap;

                // Koordinat baru untuk lokasi awal peta
                LatLng newLocation = new LatLng(-7.2819705,112.795323);

                // Menambahkan marker ke lokasi baru
                mMap.addMarker(new MarkerOptions().position(newLocation).title("ITS"));

                // Menggerakkan kamera ke lokasi baru
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 15f));
            }
        });
    }

    public void back(View view) {
        finish();
    }



    private void gotoPeta(Double lat, Double lng, Float z) {
        if (mMap != null) {
            LatLng LokasiBaru = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(LokasiBaru).title("Marker in " + lat + ":" + lng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LokasiBaru, z));
        }
    }
//
    View.OnClickListener op = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.cariTitik){
                sembunyikanKeyBoard(v);
                gotoLokasi();

                Toast.makeText(MainActivity3.this, "in main 3", Toast.LENGTH_LONG).show();
            }
        }
    };
//
    private void gotoLokasi(){
        EditText lat = (EditText) findViewById(R.id.getLat);
        EditText lng = (EditText) findViewById(R.id.getLong);
        EditText zoom = (EditText) findViewById(R.id.getZoom);

        Double dbllat = Double.parseDouble(lat.getText().toString());
        Double dbllng = Double.parseDouble(lng.getText().toString());
        Float dblzoom = Float.parseFloat(zoom.getText().toString());

        Toast.makeText(this, "Move to Lat: "+ dbllat + " Long: " +dbllng, Toast.LENGTH_LONG).show();
        gotoPeta(dbllat, dbllng, dblzoom);
    }
//
    private void sembunyikanKeyBoard(View v){
        InputMethodManager a = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        a.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}