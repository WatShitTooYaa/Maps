package com.example.pagetest;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.pagetest.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button go = (Button) findViewById(R.id.cariTitik);
        go.setOnClickListener(op);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ITS = new LatLng(-7.2819705,112.795323);
        mMap.addMarker(new MarkerOptions().position(ITS).title("Marker in ITS"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(ITS));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ITS,15));
    }

    private void gotoPeta(Double lat, Double lng, Float z) {
        LatLng LokasiBaru = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(LokasiBaru).title("Marker in  " +lat +":" +lng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LokasiBaru,z));
    }

    View.OnClickListener op = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.cariTitik){
                sembunyikanKeyBoard(v);
                gotoLokasi();
                Toast.makeText(MapsActivity.this,"Maps activity", Toast.LENGTH_LONG).show();
            }
        }
    };

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

    private void sembunyikanKeyBoard(View v){
        InputMethodManager a = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        a.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}

