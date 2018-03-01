package mangrove.soares.diogo.weatherapp.data.remote;

import android.content.Context;

import com.google.gson.Gson;

import mangrove.soares.diogo.weatherapp.data.DataSource;
import mangrove.soares.diogo.weatherapp.data.models.currentweather.CurrentWeather;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.FiveDaysWeather;
import mangrove.soares.diogo.weatherapp.util.AppConfigurations;

/**
 * Created by diogo.soares on 22/02/2018.
 */

public class RemoteDataSource extends DataSource {

    private static RemoteDataSource remoteDataSource;

    private RemoteDataSource(){
    }

    public static RemoteDataSource getInstance() {
        if (remoteDataSource == null) {
            remoteDataSource = new RemoteDataSource();
        }
        return remoteDataSource;
    }

    @Override
    public void getCurrentWeatherByLocationId(final getCurrentWeatherCallback callback) {

        if(AppConfigurations.getCityLocation() != null){
            new RequestDataTask(new RequestDataTask.AsyncResponse() {
                @Override
                public void onResponse(String output, int statusCode, String statusMessage) {

                    try {
                        CurrentWeather currentWeather = new Gson().fromJson(output,CurrentWeather.class);
                        callback.onResponse(currentWeather);
                    }catch (Exception e){
                        e.printStackTrace();
                        callback.onFailure(statusCode, statusMessage);
                    }

                }
                @Override
                public void onFailure(String output, int statusCode, String statusMessage) {
                    callback.onFailure(statusCode, statusMessage);
                }

            }).execute(EndPointBuilder.getEndpointCurrentWeather(String.valueOf(AppConfigurations.getCityLocation().getId())));
        }

    }

    @Override
    public void getFiveDaysWeatherByLocationId(final getFiveDaysWeatherCallback callback) {

        if(AppConfigurations.getCityLocation() != null) {
            new RequestDataTask(new RequestDataTask.AsyncResponse() {
                @Override
                public void onResponse(String output, int statusCode, String statusMessage) {

                    try {
                        FiveDaysWeather fiveDaysWeather = new Gson().fromJson(output, FiveDaysWeather.class);
                        callback.onResponse(fiveDaysWeather);
                    } catch (Exception e) {
                        e.printStackTrace();
                        callback.onFailure(statusCode, statusMessage);
                    }

                }

                @Override
                public void onFailure(String output, int statusCode, String statusMessage) {
                    callback.onFailure(statusCode, statusMessage);
                }

            }).execute(EndPointBuilder.getEndpointFiveDaysWeather(String.valueOf(AppConfigurations.getCityLocation().getId())));
        }

    }

    @Override
    public void getCityList(Context ctx, getCityListCallback callback) {

    }

}