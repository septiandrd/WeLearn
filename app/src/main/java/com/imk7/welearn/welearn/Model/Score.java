package com.imk7.welearn.welearn.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by septiandrd on 14/11/17.
 */

public class Score {

    @SerializedName("modul1")
    @Expose
    private int modul1;

    @SerializedName("modul2")
    @Expose
    private int modul2;

    @SerializedName("modul3")
    @Expose
    private int modul3;

    @SerializedName("modul4")
    @Expose
    private int modul4;

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setModul1(int modul1) {
        this.modul1 = modul1;
    }

    public void setModul2(int modul2) {
        this.modul2 = modul2;
    }

    public void setModul3(int modul3) {
        this.modul3 = modul3;
    }

    public void setModul4(int modul4) {
        this.modul4 = modul4;
    }

    public int getModul1() {
        return modul1;
    }

    public int getModul2() {
        return modul2;
    }

    public int getModul3() {
        return modul3;
    }

    public int getModul4() {
        return modul4;
    }

}
