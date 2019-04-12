package com.android.duongdb.weather.utils;

import com.android.duongdb.weather.models.HistoryModel;

import java.util.ArrayList;


public interface DatabaseLoadListener {
    void onFinish(ArrayList<HistoryModel> result);
}
