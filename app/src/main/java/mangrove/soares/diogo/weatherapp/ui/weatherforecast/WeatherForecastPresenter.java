package mangrove.soares.diogo.weatherapp.ui.weatherforecast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mangrove.soares.diogo.weatherapp.data.DataRepository;
import mangrove.soares.diogo.weatherapp.data.DataSource;
import mangrove.soares.diogo.weatherapp.data.models.currentweather.CurrentWeather;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.FiveDaysWeather;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.ListItem;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.WeatherRow;
import mangrove.soares.diogo.weatherapp.ui.base.BasePresenter;
import mangrove.soares.diogo.weatherapp.util.AppConfigurations;
import mangrove.soares.diogo.weatherapp.util.Utils;

/**
 * Created by diogo.soares on 22/02/2018.
 */

public class WeatherForecastPresenter extends BasePresenter<WeatherForecastContract.View> implements WeatherForecastContract.Presenter {

    private List<WeatherRow> weatherRowList = new ArrayList<>();
    private double tempMin;
    private double tempMax;
    private Date weekDay;
    private String icon;
    private int day;
    private int lastDay = 0;
    private boolean firstCheck = true;
    private static int lastTemperatureUnits;
    private static int lastCityLocationId;

    public WeatherForecastPresenter(WeatherForecastContract.View view) {
        this.view = view;
        lastTemperatureUnits = AppConfigurations.getTemperatureUnits().getValue();
    }

    @Override
    public void getWeather() {
        if (AppConfigurations.getTemperatureUnits().getValue() != lastTemperatureUnits
                || AppConfigurations.getCityLocation().getId() != lastCityLocationId) {
            getCurrentWeather();
            getFiveDaysWeather();
        }
    }

    private void getCurrentWeather() {
        if (view == null) {
            return;
        }
        lastTemperatureUnits = AppConfigurations.getTemperatureUnits().getValue();
        lastCityLocationId = AppConfigurations.getCityLocation().getId();
        DataRepository.getInstance().getCurrentWeather(new DataSource.getCurrentWeatherCallback() {
            @Override
            public void onResponse(CurrentWeather currentWeather) {
                if (view != null) {
                    view.showCurrentWeather(currentWeather);
                }
            }

            @Override
            public void onFailure(int statusCode, String statusMessage) {

            }
        });
    }

    private void getFiveDaysWeather() {
        if (view == null) {
            return;
        }
        DataRepository.getInstance().getFiveDaysWeather(new DataSource.getFiveDaysWeatherCallback() {
            @Override
            public void onResponse(FiveDaysWeather fiveDaysWeather) {
                if (view != null) {
                    handleData(fiveDaysWeather);
                }
            }

            @Override
            public void onFailure(int statusCode, String statusMessage) {

            }
        });
    }

    private void handleData(FiveDaysWeather fiveDaysWeather) {
        weatherRowList.clear();
        for (ListItem listItem : fiveDaysWeather.getList()) {

            int today = Utils.getDayOfMonth(new Date());
            day = Utils.getDayOfMonth(Utils.convertStringToDate(listItem.getDtTxt()));

            if (day != today) {
                if (lastDay == 0 || day == lastDay) {
                    compareTemperatures(listItem);
                } else {
                    addTemperaturesToList();
                    resetVars();
                    compareTemperatures(listItem);
                }
                lastDay = day;
            }

        }
        lastDay = 0;
        firstCheck = true;
        addTemperaturesToList();
        view.showFiveDaysWeather(weatherRowList);
    }

    private void compareTemperatures(ListItem listItem) {
        if (firstCheck) {
            firstCheck = false;
            tempMin = listItem.getMain().getTempMin();
            tempMax = listItem.getMain().getTempMax();
            weekDay = Utils.convertStringToDate(listItem.getDtTxt());
            icon = listItem.getWeather().get(0).getIcon();
        } else {
            if (tempMin > listItem.getMain().getTempMin()) {
                tempMin = listItem.getMain().getTempMin();
            }
            if (tempMax < listItem.getMain().getTempMax()) {
                tempMax = listItem.getMain().getTempMax();
            }
        }
    }

    private void addTemperaturesToList() {
        WeatherRow weatherRow = new WeatherRow();
        weatherRow.setTempMin(tempMin);
        weatherRow.setTempMax(tempMax);
        weatherRow.setWeekDay(weekDay);
        weatherRow.setIcon(icon);
        weatherRowList.add(weatherRow);
    }

    private void resetVars() {
        tempMin = 0;
        tempMax = 0;
        firstCheck = true;
    }

}