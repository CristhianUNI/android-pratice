package com.example.cristhian.practiceretrofit.Api;

/**
 * Created by cristhian on 11/12/17.
 */

public class ApiServiceImp {

    public static ApiService getApiService(){
        return ApiClient.getClient(ApiUtils.BASE_URL).create(ApiService.class);
    }

}
