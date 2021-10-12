package com.example.weatherviev.data.network;

import com.example.weatherviev.data.pojo.CastWeather;
import com.example.weatherviev.data.pojo.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitServis {

    @GET("data/2.5/weather")
    Call<CurrentWeather> getDataFronApi(@Query("q")String cityName, @Query("appid") String appid,@Query("units")String metric);



}
//lat=35&lon=139&=bff315489a5688c022edb7061bc96397


/*
https://api.openweathermap.org/data/2.5/weather?q=London&appid=bff315489a5688c022edb7061bc96397*/
