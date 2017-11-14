package com.example.cristhian.practiceretrofit.Model;

/**
 * Created by cristhian on 11/12/17.
 */

public class NearByPlacesRequest {

    private String latitude;
    private String longitude;

    public NearByPlacesRequest(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
