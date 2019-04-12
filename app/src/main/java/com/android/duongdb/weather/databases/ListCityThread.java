package com.android.duongdb.weather.databases;

import android.content.Context;
import android.os.AsyncTask;

import com.android.duongdb.weather.models.HistoryModel;
import com.android.duongdb.weather.utils.DatabaseLoadListener;

import java.util.ArrayList;

public class ListCityThread extends AsyncTask<String, Integer, ArrayList<HistoryModel>>{

    private DatabaseHelper databaseHelper;
    private DatabaseLoadListener databaseLoadListener;

    public ListCityThread(Context context){
        databaseHelper = new DatabaseHelper(context);
    }
    @Override
    protected ArrayList<HistoryModel> doInBackground(String ... params) {
        return databaseHelper.getHistoryModels();
    }

    @Override
    protected void onPostExecute(ArrayList<HistoryModel> result){
        if(result != null && databaseLoadListener != null) {
            databaseLoadListener.onFinish(result);
        }
    }

    public DatabaseLoadListener getDatabaseLoadListener() {
        return databaseLoadListener;
    }

    public void setDatabaseLoadListener(DatabaseLoadListener databaseLoadListener) {
        this.databaseLoadListener = databaseLoadListener;
    }
}