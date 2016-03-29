package com.juanma.weatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JuanMa on 28/3/16.
 */
public class Temperature implements Parcelable {
    private Double day;
    private Double night;
    @SerializedName("eve")
    private Double evening;
    @SerializedName("morn")
    private Double morning;
    private Double min;
    private Double max;

    public Temperature() {
    }

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Double getNight() {
        return night;
    }

    public void setNight(Double night) {
        this.night = night;
    }

    public Double getEvening() {
        return evening;
    }

    public void setEvening(Double evening) {
        this.evening = evening;
    }

    public Double getMorning() {
        return morning;
    }

    public void setMorning(Double morning) {
        this.morning = morning;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.day);
        dest.writeValue(this.night);
        dest.writeValue(this.evening);
        dest.writeValue(this.morning);
        dest.writeValue(this.min);
        dest.writeValue(this.max);
    }

    protected Temperature(Parcel in) {
        this.day = (Double) in.readValue(Double.class.getClassLoader());
        this.night = (Double) in.readValue(Double.class.getClassLoader());
        this.evening = (Double) in.readValue(Double.class.getClassLoader());
        this.morning = (Double) in.readValue(Double.class.getClassLoader());
        this.min = (Double) in.readValue(Double.class.getClassLoader());
        this.max = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Temperature> CREATOR = new Parcelable.Creator<Temperature>() {
        @Override
        public Temperature createFromParcel(Parcel source) {
            return new Temperature(source);
        }

        @Override
        public Temperature[] newArray(int size) {
            return new Temperature[size];
        }
    };
}