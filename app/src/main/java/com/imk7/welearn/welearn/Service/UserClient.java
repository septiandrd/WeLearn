package com.imk7.welearn.welearn.Service;

import android.support.v4.media.MediaMetadataCompat;

import com.imk7.welearn.welearn.Model.GetUserResponse;
import com.imk7.welearn.welearn.Model.LoginResponse;
import com.imk7.welearn.welearn.Model.SignUpResponse;
import com.imk7.welearn.welearn.Model.User;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by septiandrd on 11/11/17.
 */

public interface UserClient {

    @POST("login")
    Call<LoginResponse> login(@Body User user);

    @POST("signup")
    Call<SignUpResponse> signup(@Body User user);

    @GET("getUser")
    Call<GetUserResponse> getUser(@Query("token") String token);

//    @GET("logout")
//    Call<GetUserResponse> logout();
//
//    @GET("test")
//    Call<ResponseBody> test();

}
