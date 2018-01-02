package com.android.sunshine;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mNoNetworkTextView;
    private RelativeLayout mHomeActivityLayout;
    private Button mWifiSettingsButton;

    public static void startHomeActivity(Context context) {
        Intent homeActivityIntent = new Intent(context, HomeActivity.class);
        context.startActivity(homeActivityIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViews();
        checkNetworkStatus();
        mWifiSettingsButton.setOnClickListener(this);
    }

    private void findViews() {
        mHomeActivityLayout = findViewById(R.id.activityHome_layout);
        mNoNetworkTextView = findViewById(R.id.activityHome_textView_noNetwork);
        mWifiSettingsButton = findViewById(R.id.activityHome_button_wifiSettings);
    }

    private void checkNetworkStatus() {
        Thread newThread = new Thread() {
            @Override
            public void run() {
                try {
                    //check if connected!
                    while (!isConnected(HomeActivity.this)) {
                        //Wait to connect
                        Thread.sleep(1000);
                    }
                    CurrentWeatherActivity.startCuurentWeatherActivity(HomeActivity.this);
                    finish();
                } catch (Exception e) {
                    Log.e("newThread Error ",e.getMessage());
                }
            }
        };
        newThread.start();
    }
    @Override
    public void onClick(View clickedView) {
        if (clickedView.getId() == R.id.activityHome_button_wifiSettings)
            openNetworkSettings();
    }

    private void openNetworkSettings() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        startActivity(intent);
    }
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED;
    }
}
