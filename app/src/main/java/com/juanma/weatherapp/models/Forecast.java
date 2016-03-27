package com.juanma.weatherapp.models;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by JuanMa on 26/3/16.
 */
public class Forecast {

    private double pressure;
    private double humidity;
    private double speed;
    private double clouds;
    @SerializedName("temp")
    private Temperature temperature;
    private List<Weather> weather;

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

    public class Temperature{
        private Double day;
        private Double night;
        @SerializedName("eve")
        private Double evening;
        @SerializedName("mor")
        private Double morning;
        private Double min;
        private Double max;

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
    }

    public class Weather{
        private int id;
        private String main;
        private String description;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
