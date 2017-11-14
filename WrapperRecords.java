package com.example.cristhian.practiceretrofit.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cristhian on 11/13/17.
 */

public class WrapperRecords {

    @SerializedName("records")
    private List<PublishingResponse> publishingResponses;

    public WrapperRecords(List<PublishingResponse> publishingResponses) {
        this.publishingResponses = publishingResponses;
    }

    public List<PublishingResponse> getPublishingResponses() {
        return publishingResponses;
    }

    public void setPublishingResponses(List<PublishingResponse> publishingResponses) {
        this.publishingResponses = publishingResponses;
    }
}
