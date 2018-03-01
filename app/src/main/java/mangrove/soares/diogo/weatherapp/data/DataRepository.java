package mangrove.soares.diogo.weatherapp.data;

import android.content.Context;

import java.util.List;

import mangrove.soares.diogo.weatherapp.data.local.LocalDataSource;
import mangrove.soares.diogo.weatherapp.data.models.currentweather.CurrentWeather;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.City;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.FiveDaysWeather;
import mangrove.soares.diogo.weatherapp.data.remote.RemoteDataSource;
import mangrove.soares.diogo.weatherapp.util.EnumSettingsType;

/**
 * Created by diogo.soares on 22/02/2018.
 */

public class DataRepository {

    private static DataRepository dataRepository;

    private DataRepository() {
    }

    public static DataRepository getInstance() {
        if (dataRepository == null) {
            dataRepository = new DataRepository();
        }
        return dataRepository;
    }

    public void getCurrentWeather(final DataSource.getCurrentWeatherCallback callback) {
        RemoteDataSource.getInstance().getCurrentWeatherByLocationId(new DataSource.getCurrentWeatherCallback() {
            @Override
            public void onResponse(CurrentWeather currentWeather) {
                callback.onResponse(currentWeather);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage) {
                callback.onFailure(statusCode, statusMessage);
            }
        });
    }

    public void getFiveDaysWeather(final DataSource.getFiveDaysWeatherCallback callback) {
        RemoteDataSource.getInstance().getFiveDaysWeatherByLocationId(new DataSource.getFiveDaysWeatherCallback() {

            @Override
            public void onResponse(FiveDaysWeather fiveDaysWeather) {
                callback.onResponse(fiveDaysWeather);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage) {
                callback.onFailure(statusCode, statusMessage);
            }
        });
    }

    public void getCityList(Context ctx, final DataSource.getCityListCallback callback) {
        LocalDataSource.getInstance().getCityList(ctx, new DataSource.getCityListCallback() {
            @Override
            public void onResponse(List<City> cityList) {
                callback.onResponse(cityList);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage) {
                callback.onFailure(statusCode, statusMessage);
            }
        });
    }

}
