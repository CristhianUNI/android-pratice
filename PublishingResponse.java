package com.example.cristhian.practiceretrofit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cristhian on 11/12/17.
 */

public class PublishingResponse {


    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("price")
    @Expose
    private Double price;

    @SerializedName("lotSize")
    @Expose
    private Double lotSize;

    public PublishingResponse(int id, String title, Double price, Double lotSize) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.lotSize = lotSize;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getLotSize() {
        return lotSize;
    }

    public void setLotSize(Double lotSize) {
        this.lotSize = lotSize;
    }
}
