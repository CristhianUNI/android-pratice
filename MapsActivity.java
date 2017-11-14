package com.example.cristhian.practiceretrofit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.cristhian.practiceretrofit.Model.NearByPlacesResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<NearByPlacesResponse> nearByPlaceList;
    private boolean isFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //get list coordinates
        Intent intent = getIntent();

        if (intent != null) {
            nearByPlaceList = (ArrayList<NearByPlacesResponse>) intent.getSerializableExtra("Coordinates");
        }

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        //Lat y Long for default
//        LatLng syndey = new LatLng(-63.04092, -60.9589994);
//        mMap.addMarker(new MarkerOptions().position(syndey).title("Hola!").icon(BitmapDescriptorFactory.defaultMarker()));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(syndey,10));

        //get Current Location
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }

        } else {
            mMap.setMyLocationEnabled(true);

            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
                    LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Mi Ubicacion").icon(BitmapDescriptorFactory.defaultMarker()));
                    if (isFirstTime) {
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                        isFirstTime = false;
                    }

                }
            });

            for (NearByPlacesResponse nearByPlacesResponse: nearByPlaceList) {
                try {
                    LatLng latLng = new LatLng( Double.parseDouble(nearByPlacesResponse.getLatitude()) ,
                             Double.parseDouble(nearByPlacesResponse.getLongitude()) );
                    mMap.addMarker(new MarkerOptions().position(latLng).title(nearByPlacesResponse.getTitle())
                            .icon(BitmapDescriptorFactory.defaultMarker()));
                }catch (Exception ex ) {
                    Log.d("ParseException", "Error en parseo");
                }

            }
        }

    }
}
