package com.juanma.weatherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by JuanMa on 27/3/16.
 */
public class WeatherCity {
    @SerializedName("list")
    List<Forecast> forecasts;

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
}
