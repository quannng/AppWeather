package com.android.duongdb.weather.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/*
 * Singleton preferences helper class, it just delegates some of
 * the default SharedPreferences methods for convenient use.
 */
public class PreferenceHelper {

    private static PreferenceHelper sHelper;

    private static SharedPreferences mPreference;

    private PreferenceHelper(Context context) {
        mPreference = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static PreferenceHelper getInstance(Context context) {
        if (sHelper == null) {
            sHelper = new PreferenceHelper(context);
        }

        return sHelper;
    }

    /*
     * NOTE: apply() is safe enough and faster over commit()
     */

    public void putInt(String key, int value) {
        mPreference.edit().putInt(key, value).apply();
    }

    public int getInt(String key, int defaultValue) {
        return mPreference.getInt(key, defaultValue);
    }

    public static void putString(String key, String value) {
        mPreference.edit().putString(key, value).apply();
    }

    public static String getString(String key, String defaultValue) {
        return mPreference.getString(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        mPreference.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mPreference.getBoolean(key, defaultValue);
    }
}