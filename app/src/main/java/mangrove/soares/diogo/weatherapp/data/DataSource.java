package mangrove.soares.diogo.weatherapp.data;

import android.content.Context;

import java.util.List;

import mangrove.soares.diogo.weatherapp.data.models.currentweather.CurrentWeather;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.City;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.FiveDaysWeather;

/**
 * Created by diogo.soares on 24/02/2018.
 */

public abstract class DataSource {

    public interface getCurrentWeatherCallback {
        void onResponse(CurrentWeather currentWeather);
        void onFailure(int statusCode, String statusMessage);
    }

    public interface getFiveDaysWeatherCallback {
        void onResponse(FiveDaysWeather fiveDaysWeather);
        void onFailure(int statusCode, String statusMessage);
    }

    public interface getCityListCallback {
        void onResponse(List<City> cityList);
        void onFailure(int statusCode, String statusMessage);
    }

    public abstract void getCurrentWeatherByLocationId(final getCurrentWeatherCallback callback);

    public abstract void getFiveDaysWeatherByLocationId(final getFiveDaysWeatherCallback callback);

    public abstract void getCityList(Context ctx, final getCityListCallback callback);

}
