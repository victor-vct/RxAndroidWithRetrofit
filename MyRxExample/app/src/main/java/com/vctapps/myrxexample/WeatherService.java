package com.vctapps.myrxexample;

import com.vctapps.myrxexample.model.Main;
import com.vctapps.myrxexample.model.WeatherData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by vpaulino on 04/11/2016.
 */

public interface WeatherService {

    static final String URL_BASE = "http://api.openweathermap.org/";
    static final String APPID = "YOUR_APPID";

    @GET("data/2.5/weather")
    Observable<WeatherData> getAtualWeather(@Query("q") String cityName, @Query("appid") String appId);
}
