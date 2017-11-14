package com.imk7.welearn.welearn.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by septiandrd on 12/11/17.
 */

public class SaveSharedPreference {
    static final String TOKEN = "";

    static SharedPreferences getSharedPreferences(Context c) {
        return PreferenceManager.getDefaultSharedPreferences(c);
    }

    public static void setToken(Context c, String Username) {
        SharedPreferences.Editor editor = getSharedPreferences(c).edit();
        editor.putString(TOKEN,Username);
        editor.commit();
    }

    public static String getToken(Context c) {
        return getSharedPreferences(c).getString(TOKEN,"");
    }
}
