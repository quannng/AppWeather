package com.android.duongdb.weather.databases;

import android.content.Context;
import android.os.AsyncTask;

import com.android.duongdb.weather.models.HistoryModel;
import com.android.duongdb.weather.utils.DatabaseUpdateListener;

public class UpdateCityThread extends AsyncTask<HistoryModel, Integer, Boolean>{

    private DatabaseHelper databaseHelper;
    private DatabaseUpdateListener databaseUpdateListener;

    public UpdateCityThread(Context context){
        databaseHelper = new DatabaseHelper(context);
    }
    @Override
    protected Boolean doInBackground(HistoryModel ... historyModeles) {
        return databaseHelper.updateOrInsert(historyModeles[0]);
    }

    @Override
    protected void onPostExecute(Boolean result){
        if(result != null && databaseUpdateListener != null) {
            databaseUpdateListener.onFinish(result);
        }
    }

    public DatabaseUpdateListener getDatabaseUpdateListener() {
        return databaseUpdateListener;
    }

    public void setDatabaseUpdateListener(DatabaseUpdateListener databaseUpdateListener) {
        this.databaseUpdateListener = databaseUpdateListener;
    }
}