package mangrove.soares.diogo.weatherapp.data.local;

import android.content.Context;

import java.util.List;

import mangrove.soares.diogo.weatherapp.data.DataSource;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.City;

/**
 * Created by diogo.soares on 23/02/2018.
 */

public class LocalDataSource extends DataSource {

    private static LocalDataSource localDataSource;

    private LocalDataSource() {
    }

    public static LocalDataSource getInstance() {
        if (localDataSource == null) {
            localDataSource = new LocalDataSource();
        }
        return localDataSource;
    }

    @Override
    public void getCurrentWeatherByLocationId(getCurrentWeatherCallback callback) {

    }

    @Override
    public void getFiveDaysWeatherByLocationId(getFiveDaysWeatherCallback callback) {

    }

    @Override
    public void getCityList(Context ctx, final getCityListCallback callback) {
        new RequestLocalDataTask(ctx, new RequestLocalDataTask.AsyncResponse() {

            @Override
            public void onResponse(List<City> cityList, int statusCode, String statusMessage) {
                callback.onResponse(cityList);
            }

            @Override
            public void onFailure(int statusCode, String statusMessage) {
                callback.onFailure(statusCode, statusMessage);
            }

        }).execute();
    }

}