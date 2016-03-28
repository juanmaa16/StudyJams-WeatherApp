package com.juanma.weatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JuanMa on 26/3/16.
 */
public class Forecast implements Parcelable {

    private double pressure;
    private double humidity;
    private double speed;
    private double clouds;
    @SerializedName("temp")
    private Temperature temperature;
    private List<Weather> weather;

    public Forecast() {
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getClouds() {
        return clouds;
    }

    public void setClouds(double clouds) {
        this.clouds = clouds;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Weather getWeather() {
        return weather.get(0);
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.pressure);
        dest.writeDouble(this.humidity);
        dest.writeDouble(this.speed);
        dest.writeDouble(this.clouds);
        dest.writeParcelable(this.temperature, flags);
        dest.writeList(this.weather);
    }

    protected Forecast(Parcel in) {
        this.pressure = in.readDouble();
        this.humidity = in.readDouble();
        this.speed = in.readDouble();
        this.clouds = in.readDouble();
        this.temperature = in.readParcelable(Temperature.class.getClassLoader());
        this.weather = new ArrayList<Weather>();
        in.readList(this.weather, Weather.class.getClassLoader());
    }

    public static final Parcelable.Creator<Forecast> CREATOR = new Parcelable.Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel source) {
            return new Forecast(source);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };

}
