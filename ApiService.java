package com.example.cristhian.practiceretrofit.Api;

import com.example.cristhian.practiceretrofit.Model.ContactResponse;
import com.example.cristhian.practiceretrofit.Model.NearByPlaceList;
import com.example.cristhian.practiceretrofit.Model.NearByPlacesRequest;
import com.example.cristhian.practiceretrofit.Model.PublishingDetailResponse;
import com.example.cristhian.practiceretrofit.Model.WrapperPost;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by cristhian on 11/12/17.
 */

public interface ApiService {

    @POST("Post/NearBy")
    Call<NearByPlaceList> getNearByPlaces(@Header("Content-Type") String contentType,
                                          @Header("api-key") String apiKey,
                                          @Body NearByPlacesRequest nearByPlacesRequest);

    @GET("Contact")
    Call<ContactResponse> getContactInformation(@Header("api-key") String apiKey);

    @GET("Post/{postId}")
    Call<PublishingDetailResponse> getPublishingDetail(@Header("api-key") String apiKey, @Path("postId") int postId);

    @GET("Post")
    Call<WrapperPost> getAllPublishing(@Header("api-key") String apiKey,
                                       @Query("Type") int type
                                            );

}
