package com.imk7.welearn.welearn.Service;

import com.imk7.welearn.welearn.Model.SignUpResponse;
import com.imk7.welearn.welearn.Model.User;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by septiandrd on 11/11/17.
 */

public interface UserClient {

    @POST("login")
    Call<ResponseBody> login(@Body User user);

    @POST("signup")
    Call<SignUpResponse> signup(@Body User user);

    @GET("user")
    Call<User> getUser(@Header("Authorization") String authToken);

}
