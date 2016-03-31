package com.juanma.weatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by JuanMa on 27/3/16.
 */
public class WeatherCity implements Parcelable {
    @SerializedName("list")
    List<Forecast> forecasts;

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(forecasts);
    }

    public WeatherCity() {
    }

    protected WeatherCity(Parcel in) {
        this.forecasts = in.createTypedArrayList(Forecast.CREATOR);
    }

    public static final Parcelable.Creator<WeatherCity> CREATOR = new Parcelable.Creator<WeatherCity>() {
        @Override
        public WeatherCity createFromParcel(Parcel source) {
            return new WeatherCity(source);
        }

        @Override
        public WeatherCity[] newArray(int size) {
            return new WeatherCity[size];
        }
    };
}
