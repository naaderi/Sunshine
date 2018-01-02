package com.android.sunshine;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sunshine.helpers.NetworkStatus;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private NetworkStatus helperFunctions = new NetworkStatus(this);
    private TextView mNoNetworkTextView;
    private RelativeLayout mHomeActivityLayout;
    private Button mWifiSettingsButton;
    private boolean checkNetworkNow=true;

    public static void startHomeActivity(Context context) {
        Intent homeActivityIntent = new Intent(context, HomeActivity.class);
        context.startActivity(homeActivityIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViews();
        mWifiSettingsButton.setOnClickListener(this);
        checkNetworkStatus();

      //  if (checkNetworkStatus()) {
//            Intent i=new Intent(HomeActivity.this,ForecastListviewActivity.class);
//            startActivity(i);
//            CurrentWeatherActivity.startCuurentWeatherActivity(HomeActivity.this);
//            finish();
//        } else {
//            mHomeActivityLayout.setVisibility(View.VISIBLE);
//        }
    }

    private void findViews() {
        mHomeActivityLayout = findViewById(R.id.activityHome_layout);
        mNoNetworkTextView = findViewById(R.id.activityHome_textView_noNetwork);
        mWifiSettingsButton=findViewById(R.id.activityHome_button_wifiSettings);
    }

    private void checkNetworkStatus() {
        Toast.makeText(this, "checking network", Toast.LENGTH_SHORT).show();
      //  if(checkNetworkNow) {
            if (helperFunctions.isNetworkConnected()) {
                CurrentWeatherActivity.startCuurentWeatherActivity(HomeActivity.this);
                finish();
        //    }
            } else {
                mHomeActivityLayout.setVisibility(View.VISIBLE);

            }

          //  return true;      // CurrentWeatherActivity.startCuurentWeatherActivity(HomeActivity.this);
//        } else {
//            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
//            return false;
//        }
    }

    @Override
    public void onClick(View clickedView) {
        if(clickedView.getId()==R.id.activityHome_button_wifiSettings)
            openNetworkSettings();
      //  checkNetworkStatus();
    }


    private void openNetworkSettings() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        startActivityForResult(intent,0);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(requestCode==0)
        {
            WifiManager wifiManager = (WifiManager)
                    getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if(!wifiManager.isWifiEnabled())
                Log.i("Error res:"," on result");
           onRestart();
        }
    }


}
