package com.juanma.weatherapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.juanma.weatherapp.R;
import com.juanma.weatherapp.managers.VolleyManager;
import com.juanma.weatherapp.models.Forecast;
import com.juanma.weatherapp.models.WeatherCity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private VolleyManager volleyManager;
    private static final String URL_WEATHER = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Rosario&mode=json&units=metric&cnt=7&appid=794a9f16417650c01f47be753acc436d";
    private EditText etLocation;
    private Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLocation = (EditText) findViewById(R.id.etLocation);
        btnAceptar = (Button) findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getForecasts();
            }
        });
    }

    private void getForecasts() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL_WEATHER,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        WeatherCity weatherCity = parseWeatherToJson(response.toString());
                        startListForecast(weatherCity);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());
                    }
                }
        );

        volleyManager = VolleyManager.getInstance(this);
        volleyManager.addToRequestQueue(jsonObjectRequest);
    }

    private WeatherCity parseWeatherToJson(String jsonObject) {
        Gson gson = new Gson();
        WeatherCity weatherCity = gson.fromJson(jsonObject, WeatherCity.class);
        return weatherCity;
    }

    private void startListForecast(WeatherCity wc){
        if(wc.getForecasts().size()>0){
            Intent intent = new Intent(this, ForecastListActivity.class);
            intent.putExtra("weatherCity", wc);
            startActivity(intent);
        }else{
            Toast.makeText(this, R.string.error_sin_resultados,Toast.LENGTH_SHORT);
        }
    }
}
