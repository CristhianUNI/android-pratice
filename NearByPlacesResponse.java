package com.example.cristhian.practiceretrofit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by cristhian on 11/12/17.
 */

public class NearByPlacesResponse implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("postNumber")
    @Expose
    private String postNumber;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("type")
    @Expose
    private String type;

    public NearByPlacesResponse(int id, String postNumber, String title, String latitude, String longitude, String type) {
        this.id = id;
        this.postNumber = postNumber;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
