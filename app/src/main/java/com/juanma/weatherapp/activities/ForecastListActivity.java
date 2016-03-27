package com.juanma.weatherapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.juanma.weatherapp.R;

import com.juanma.weatherapp.fragments.ForecastDetailFragment;
import com.juanma.weatherapp.managers.VolleyManager;
import com.juanma.weatherapp.models.Forecast;
import com.juanma.weatherapp.models.Weather;

import org.json.JSONObject;

import java.util.List;

/**
 * An activity representing a list of Forecasts. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ForecastDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ForecastListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private VolleyManager volleyManager;
    private static final String URL_WEATHER = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Rosario&mode=json&units=metric&cnt=7&appid=794a9f16417650c01f47be753acc436d";
    private List<Forecast> mForecastList;
    public static final String TAG = ForecastListActivity.class.getSimpleName();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        recyclerView = (RecyclerView) findViewById(R.id.forecast_list);

        getForecasts();

        if (findViewById(R.id.forecast_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new ForecastListAdapter(mForecastList));
    }

    private void getForecasts() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL_WEATHER,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Weather weather = parseWeatherToJson(response.toString());
                        mForecastList = weather.getForecasts();
                        setupRecyclerView(recyclerView);
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

    private Weather parseWeatherToJson(String jsonObject) {
        Gson gson = new Gson();
        Weather weather = gson.fromJson(jsonObject, Weather.class);
        return weather;
    }

    public class ForecastListAdapter
            extends RecyclerView.Adapter<ForecastListAdapter.ForecastViewHolder> {

        private final List<Forecast> mForecasts;

        public ForecastListAdapter(List<Forecast> items) {
            mForecasts = items;
        }

        @Override
        public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.forecast_list_content, parent, false);
            return new ForecastViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ForecastViewHolder holder, int position) {
            holder.mItem = mForecasts.get(position);
            holder.mIdView.setText(String.valueOf(mForecasts.get(position).getHumidity()));
            holder.mContentView.setText(String.valueOf(mForecasts.get(position).getPressure()));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        //arguments.putString(ForecastDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        arguments.putString(ForecastDetailFragment.ARG_ITEM_ID, "1");
                        ForecastDetailFragment fragment = new ForecastDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.forecast_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ForecastDetailActivity.class);
                        //intent.putExtra(ForecastDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        intent.putExtra(ForecastDetailFragment.ARG_ITEM_ID, "1");

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mForecasts.size();
        }

        public class ForecastViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Forecast mItem;

            public ForecastViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
