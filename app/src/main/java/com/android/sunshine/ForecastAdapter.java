package com.android.sunshine;

import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.sunshine.helpers.ConvertDate;
import com.android.sunshine.weatherforecast.WeatherForecast;
import com.facebook.drawee.view.SimpleDraweeView;

import static com.android.sunshine.App.DEGREE_AND_C_SIGN;
import static com.android.sunshine.App.DEGREE_SIGN;
import static com.android.sunshine.App.PERCENT_SIGN;
import static com.android.sunshine.App.PRESSURE_UNIT;
import static com.android.sunshine.App.WINND_SPEED_UNIT;

/**
 * Created by Nazila on 12/6/2017.
 */
public class ForecastAdapter extends BaseAdapter {
    private WeatherForecast mForecastListItem;
    private String iconStr;
    private String weatherDescription = "";
    private TextView dateTextView;

    public ForecastAdapter(WeatherForecast ForecastListItem) {
        this.mForecastListItem = ForecastListItem;
    }

    @Override
    public int getCount() {
        return mForecastListItem.getList().size();
    }

    @Override
    public Object getItem(int i) {
        return mForecastListItem.getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowViews = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_listview_forecast, null, false);

        //forecast -set description
        weatherDescription = mForecastListItem.getList().get(i).getWeather().get(0).getMain();
        TextView descriptionTextView = rowViews.findViewById(R.id.layoutDescriptIconDate_textView_description);
        descriptionTextView.setText(weatherDescription);

        //set icon
        iconStr = mForecastListItem.getList().get(i).getWeather().get(0).getIcon();

        Uri uri = Uri.parse("http://openweathermap.org/img/w/" + iconStr + ".png");
        SimpleDraweeView draweeView = rowViews.findViewById(R.id.layoutDescriptIconDate_imageView_icon);
        draweeView.setImageURI(uri);
        //set date

        long unixSeconds = mForecastListItem.getList().get(i).getDt();
        String date = ConvertDate.convertDate(unixSeconds);
        dateTextView = rowViews.findViewById(R.id.layoutDescriptIconDate_textView_date);
        if (!TextUtils.isEmpty(String.valueOf(date)))
            dateTextView.setText(date);

        //set temperature in parts of day
        View tempsLayout = rowViews.findViewById(R.id.rowListView_layout_temps);
//        set morning temperature
        double morningTemp = mForecastListItem.getList().get(i).getTemp().getMorn();
        TextView morningTempTextView = tempsLayout.findViewById(R.id.layoutTemperature_textView_morningTemp);
        morningTempTextView.setText(String.valueOf((int) morningTemp) + DEGREE_AND_C_SIGN);
//        //set day temp temperature
        double dayTemp = mForecastListItem.getList().get(i).getTemp().getDay();
        TextView dayTempTextView = tempsLayout.findViewById(R.id.layoutTemperature_textView_dayTemp);
        dayTempTextView.setText(String.valueOf((int) dayTemp) + DEGREE_AND_C_SIGN);
//        //set evening temperature
        double eveningTemp = mForecastListItem.getList().get(i).getTemp().getEve();
        TextView eveningTempTextView = tempsLayout.findViewById(R.id.layoutTemperature_textView_eveningTemp);
        eveningTempTextView.setText(String.valueOf((int) eveningTemp) + DEGREE_AND_C_SIGN);
//        //set night temperature
        double nightTemp = mForecastListItem.getList().get(i).getTemp().getNight();
        TextView nightTempTextView = tempsLayout.findViewById(R.id.layoutTemperature_textView_nightTemp);
        nightTempTextView.setText(String.valueOf((int) nightTemp) + DEGREE_AND_C_SIGN);
////        //info box
////        //atmosphericPressure title
        View infoBoxesPressure = rowViews.findViewById(R.id.rowListViewForecast_layout_infoBox_atmosphericPressure);
        TextView atmosphericPressureTitle = infoBoxesPressure.findViewById(R.id.layoutInfoBox_textView_title);
        atmosphericPressureTitle.setText(R.string.atmosphericPressure);
        //atmosphericPressure value
        double pressure = mForecastListItem.getList().get(i).getPressure();
        TextView atmosphericPressureValue = infoBoxesPressure.findViewById(R.id.layoutInfoBox_textView_value);
        atmosphericPressureValue.setText(String.valueOf((int) pressure)+PRESSURE_UNIT);

        //min max temp
        View infoBoxesMinMaxTemp = rowViews.findViewById(R.id.rowListViewForecast_layout_infoBox_minMaxTemp);
        TextView minMaxTempTitle = infoBoxesMinMaxTemp.findViewById(R.id.layoutInfoBox_textView_title);
        minMaxTempTitle.setText(R.string.min_max_temp);
        // value
        double minTemp = mForecastListItem.getList().get(i).getTemp().getMin();
        double maxTemp = mForecastListItem.getList().get(i).getTemp().getMax();

        TextView minMaxTempValue = infoBoxesMinMaxTemp.findViewById(R.id.layoutInfoBox_textView_value);
        minMaxTempValue.setText(String.valueOf((int) minTemp) + DEGREE_AND_C_SIGN + "-" + String.valueOf((int) maxTemp) + DEGREE_AND_C_SIGN);

        //wind speed
        View infoBoxesWindSpeed = rowViews.findViewById(R.id.rowListViewForecast_layout_infoBox_windSpeed);
        TextView windSpeedTitle = infoBoxesWindSpeed.findViewById(R.id.layoutInfoBox_textView_title);
        windSpeedTitle.setText(R.string.wind_speed);

        //value
        double windSpeed = mForecastListItem.getList().get(i).getSpeed();
        TextView windSpeedValue = infoBoxesWindSpeed.findViewById(R.id.layoutInfoBox_textView_value);
        windSpeedValue.setText(String.valueOf((int) windSpeed)+WINND_SPEED_UNIT);

        //humidity
        View infoBoxesHumidity = rowViews.findViewById(R.id.rowListViewForecast_layout_infoBox_humidity);
        TextView humidityTitle = infoBoxesHumidity.findViewById(R.id.layoutInfoBox_textView_title);
        humidityTitle.setText(R.string.humidity);
        //value
        int humidity = mForecastListItem.getList().get(i).getHumidity();
        TextView humidityValue = infoBoxesHumidity.findViewById(R.id.layoutInfoBox_textView_value);
    humidityValue.setText(String.valueOf(humidity)+PERCENT_SIGN);


        //wind direction
        View infoBoxesWindDirection = rowViews.findViewById(R.id.rowListViewForecast_layout_infoBox_windDirection);
        TextView windDirectionTitle = infoBoxesWindDirection.findViewById(R.id.layoutInfoBox_textView_title);
        windDirectionTitle.setText(R.string.wind_direction);
        //value
        double windDirection = mForecastListItem.getList().get(i).getDeg();
        TextView windDirectionValue = infoBoxesWindDirection.findViewById(R.id.layoutInfoBox_textView_value);
        windDirectionValue.setText(String.valueOf((int) windDirection)+DEGREE_SIGN);
//        //cloud cover
        View infoBoxesClouds = rowViews.findViewById(R.id.rowListViewForecast_layout_infoBox_clouds);
        TextView cloudsTitle = infoBoxesClouds.findViewById(R.id.layoutInfoBox_textView_title);
        cloudsTitle.setText(R.string.clouds);
        //value
        int cloudy = mForecastListItem.getList().get(i).getClouds();
        TextView cloudsValue = infoBoxesClouds.findViewById(R.id.layoutInfoBox_textView_value);
        cloudsValue.setText(String.valueOf(cloudy)+PERCENT_SIGN);

        return rowViews;
    }


}
