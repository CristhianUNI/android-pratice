package com.example.cristhian.practiceretrofit.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by cristhian on 11/12/17.
 */

public class NearByPlaceList {

    @SerializedName("data")
    private ArrayList<NearByPlacesResponse> nearByPlacesResponses;

    public ArrayList<NearByPlacesResponse> getNearByPlacesResponses() {
        return nearByPlacesResponses;
    }

    public void setNearByPlacesResponses(ArrayList<NearByPlacesResponse> nearByPlacesResponses) {
        this.nearByPlacesResponses = nearByPlacesResponses;
    }

    public NearByPlaceList(ArrayList<NearByPlacesResponse> nearByPlacesResponses) {

        this.nearByPlacesResponses = nearByPlacesResponses;
    }
}
