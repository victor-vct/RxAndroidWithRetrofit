package com.vctapps.myrxexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vctapps.myrxexample.model.Main;
import com.vctapps.myrxexample.model.WeatherData;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button mButton;
    private TextView mTextMaxTemp;
    private TextView mTextMinTemp;
    private EditText mEditNameCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.btn_download);
        mTextMaxTemp = (TextView) findViewById(R.id.tv_max_temp);
        mTextMinTemp = (TextView) findViewById(R.id.tv_min_temp);

        WeatherService api = getRetrofit().create(WeatherService.class);

        final Observable<WeatherData> observable = api.getAtualWeather("Sao Caetano", WeatherService.APPID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        mButton.setOnClickListener(v -> observable.subscribe(
                weatherData -> showWeather(weatherData)));
    }

    private void showWeather(WeatherData weatherData){
        mTextMinTemp.setText(weatherData.getMain().getTemp_min() + "ºF");
        mTextMaxTemp.setText(weatherData.getMain().getTemp_max() + "ºF");
    }

    private Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(WeatherService.URL_BASE)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
