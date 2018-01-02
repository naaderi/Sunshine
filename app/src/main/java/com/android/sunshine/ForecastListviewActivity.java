package com.android.sunshine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.sunshine.weatherforecast.WeatherForecast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForecastListviewActivity extends AppCompatActivity {

    private ListView mForecastListview;
    private ForecastAdapter mForecastAdapter;
    private WeatherForecast mWeatherForecast;

    public static void startForecastActivity(Context context) {
        Intent forecastIntent = new Intent(context, ForecastListviewActivity.class);
        context.startActivity(forecastIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_listview);

        findViews();
        callRetrofit();
    }

    private void findViews() {
        mForecastListview = findViewById(R.id.activityForecasListView_listView);
    }

    private void initListView() {
        mForecastAdapter = new ForecastAdapter(mWeatherForecast);
        mForecastListview.setAdapter(mForecastAdapter);
    }

    private void callRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherForecastWebApi api = retrofit.create(WeatherForecastWebApi.class);
        Call<WeatherForecast> weatherForecastResponse = api.weatherForecastApi();
        weatherForecastResponse.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                mWeatherForecast = response.body();
                initListView();
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                HomeActivity.startHomeActivity(ForecastListviewActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.weatherforecast,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.exit)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
