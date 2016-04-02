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
                btnAceptar.setEnabled(false);
                if(etLocation.getText().toString().length() == 0){
                    Toast.makeText(MainActivity.this, R.string.error_empty_location,Toast.LENGTH_SHORT);
                    btnAceptar.setEnabled(true);
                    return;
                }
                getForecasts();
            }
        });
    }

    private void getForecasts() {
        String uri = String.format("http://api.openweathermap.org/data/2.5/forecast/daily?q=%s&mode=json&units=metric&cnt=7&appid=%s",
                etLocation.getText().toString(),
                getResources().getString(R.string.weather_appid));
        Log.d("URL", uri);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                uri,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        WeatherCity weatherCity = parseWeatherToJson(response.toString());
                        btnAceptar.setEnabled(true);
                        startListForecast(weatherCity);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        btnAceptar.setEnabled(true);
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
            intent.putExtra(getString(R.string.key_extra_weather_city), wc);
            intent.putExtra(getString(R.string.key_extra_city_name), etLocation.getText().toString());
            startActivity(intent);
        }else{
            Toast.makeText(this, R.string.error_no_results,Toast.LENGTH_SHORT);
        }
    }
}
