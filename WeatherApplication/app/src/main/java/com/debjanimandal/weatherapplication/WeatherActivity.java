package com.debjanimandal.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {

    private TextView cityWeather,temperatureWeather,weatherConditionWeather,humidityWeather,maxTempWeather,minTempWeather,pressureWeather,windWeather;
    private ImageView imageViewWeather;
    private Button search;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        cityWeather=findViewById(R.id.textViewCityWeather);
        temperatureWeather=findViewById(R.id.textViewTempWeather);
        weatherConditionWeather=findViewById(R.id.textViewWeatherConditionWeather);
        humidityWeather=findViewById(R.id.textViewHumidityWeather);
        maxTempWeather=findViewById(R.id.textViewMaxTempWeather);
        minTempWeather=findViewById(R.id.textViewMinTempWeather);
        pressureWeather=findViewById(R.id.textViewPressureWeather);
        windWeather=findViewById(R.id.textViewWindWeather);
        imageViewWeather=findViewById(R.id.imageViewWeather);
        search=findViewById(R.id.search);
        editText=findViewById(R.id.editTextCityName);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName=editText.getText().toString();
                getWeatherData(cityName);
                editText.setText("");
            }
        });

    }
    public void getWeatherData(String name)
    {
        WeatherAPI weatherAPI=RetrofitWeather.getClient().create(WeatherAPI.class);
        Call<OpenWeatherMap> call=weatherAPI.getWeatherWithCityName(name);
        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {
                if(response.isSuccessful()) {
                    cityWeather.setText(response.body().getName() + " , " + response.body().getSys().getCountry());
                    temperatureWeather.setText(response.body().getMain().getTemp() + " °C");
                    weatherConditionWeather.setText(response.body().getWeather().get(0).getDescription());
                    humidityWeather.setText(" : " + response.body().getMain().getHumidity() + "%");
                    maxTempWeather.setText(" : " + response.body().getMain().getTempMax() + " °C");
                    minTempWeather.setText(" : " + response.body().getMain().getTempMin() + " °C");
                    pressureWeather.setText(" : " + response.body().getMain().getHumidity());
                    windWeather.setText(" : " + response.body().getWind().getSpeed());

                    String iconCode = response.body().getWeather().get(0).getIcon();
                    Picasso.get().load("https://openweathermap.org/img/wn/" + iconCode + "@2x.png")
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(imageViewWeather);
                }
                else {
                    Toast.makeText(WeatherActivity.this, "City not found, Please try again.", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<OpenWeatherMap> call, Throwable t) {

            }
        });
    }
}