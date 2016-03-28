package com.juanma.weatherapp.utils;

import com.juanma.weatherapp.R;

/**
 * Created by JuanMa on 27/3/16.
 */
public class WeatherUtils {
    //Based on Utility class found at:
    //https://raw.githubusercontent.com/udacity/Sunshine-Version-2/sunshine_master/app/src/main/java/com/example/android/sunshine/app/Utility.java

    /**
     * Helper method to provide the icon resource id according to the weather condition id returned
     * by the OpenWeatherMap call.
     * @param weatherId from OpenWeatherMap API response
     * @return resource id for the corresponding icon. -1 if no relation is found.
     */
    public static int getIconResourceForWeatherCondition(int weatherId) {
        // Based on weather code data found at:
        // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.ic_weather_lightning_grey600_48dp;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.ic_weather_rainy_grey600_48dp;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.ic_weather_pouring_grey600_48dp;
        } else if (weatherId == 511) {
            return R.drawable.ic_weather_snowy_grey600_48dp;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.ic_weather_pouring_grey600_48dp;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.ic_weather_snowy_grey600_48dp;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.ic_weather_fog_grey600_48dp;
        } else if (weatherId == 761 || weatherId == 781) {
            return R.drawable.ic_weather_lightning_grey600_48dp;
        } else if (weatherId == 800) {
            return R.drawable.ic_weather_sunny_grey600_48dp;
        } else if (weatherId == 801) {
            return R.drawable.ic_weather_partlycloudy_grey600_48dp;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.ic_weather_cloudy_grey600_48dp;
        }
        return -1;
    }

    /**
     * Helper method to provide the art resource id according to the weather condition id returned
     * by the OpenWeatherMap call.
     * @param weatherId from OpenWeatherMap API response
     * @return resource id for the corresponding icon. -1 if no relation is found.
     */
//    public static int getArtResourceForWeatherCondition(int weatherId) {
//        // Based on weather code data found at:
//        // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
//        if (weatherId >= 200 && weatherId <= 232) {
//            return R.drawable.art_storm;
//        } else if (weatherId >= 300 && weatherId <= 321) {
//            return R.drawable.art_light_rain;
//        } else if (weatherId >= 500 && weatherId <= 504) {
//            return R.drawable.art_rain;
//        } else if (weatherId == 511) {
//            return R.drawable.art_snow;
//        } else if (weatherId >= 520 && weatherId <= 531) {
//            return R.drawable.art_rain;
//        } else if (weatherId >= 600 && weatherId <= 622) {
//            return R.drawable.art_snow;
//        } else if (weatherId >= 701 && weatherId <= 761) {
//            return R.drawable.art_fog;
//        } else if (weatherId == 761 || weatherId == 781) {
//            return R.drawable.art_storm;
//        } else if (weatherId == 800) {
//            return R.drawable.art_clear;
//        } else if (weatherId == 801) {
//            return R.drawable.art_light_clouds;
//        } else if (weatherId >= 802 && weatherId <= 804) {
//            return R.drawable.art_clouds;
//        }
//        return -1;
//    }

}
