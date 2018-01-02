package com.android.sunshine;

/**
 * Created by Nazila on 12/12/2017.
 */

public class ForecastListItem {
    private String mDate;
    private String mWeatherDescription;
    private String mWeatherDescriptionIcon;

    private double mMorningTemperature;
    private double mDayTemperature;
    private double mEveningTemperature;
    private double mNightTemperature;

    private double mMinDailyTemperature;
    private double mMaxDailyTemperature;

    private int mClouds;
    private double mAtmosphericPressure;
    private int mHumidity;
    private double mWindSpeed;
    private int mWindDirection;

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getWeatherDescription() {
        return mWeatherDescription;
    }

    public void setWeatherDescription(String mWeatherDescription) {
        this.mWeatherDescription = mWeatherDescription;
    }

    public String getWeatherDescriptionIcon() {
        return mWeatherDescriptionIcon;
    }

    public void setWeatherDescriptionIcon(String mWeatherDescriptionIcon) {
        this.mWeatherDescriptionIcon = mWeatherDescriptionIcon;
    }

    public double getMorningTemperature() {
        return mMorningTemperature;
    }

    public void setMorningTemperature(double mMorningTemperature) {
        this.mMorningTemperature = mMorningTemperature;
    }

    public double getDayTemperature() {
        return mDayTemperature;
    }

    public void setDayTemperature(double mDayTemperature) {
        this.mDayTemperature = mDayTemperature;
    }

    public double getEveningTemperature() {
        return mEveningTemperature;
    }

    public void setEveningTemperature(double mEveningTemperature) {
        this.mEveningTemperature = mEveningTemperature;
    }

    public double getNightTemperature() {
        return mNightTemperature;
    }

    public void setNightTemperature(double mNightTemperature) {
        this.mNightTemperature = mNightTemperature;
    }

    public double getMinDailyTemperature() {
        return mMinDailyTemperature;
    }

    public void setMinDailyTemperature(double mMinDailyTemperature) {
        this.mMinDailyTemperature = mMinDailyTemperature;
    }

    public double getMaxDailyTemperature() {
        return mMaxDailyTemperature;
    }

    public void setMaxDailyTemperature(double mMaxDailyTemperature) {
        this.mMaxDailyTemperature = mMaxDailyTemperature;
    }

    public int getClouds() {
        return mClouds;
    }

    public void setClouds(int mClouds) {
        this.mClouds = mClouds;
    }

    public double getAtmosphericPressure() {
        return mAtmosphericPressure;
    }

    public void setAtmosphericPressure(double mAtmosphericPressure) {
        this.mAtmosphericPressure = mAtmosphericPressure;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public void setHumidity(int mHumidity) {
        this.mHumidity = mHumidity;
    }

    public double getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(double mWindSpeed) {
        this.mWindSpeed = mWindSpeed;
    }

    public int getWindDirection() {
        return mWindDirection;
    }

    public void setWindDirection(int mWindDirection) {
        this.mWindDirection = mWindDirection;
    }
}

