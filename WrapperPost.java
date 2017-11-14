package com.example.cristhian.practiceretrofit.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cristhian on 11/13/17.
 */

public class WrapperPost {

    @SerializedName("post")
    private WrapperRecords wrapperRecords;

    public WrapperPost(WrapperRecords wrapperRecords) {
        this.wrapperRecords = wrapperRecords;
    }

    public WrapperRecords getWrapperRecords() {
        return wrapperRecords;
    }

    public void setWrapperRecords(WrapperRecords wrapperRecords) {
        this.wrapperRecords = wrapperRecords;
    }
}
