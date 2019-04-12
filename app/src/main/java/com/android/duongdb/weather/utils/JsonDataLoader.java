package com.android.duongdb.weather.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.duongdb.weather.ApiService;
import com.android.duongdb.weather.helpers.PreferenceHelper;
import com.android.duongdb.weather.helpers.DataHelper;
import com.android.duongdb.weather.models.Weather;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonDataLoader extends AsyncTask<String, Void, String> {

    private DataLoadListener dataLoadListener;
    private Boolean isLocationSet;
    private PreferenceHelper preferences;
    private Context context;

    public JsonDataLoader(DataLoadListener dataLoadListener, Boolean isLocationSet, Context context) {
        this.isLocationSet = isLocationSet;
        this.context = context;
        this.dataLoadListener = dataLoadListener;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... params) {
        String address = "https://weather-ydn-yql.media.yahoo.com/forecastrss?&format=json";
        HttpURLConnection urlConnection = null;
        String result = "";
//        Log.d("====>>>>", params[0]);
//        String lat = params[0].replace("(", "").replace(")", "").split(",")[0].substring(0, 5);
//        String lon = params[0].replace("(", "").replace(")", "").split(",")[1].substring(0, 5);
//        Log.d("====>>>>", lat);
//        Log.d("====>>>>", lon);
//        address += "&lat=" + lat + "&lon=" + lon;
//        Log.d("====>>>>", address);

        try {
            URL url = new URL(address);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("X-Yahoo-App-Id", "ZV4O165a");
            urlConnection.setRequestMethod("GET");
            String author = "OAuth oauth_consumer_key=\"dj0yJmk9amhSZHNLRTBCc3JMJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWYy\", oauth_nonce=\"KyCljHFt_\", oauth_timestamp=\"1554923796\", oauth_signature_method=\"HMAC-SHA1\", oauth_signature=\"+PoaN+bstErNJhJzycUySFR5d4M=\", oauth_version=\"1.0\"";
            Log.d("doInBackground", author);
            urlConnection.setRequestProperty("Authorization", author);

            int code = urlConnection.getResponseCode();
            Log.d("=============>>>>>", code + "");
            if (code == 200) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                if (in != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null)
                        result += line;
                }
                in.close();
            }
            result = "{\n" +
                    "   \"location\":{\n" +
                    "      \"woeid\": 2502265,\n" +
                    "      \"city\":\"HaNoi\",\n" +
                    "      \"region\":\" CA\",\n" +
                    "      \"country\":\"VietNam\",\n" +
                    "      \"lat\":37.371609,\n" +
                    "      \"long\":-122.038254,\n" +
                    "      \"timezone_id\":\"America/Los_Angeles\"\n" +
                    "   },\n" +
                    "   \"current_observation\":{\n" +
                    "      \"wind\":{\n" +
                    "         \"chill\":59,\n" +
                    "         \"direction\":165,\n" +
                    "         \"speed\":8.7\n" +
                    "      },\n" +
                    "      \"atmosphere\":{\n" +
                    "         \"humidity\":76,\n" +
                    "         \"visibility\":10,\n" +
                    "         \"pressure\":29.68\n" +
                    "      },\n" +
                    "      \"astronomy\":{\n" +
                    "         \"sunrise\":\"7:23 am\",\n" +
                    "         \"sunset\":\"5:7 pm\"\n" +
                    "      },\n" +
                    "      \"condition\":{\n" +
                    "         \"text\":\"Rain\",\n" +
                    "         \"code\":12,\n" +
                    "         \"temperature\":80\n" +
                    "      },\n" +
                    "      \"pubDate\":1554962865\n" +
                    "   },\n" +
                    "   \"forecasts\":[\n" +
                    "      {\n" +
                    "         \"day\":\"Tue\",\n" +
                    "         \"date\":1546934400,\n" +
                    "         \"low\":72,\n" +
                    "         \"high\":81,\n" +
                    "         \"text\":\"Rain\",\n" +
                    "         \"code\":12\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"day\":\"Wed\",\n" +
                    "         \"date\":1547020800,\n" +
                    "         \"low\":51,\n" +
                    "         \"high\":62,\n" +
                    "         \"text\":\"Scattered Showers\",\n" +
                    "         \"code\":39\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"day\":\"Thu\",\n" +
                    "         \"date\":1547107200,\n" +
                    "         \"low\":46,\n" +
                    "         \"high\":60,\n" +
                    "         \"text\":\"Mostly Cloudy\",\n" +
                    "         \"code\":28\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"day\":\"Fri\",\n" +
                    "         \"date\":1547193600,\n" +
                    "         \"low\":48,\n" +
                    "         \"high\":61,\n" +
                    "         \"text\":\"Showers\",\n" +
                    "         \"code\":11\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"day\":\"Sat\",\n" +
                    "         \"date\":1547280000,\n" +
                    "         \"low\":47,\n" +
                    "         \"high\":62,\n" +
                    "         \"text\":\"Rain\",\n" +
                    "         \"code\":12\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"day\":\"Sun\",\n" +
                    "         \"date\":1547366400,\n" +
                    "         \"low\":48,\n" +
                    "         \"high\":58,\n" +
                    "         \"text\":\"Rain\",\n" +
                    "         \"code\":12\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"day\":\"Mon\",\n" +
                    "         \"date\":1547452800,\n" +
                    "         \"low\":47,\n" +
                    "         \"high\":58,\n" +
                    "         \"text\":\"Rain\",\n" +
                    "         \"code\":12\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"day\":\"Tue\",\n" +
                    "         \"date\":1547539200,\n" +
                    "         \"low\":46,\n" +
                    "         \"high\":59,\n" +
                    "         \"text\":\"Scattered Showers\",\n" +
                    "         \"code\":39\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"day\":\"Wed\",\n" +
                    "         \"date\":1547625600,\n" +
                    "         \"low\":49,\n" +
                    "         \"high\":56,\n" +
                    "         \"text\":\"Rain\",\n" +
                    "         \"code\":12\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"day\":\"Thu\",\n" +
                    "         \"date\":1547712000,\n" +
                    "         \"low\":49,\n" +
                    "         \"high\":59,\n" +
                    "         \"text\":\"Scattered Showers\",\n" +
                    "         \"code\":39\n" +
                    "      }\n" +
                    "   ]\n" +
                    "}";
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return result;

    }

    @Override
    protected void onPostExecute(String result) {
        try {
            Weather weather = DataHelper.INSTANCE.renderWeather(result);
            Log.d("onPostExecute", weather + "");
            dataLoadListener.onFinish(DataHelper.INSTANCE.renderWeather(result), isLocationSet);
            preferences = PreferenceHelper.getInstance(context);
            preferences.putString("DATA", result);
        } catch (JSONException e) {
            dataLoadListener.onFinish(null, isLocationSet);
            e.printStackTrace();
        }
        super.onPostExecute(result);
    }


    public DataLoadListener getDataLoadListener() {
        return dataLoadListener;
    }

    public void setDataLoadListener(DataLoadListener dataLoadListener) {
        this.dataLoadListener = dataLoadListener;
    }
}