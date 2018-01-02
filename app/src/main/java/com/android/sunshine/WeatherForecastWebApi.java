package com.android.sunshine;

import com.android.sunshine.weatherforecast.WeatherForecast;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nazila on 12/17/2017.
 */

public interface WeatherForecastWebApi {
    @GET("forecast/daily?id=112931&units=metric&lang=fa&cnt=7&APPID=913bebceaa8367d09d1da9a134c525ba")
    Call<WeatherForecast> weatherForecastApi();
}
