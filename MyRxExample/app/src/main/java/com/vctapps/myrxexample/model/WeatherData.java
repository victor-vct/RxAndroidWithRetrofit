package com.vctapps.myrxexample.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vpaulino on 04/11/2016.
 */

public class WeatherData {

    @SerializedName("main")
    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
