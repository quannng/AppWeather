package com.android.duongdb.weather.dialogs;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.duongdb.weather.R;
import com.android.duongdb.weather.helpers.DialogHelper;
import com.android.duongdb.weather.models.Weather;
import com.android.duongdb.weather.utils.DataLoadListener;
import com.android.duongdb.weather.utils.JsonDataLoader;

public class SearchDialog extends Dialog implements View.OnClickListener, LocationListener, DataLoadListener {
    private Button okButton, cancelButton;
    private EditText inputEditText;
    private ImageView progressBar;
    private ImageButton locationButton;
    private LocationManager locationManager;
    private JsonDataLoader dataLoader;
    private DataLoadListener dataLoadListener;
    private Weather weather;
    private String city;
    private Animation rotateAnimation;


    public SearchDialog(Context context, DataLoadListener dataLoadListener) {
        super(context);
        this.dataLoadListener = dataLoadListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        setContentView(R.layout.dialog_location);
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        rotateAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        progressBar = (ImageView) findViewById(R.id.progressbar);
        showProgressBar(false);

        locationButton = (ImageButton) findViewById(R.id.location_button);
        locationButton.setOnClickListener(this);

        okButton = (Button) findViewById(R.id.ok_button);
        okButton.setOnClickListener(this);

        cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(this);

        inputEditText = (EditText) findViewById(R.id.input_editext);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok_button:
                if (dataLoader != null) dataLoader.cancel(true);
                if (locationManager != null) locationManager.removeUpdates(SearchDialog.this);
                if (city != null && weather != null && city.equals(inputEditText.getText().toString())) {
                    dataLoadListener.onFinish(this.weather, true);
                    dismiss();
                } else if (inputEditText.getText().toString().length() != 0)
                    queryWeather(inputEditText.getText().toString(), false);
                break;
            case R.id.cancel_button:
                showProgressBar(false);
                if (dataLoader != null) dataLoader.cancel(true);
                if (locationManager != null) locationManager.removeUpdates(SearchDialog.this);
                dismiss();
                break;
            case R.id.location_button:
                setLocation();
                break;
            default:
                break;
        }
    }

    public void setLocation() {
        if (!DialogHelper.isNetworkConnected(getContext()))
            DialogHelper.showNetworkDialog(getContext());
        else if (DialogHelper.getGPSStatus(getContext()) != null) {
            showProgressBar(true);
            //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60*1000, 10, this);
            if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(DialogHelper.getGPSStatus(getContext()));
            if(location != null) onLocationChanged(location);
            else locationManager.requestLocationUpdates(DialogHelper.getGPSStatus(getContext()), 60*1000, 10, this);
        }
       /*
        else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                showProgressBar(true);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60*1000, 10, this);
        }else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                showProgressBar(true);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60*1000, 10, this);
        }*/
        else DialogHelper.showLocationDialog(getContext());
    }

    public void queryWeather(String address, boolean isLocationSet){
        showProgressBar(true);
        dataLoader = new JsonDataLoader(this, isLocationSet, getContext());
        dataLoader.execute(address);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null){
            queryWeather("(" + location.getLatitude()+ "," + location.getLongitude() + ")", true);
        }
        else Toast.makeText(getContext(), "Cant get data. Please check and try again", Toast.LENGTH_SHORT).show();
        locationManager.removeUpdates(SearchDialog.this);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void setLocationInput(String location){
        inputEditText.setText(location);
    }

    public void showProgressBar(boolean isShow){
        if(isShow){
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(rotateAnimation);
        }
        else {
            progressBar.setVisibility(View.GONE);
            progressBar.clearAnimation();
        }

    }

    @Override
    public void onFinish(Weather weather, boolean isLocationSet) {
        showProgressBar(false);
        if(weather == null){
            Toast.makeText(getContext(), "Cant get data. Please check and try again", Toast.LENGTH_SHORT).show();
        }else if(isLocationSet){
            this.weather = weather;
            this.city = weather.getCityName() + ", "+ weather.getCountryName();
            setLocationInput(city);
        } else {
            dataLoadListener.onFinish(weather , isLocationSet);
            dismiss();
        }
    }
}
