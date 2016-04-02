package com.juanma.weatherapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.juanma.weatherapp.R;
import com.juanma.weatherapp.activities.ForecastDetailActivity;
import com.juanma.weatherapp.activities.ForecastListActivity;
import com.juanma.weatherapp.models.Forecast;
import com.juanma.weatherapp.utils.WeatherUtils;

/**
 * A fragment representing a single Forecast detail screen.
 * This fragment is either contained in a {@link ForecastListActivity}
 * in two-pane mode (on tablets) or a {@link ForecastDetailActivity}
 * on handsets.
 */
public class ForecastDetailFragment extends Fragment {

    private Forecast mForecast;

    public ForecastDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mForecast = getArguments().getParcelable(getString(R.string.key_extra_forecast));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.forecast_detail, container, false);

        if (mForecast != null) {
            ((ImageView) rootView.findViewById(R.id.ivImage)).setImageResource(WeatherUtils.getIconResourceForWeatherCondition(mForecast.getWeather().getId()));
            ((TextView) rootView.findViewById(R.id.tvMain)).setText(mForecast.getWeather().getMain());
            ((TextView) rootView.findViewById(R.id.tvDescription)).setText(mForecast.getWeather().getDescription());
            ((TextView) rootView.findViewById(R.id.tvTempMinMax)).setText(WeatherUtils.getMinMaxTempText(mForecast.getTemperature().getMin(), mForecast.getTemperature().getMax()));
            ((TextView) rootView.findViewById(R.id.tvHumidValue)).setText(String.valueOf(mForecast.getHumidity()));
            ((TextView) rootView.findViewById(R.id.tvPressureValue)).setText(WeatherUtils.getPressureUnit(mForecast.getPressure()));
            ((TextView) rootView.findViewById(R.id.tvRainValue)).setText(String.valueOf(mForecast.getRain()));
            ((TextView) rootView.findViewById(R.id.tvMorningValue)).setText(WeatherUtils.getTempUnit(mForecast.getTemperature().getMorning()));
            ((TextView) rootView.findViewById(R.id.tvEveValue)).setText(WeatherUtils.getTempUnit(mForecast.getTemperature().getEvening()));
            ((TextView) rootView.findViewById(R.id.tvDayValue)).setText(WeatherUtils.getTempUnit(mForecast.getTemperature().getDay()));
            ((TextView) rootView.findViewById(R.id.tvNightValue)).setText(WeatherUtils.getTempUnit(mForecast.getTemperature().getNight()));
            ((TextView) rootView.findViewById(R.id.tvWindValue)).setText(WeatherUtils.getWindUnit(mForecast.getSpeed()));
            ((TextView) rootView.findViewById(R.id.tvCloudsValue)).setText(WeatherUtils.getCloudsUnit(mForecast.getClouds()));
        }

        return rootView;
    }
}
