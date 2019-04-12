package com.android.duongdb.weather.databases;

import android.content.Context;
import android.os.AsyncTask;

import com.android.duongdb.weather.utils.DatabaseDeleteListener;

public class DeleteCityThread extends AsyncTask<String, Integer, Integer>{

    private DatabaseHelper databaseHelper;
    private DatabaseDeleteListener databaseDeleteListener;

    public DeleteCityThread(Context context){
        databaseHelper = new DatabaseHelper(context);
    }
    @Override
    protected Integer doInBackground(String ... citys) {
        databaseHelper.deleteHistoryModel(citys[0]);
        return Integer.parseInt(citys[1]);
    }

    @Override
    protected void onPostExecute(Integer result){
        if(databaseDeleteListener != null)
        databaseDeleteListener.onFinishDelete(result);
    }

    public DatabaseDeleteListener getDatabaseDeleteListener() {
        return databaseDeleteListener;
    }

    public void setDatabaseDeleteListener(DatabaseDeleteListener databaseDeleteListener) {
        this.databaseDeleteListener = databaseDeleteListener;
    }
}