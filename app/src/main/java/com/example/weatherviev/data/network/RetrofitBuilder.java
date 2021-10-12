package com.example.weatherviev.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static RetrofitServis servis;
    public static RetrofitServis getServis(){
        if (servis==null){
            servis=buildRetrofit();
        }
        return servis;
    }

    private static RetrofitServis buildRetrofit(){
        return new Retrofit.Builder().baseUrl("https://api.openweathermap.org/").addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitServis.class);

    }
}
//https://api.openweathermap.org/data/2.5/weather?=London&=

/*
https://api.openweathermap.org/data/2.5/weather?q=London&appid=bff315489a5688c022edb7061bc96397*/