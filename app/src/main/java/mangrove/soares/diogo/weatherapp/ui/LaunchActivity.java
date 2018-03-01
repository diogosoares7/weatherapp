package mangrove.soares.diogo.weatherapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import mangrove.soares.diogo.weatherapp.R;
import mangrove.soares.diogo.weatherapp.data.DataRepository;
import mangrove.soares.diogo.weatherapp.data.DataSource;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.City;
import mangrove.soares.diogo.weatherapp.util.AppConfigurations;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        if (savedInstanceState == null) {
            loadData();
        }

    }

    private void loadData() {
        DataRepository.getInstance().getCityList(getApplicationContext(), new DataSource.getCityListCallback() {
            @Override
            public void onResponse(List<City> cityList) {
                AppConfigurations.setCityList(cityList);
                Intent i = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(int statusCode, String statusMessage) {

            }
        });
    }
}