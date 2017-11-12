package com.imk7.welearn.welearn.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by septiandrd on 12/11/17.
 */

public class SaveSharedPreference {
    static final String USERNAME = "";

    static SharedPreferences getSharedPreferences(Context c) {
        return PreferenceManager.getDefaultSharedPreferences(c);
    }

    public static void setUSERNAME(Context c, String Username) {
        SharedPreferences.Editor editor = getSharedPreferences(c).edit();
        editor.putString(USERNAME,Username);
        editor.commit();
    }

    public static String getUSERNAME(Context c) {
        return getSharedPreferences(c).getString(USERNAME,"");
    }
}
