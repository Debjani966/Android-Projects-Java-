package com.debjanimandal.weatherapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET("weather?appid=75dd72eb927d8bd6f8592257063398d2&units=metric")
    Call<OpenWeatherMap> getWeatherWithLocation(@Query("lat")double lat,@Query("lon")double lon);

    @GET("weather?appid=75dd72eb927d8bd6f8592257063398d2&units=metric")
    Call<OpenWeatherMap> getWeatherWithCityName(@Query("q")String name);
}
