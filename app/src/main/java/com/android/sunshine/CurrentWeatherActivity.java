package com.android.sunshine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.sunshine.currentweather.CurrentWeather;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.sunshine.App.DEGREE_AND_C_SIGN;
import static com.android.sunshine.App.DEGREE_SIGN;
import static com.android.sunshine.App.PERCENT_SIGN;
import static com.android.sunshine.App.PRESSURE_UNIT;
import static com.android.sunshine.App.WINND_SPEED_UNIT;

public class CurrentWeatherActivity extends AppCompatActivity {

    private CurrentWeather mCurrentWeather;
    private TextView mTemp;
    private TextView mDescriptionTextView;
    private View mPressureView;
    private TextView mPressureTitleTextView;
    private TextView mPressureValueTextView;
    private View mMinMaxTempView;
    private TextView mMinMaxTempTitleTextView;
    private TextView mMinMaxTempValueTextView;
    private View mWindSpeedView;
    private TextView mWindSpeedTitleTextView;
    private TextView mWindSpeedValueTextView;

    private View mHumidityView;
    private TextView mHumidityTitleTextView;
    private TextView mHumidityValueTextView;

    private View mWindDirView;
    private TextView mWindDirTitleTextView;
    private TextView mWindDirValueTextView;

    private View mCloudyView;
    private TextView mCloudyTitleTextView;
    private TextView mCloudyValueTextView;

    private static String TIME_PATTERN = "h:mm a";
    private static long THOUSAND = 1000L;
    private TextView mSunriseTextView;
    private TextView mSunsetTextView;
    private String iconStr;

    public static void startCuurentWeatherActivity(Context context) {
        Intent currentWeatherIntent = new Intent(context, CurrentWeatherActivity.class);
        context.startActivity(currentWeatherIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_current_weather);

        findViews();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CurrentWeatherWebApi api = retrofit.create(CurrentWeatherWebApi.class);
        Call<CurrentWeather> call = api.currentWeatherApi();
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(@NonNull Call<CurrentWeather> currentWeatherCall, @NonNull Response<CurrentWeather> currentWeatherResponse) {
                mCurrentWeather = currentWeatherResponse.body();
                getDataFromResponse(mCurrentWeather);
            }

            @Override
            public void onFailure(@NonNull Call<CurrentWeather> onFailureall, @NonNull Throwable t) {
                Toast.makeText(CurrentWeatherActivity.this, "Something went wrong.\n  Please try later.", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void findViews() {
        mTemp = findViewById(R.id.activityCurrentWeather_textView_temp);
        mDescriptionTextView = findViewById(R.id.activityCurrentWeather_textView_description);

        mPressureView = findViewById(R.id.activityCurrentWeather_layout_atmosphericPressure);
        mPressureTitleTextView = mPressureView.findViewById(R.id.layoutInfoBox_textView_title);
        mPressureValueTextView = mPressureView.findViewById(R.id.layoutInfoBox_textView_value);

        mMinMaxTempView = findViewById(R.id.activityCurrentWeather_layout_minMaxTemp);
        mMinMaxTempTitleTextView = mMinMaxTempView.findViewById(R.id.layoutInfoBox_textView_title);
        mMinMaxTempValueTextView = mMinMaxTempView.findViewById(R.id.layoutInfoBox_textView_value);

        mWindSpeedView = findViewById(R.id.activityCurrentWeather_layout_windSpeed);
        mWindSpeedTitleTextView = mWindSpeedView.findViewById(R.id.layoutInfoBox_textView_title);
        mWindSpeedValueTextView = mWindSpeedView.findViewById(R.id.layoutInfoBox_textView_value);

        mHumidityView = findViewById(R.id.activityCurrentWeather_layout_humidity);
        mHumidityTitleTextView = mHumidityView.findViewById(R.id.layoutInfoBox_textView_title);
        mHumidityValueTextView = mHumidityView.findViewById(R.id.layoutInfoBox_textView_value);

        mWindDirView = findViewById(R.id.activityCurrentWeather_layout_windDirection);
        mWindDirTitleTextView = mWindDirView.findViewById(R.id.layoutInfoBox_textView_title);
        mWindDirValueTextView = mWindDirView.findViewById(R.id.layoutInfoBox_textView_value);


        mCloudyView = findViewById(R.id.activityCurrentWeather_layout_clouds);
        mCloudyTitleTextView = mCloudyView.findViewById(R.id.layoutInfoBox_textView_title);
        mCloudyValueTextView = mCloudyView.findViewById(R.id.layoutInfoBox_textView_value);

        mSunriseTextView = findViewById(R.id.activityCurrentWeather_textView_sunrise);
        mSunsetTextView = findViewById(R.id.activityCurrentWeather_textView_sunset);
    }


    private void getDataFromResponse(CurrentWeather mCurrentWeather) {
        mDescriptionTextView.setText(mCurrentWeather.getWeather().get(0).getMain());
        iconStr = mCurrentWeather.getWeather().get(0).getIcon();
        Uri uri = Uri.parse("http://openweathermap.org/img/w/" + iconStr + ".png");
        SimpleDraweeView draweeView = findViewById(R.id.activityCurrentWeather_icon);
        draweeView.setImageURI(uri);
        draweeView.setImageURI(uri);

        double tempDouble =mCurrentWeather.getMain().getTemp();
        int tempInt=(int)tempDouble;
        mTemp.setText(tempInt+ DEGREE_AND_C_SIGN);

        mPressureTitleTextView.setText(R.string.atmosphericPressure);
        double pressure = mCurrentWeather.getMain().getPressure();
        mPressureValueTextView.setText(pressure + PRESSURE_UNIT);

        mMinMaxTempTitleTextView.setText(R.string.min_max_temp);
        double minTemp = mCurrentWeather.getMain().getTempMin();
        int minTempInt=(int) minTemp;
        double maxTemp = mCurrentWeather.getMain().getTempMax();
        int maxTempInt=(int) maxTemp;
        mMinMaxTempValueTextView.setText(minTempInt + DEGREE_AND_C_SIGN + "-" +
                maxTempInt + DEGREE_AND_C_SIGN);

        mWindSpeedTitleTextView.setText(R.string.wind_speed);
        double windSpeed = mCurrentWeather.getWind().getSpeed();
        mWindSpeedValueTextView.setText(String.valueOf((int) windSpeed) + WINND_SPEED_UNIT);

        mWindDirTitleTextView.setText(R.string.wind_direction);
        int winDirection = mCurrentWeather.getWind().getDeg();
        mWindDirValueTextView.setText(winDirection + DEGREE_SIGN);

        mHumidityTitleTextView.setText(R.string.humidity);
        String humidity =String.valueOf(mCurrentWeather.getMain().getHumidity());
        mHumidityValueTextView.setText(humidity + PERCENT_SIGN);

        mCloudyTitleTextView.setText(R.string.clouds);
        String cloudy =String.valueOf(mCurrentWeather.getClouds().getAll());
        mCloudyValueTextView.setText(cloudy + PERCENT_SIGN);

        long sunrise = mCurrentWeather.getSys().getSunrise();
        mSunriseTextView.setText(convertTime(sunrise));

        long sunset = mCurrentWeather.getSys().getSunset();
        mSunsetTextView.setText(convertTime(sunset));
    }

    public static String convertTime(long unixTime) {
        String time = new SimpleDateFormat(TIME_PATTERN)
                .format(new Date(unixTime * THOUSAND));
        return time;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.currentweather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.weatherForecast_menu)
            ForecastListviewActivity.startForecastActivity(CurrentWeatherActivity.this);
        return super.onOptionsItemSelected(item);
    }
}
