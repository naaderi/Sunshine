package com.android.sunshine;

import com.android.sunshine.currentweather.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nazila on 12/19/2017.
 */

public interface CurrentWeatherWebApi {
    @GET("weather?id=112931&units=metric&lang=fa&APPID=913bebceaa8367d09d1da9a134c525ba")
    Call<CurrentWeather> currentWeatherApi();
}
