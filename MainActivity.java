package com.example.cristhian.practiceretrofit;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cristhian.practiceretrofit.Api.ApiServiceImp;
import com.example.cristhian.practiceretrofit.Api.ApiUtils;
import com.example.cristhian.practiceretrofit.Model.ContactResponse;
import com.example.cristhian.practiceretrofit.Model.NearByPlaceList;
import com.example.cristhian.practiceretrofit.Model.NearByPlacesRequest;
import com.example.cristhian.practiceretrofit.Model.PublishingDetailResponse;
import com.example.cristhian.practiceretrofit.Model.WrapperPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText editTextLat;
    EditText editTextLong;
    Button buttonCoordinates;
    Button buttonMap;
    LocationManager locationManager;
    Location location;
    NearByPlaceList nearByPlaceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showToolBar(getString(R.string.app_name));

        editTextLat = (EditText) findViewById(R.id.EdiTextLat);
        editTextLong = (EditText) findViewById(R.id.EdiTextLong);
        buttonCoordinates = (Button) findViewById(R.id.ButtonCoordinates);
        buttonMap = (Button) findViewById(R.id.ButtonShowMap);

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                if (!nearByPlaceList.getNearByPlacesResponses().isEmpty()) {
                    intent.putExtra("Coordinates", nearByPlaceList.getNearByPlacesResponses());
                }
                startActivity(intent);
            }
        });

        locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,10,locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, locationListener);

        Call<WrapperPost> wrapperPostCall = ApiServiceImp.getApiService().getAllPublishing(
                ApiUtils.API_KEY,
                2 //default value
        );

        wrapperPostCall.enqueue(new Callback<WrapperPost>() {
            @Override
            public void onResponse(Call<WrapperPost> call, Response<WrapperPost> response) {
                WrapperPost wrapperPost = response.body();
                if (wrapperPost == null) {
                    Log.d("InResponseWrapperIsNull", response.raw().toString());
                } else {
                    Log.d("OnResponseWrapperRecords", wrapperPost.getWrapperRecords().getPublishingResponses().get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<WrapperPost> call, Throwable t) {
                Log.d("ErrorAllPublishingResponse", "" + t.getMessage());
            }
        });

        Call<PublishingDetailResponse> publishingDetailResponseCall = ApiServiceImp.getApiService().getPublishingDetail(
                ApiUtils.API_KEY,
                26 //default value
        );

        publishingDetailResponseCall.enqueue(new Callback<PublishingDetailResponse>() {
            @Override
            public void onResponse(Call<PublishingDetailResponse> call, Response<PublishingDetailResponse> response) {
                    PublishingDetailResponse publishingDetailResponse = response.body();
                    Log.d("SuccessCPublishingResponse", "price: "+ publishingDetailResponse.getPrice());
            }

            @Override
            public void onFailure(Call<PublishingDetailResponse> call, Throwable t) {
                Log.d("ErrorPublishingResponse", "" + t.getMessage());
            }
        });

        Call<ContactResponse> contactResponseCall = ApiServiceImp.getApiService().getContactInformation(
                ApiUtils.API_KEY
        );

        contactResponseCall.enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                ContactResponse contactResponse = response.body();
                Log.d("SuccessContactResponse", "Play Store Link: "+ contactResponse.getContactFormLink());
            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {
                Log.d("ErrorContactResponse", "" + t.getMessage());
            }
        });
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Cargando");
        alertDialog.setMessage("Espere por favor mientras se cargan los datos....");

        buttonCoordinates.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    }

                } else {

                    if ( MainActivity.this.location != null) {
                        alertDialog.show();
                        editTextLat.setText(String.valueOf(MainActivity.this.location.getLatitude()));
                        editTextLong.setText(String.valueOf(MainActivity.this.location.getLongitude()));


                        Call<NearByPlaceList> nearByPlaceListCall = ApiServiceImp.getApiService().getNearByPlaces(
                                ApiUtils.CONTENT_TYPE,
                                ApiUtils.API_KEY,
                                new NearByPlacesRequest(String.valueOf(MainActivity.this.location.getLatitude()),
                                                         String.valueOf(MainActivity.this.location.getLongitude())
                                                        )
                        );

                        nearByPlaceListCall.enqueue(new Callback<NearByPlaceList>() {
                            @Override
                            public void onResponse(Call<NearByPlaceList> call, Response<NearByPlaceList> response) {
                                NearByPlaceList nearByPlaceList = response.body();
                                MainActivity.this.nearByPlaceList = nearByPlaceList;
                                Toast.makeText(getApplicationContext(),"Se ha cargado los datos correctamente!", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                                Log.d("onReponse", nearByPlaceList.getNearByPlacesResponses().get(0).getTitle());
                            }

                            @Override
                            public void onFailure(Call<NearByPlaceList> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
                                Log.d("onFailure", t.getMessage());
                            }
                        });

                    } else {
                        Log.d("Latitude","Entro en el else");
                    }
                }
            }

        });

    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            MainActivity.this.location = location;
            Log.d("ChangeLocation","Latitude: "+ MainActivity.this.location.getLatitude() + "\nLongitude: "
                    + MainActivity.this.location.getLongitude());
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            Log.d("onStatusChanged","Latitude change");
        }

        @Override
        public void onProviderEnabled(String s) {
            Log.d("onProviderEnabled","Latitude change");
        }

        @Override
        public void onProviderDisabled(String s) {
            Log.d("onProviderDisabled","Latitude change");
        }
    };

    public void showToolBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().show();
    }


}
