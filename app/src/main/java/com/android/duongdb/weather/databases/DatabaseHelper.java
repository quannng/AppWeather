package com.android.duongdb.weather.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.android.duongdb.weather.models.HistoryModel;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "weather";
    private static final String WEATHER_TABLE_NAME = "history";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_DATE= "date";
    private static final String COLUMN_CENCI = "cencius";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CODE = "code";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS " + WEATHER_TABLE_NAME + " (" +
                COLUMN_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CITY + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_CENCI+ " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_CODE + " INTEGER) " ;
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + WEATHER_TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HistoryModel> getHistoryModels(){
        Log.d("bbbbbbbbbbb", "aaaaaaaaaaaaaaaaaaaaaaaaa");
        ArrayList<HistoryModel> historyModels = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + WEATHER_TABLE_NAME, null);
        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                HistoryModel historyModel = new HistoryModel();
                Log.d("bbbbbbbbbbb", "cccccccccccccccccccccccc");
                historyModel.setCity(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)));
                historyModel.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                historyModel.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                historyModel.setCencius(cursor.getString(cursor.getColumnIndex(COLUMN_CENCI)));
                historyModel.setImageId(cursor.getInt(cursor.getColumnIndex(COLUMN_CODE)));
                cursor.moveToNext();
                historyModels.add(historyModel);
            }
        }
        return historyModels;
    }

    public void deleteHistoryModel(String cityName){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(WEATHER_TABLE_NAME, COLUMN_CITY + " = \"" + cityName+"\"", null);

    }

    public boolean updateOrInsert(HistoryModel historyModel){
        if(historyModel == null) return false;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(WEATHER_TABLE_NAME, COLUMN_CITY + " = \"" + historyModel.getCity() + "\"", null);
        if(historyModel != null) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_CITY, historyModel.getCity());
            values.put(COLUMN_DATE, historyModel.getDate());
            values.put(COLUMN_NAME, historyModel.getName());
            values.put(COLUMN_CENCI, historyModel.getCencius());
            values.put(COLUMN_CODE, historyModel.getImageId());
            sqLiteDatabase.insert(WEATHER_TABLE_NAME, null, values);
        }
        return true;
    }
}
