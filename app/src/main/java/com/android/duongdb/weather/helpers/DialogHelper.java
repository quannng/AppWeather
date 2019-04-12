package com.android.duongdb.weather.helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;


public class DialogHelper {

    public static void showLocationDialog(final Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Enable Location")
                .setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        dialog.show();
    }

    public static void showNetworkDialog(Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Missing network")
                .setMessage("Your internet connection not avaiable. Please check your connection and retry")
                .setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        dialog.show();
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    public static String getGPSStatus(Context context)
    {
        String allowedLocationProviders = Settings.System.getString(context.getContentResolver(), Settings.System.LOCATION_PROVIDERS_ALLOWED);
        if (allowedLocationProviders == null) {
            allowedLocationProviders = "";
        }
        if(allowedLocationProviders.contains(LocationManager.GPS_PROVIDER)) return LocationManager.GPS_PROVIDER;
        if(allowedLocationProviders.contains(LocationManager.NETWORK_PROVIDER)) return LocationManager.NETWORK_PROVIDER;
        else return null;
    }
}
