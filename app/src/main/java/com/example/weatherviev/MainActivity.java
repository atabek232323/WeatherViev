package com.example.weatherviev;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.weatherviev.data.network.RetrofitBuilder;
import com.example.weatherviev.data.pojo.CurrentWeather;
import com.example.weatherviev.databinding.ActivityMainBinding;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.DataFormatException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ImageView imageView;
    TextView month,day,eyar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        month = findViewById(R.id.month);
        day = findViewById(R.id.textView13);
        eyar = findViewById(R.id.tv_eyar);

        Date currenttime = Calendar.getInstance().getTime();
        String formattedData = DateFormat.getInstance().format(currenttime);
        String [] splitDate = formattedData.split(",");
        Log.d("myLog",currenttime.toString());
        Log.d("myLog",formattedData);
        month.setText(splitDate[1]);
        day.setText(splitDate[0]);
        eyar.setText(splitDate[2]);
        Log.d("myLog",splitDate[0].trim());
        Log.d("myLog",splitDate[1].trim());
        Log.d("myLog",splitDate[2].trim());



             imageView = findViewById(R.id.imageView);
             int id = R.drawable.san;
        Glide.with(getApplicationContext()).load(id).override(100,100).into(imageView);


        binding.btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logData();
            }
        });
    }


    private void logData() {

        String s = binding.etText.getText().toString();
        RetrofitBuilder.getServis().getDataFronApi(s, "5f6197a2411c35521bae0eb42931c359", "metric")
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            CurrentWeather weather = response.body();
                            binding.textView5.setText(weather.getMain().getTemp().toString());
                            binding.textView8.setText(weather.getMain().getTempMax().toString());
                            binding.textView7.setText(weather.getMain().getTempMin().toString());
                            binding.textView.setText(weather.getName().toString() + " , " + weather.getSys().getCountry().toString());
                            binding.textView20.setText(weather.getSys().getSunrise().toString());
                            binding.textView26.setText(weather.getSys().getSunset().toString());
                            binding.textView15.setText(weather.getWind().getSpeed().toString()+ "m/s"+weather.getWind().getDeg());
                            binding.textView22.setText(weather.getMain().getPressure().toString()+"mb");
                            binding.textView17.setText(weather.getMain().getHumidity().toString());
                            binding.textView24.setText(weather.getClouds().getAll().toString());


                        }

                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        binding.textView.setText(t.getLocalizedMessage());
                    }
                });
    }


}
