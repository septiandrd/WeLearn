package com.imk7.welearn.welearn.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by septiandrd on 14/11/17.
 */

public class ScoreResponse {

    @SerializedName("score")
    @Expose
    private Score score;

    public Score getScore() {
        return score;
    }
}
