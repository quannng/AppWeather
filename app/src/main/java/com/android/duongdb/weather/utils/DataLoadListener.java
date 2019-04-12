package com.android.duongdb.weather.utils;

import com.android.duongdb.weather.models.Weather;


public interface DataLoadListener {
    void onFinish(Weather weather, boolean isLocationSet);
}
