package com.android.duongdb.weather.utils;

import com.android.duongdb.weather.models.HistoryModel;


public interface ItemRemoveListener {
    void onRemove(int position, HistoryModel historyModel);
}
