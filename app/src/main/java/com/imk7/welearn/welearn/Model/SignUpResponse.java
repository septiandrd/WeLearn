package com.imk7.welearn.welearn.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by septiandrd on 12/11/17.
 */

public class SignUpResponse {

    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }
}
