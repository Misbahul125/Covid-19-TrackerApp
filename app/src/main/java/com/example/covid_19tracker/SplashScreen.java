package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    String[] PERMISSIONS = {Manifest.permission.INTERNET , Manifest.permission.ACCESS_NETWORK_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        moveAhead();
                    }
                }
            }
            else {
                try {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        moveAhead();
                    }
                    else {
                        Toast.makeText(this, "Please check your Internet connection", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Something went wrong! Please check your Internet connection", Toast.LENGTH_LONG).show();
                    //Log.i("update_statut", "" + e.getMessage());
                }
            }
        }
        else {
            Toast.makeText(this, "Please check your Internet connection", Toast.LENGTH_LONG).show();
            requestInternetPermission();
            Log.i("update_statut","Network is available : FALSE ");
        }
    }

    private void moveAhead() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this , MainActivity.class));
                finish();
            }
        },2000);
    }

    private void requestInternetPermission() {
        ActivityCompat.requestPermissions(SplashScreen.this, PERMISSIONS, 100);
    }
}