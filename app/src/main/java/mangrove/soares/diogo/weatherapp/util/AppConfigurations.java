package mangrove.soares.diogo.weatherapp.util;

import java.util.List;

import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.City;

/**
 * Created by diogo.soares on 22/02/2018.
 */

public class AppConfigurations {

    public static int forecastDays;
    public static EnumTemperatureUnits temperatureUnits;
    public static List<City> cityList;
    public static City cityLocation;


    public static int getForecastDays() {
        return forecastDays;
    }

    public static void setForecastDays(int forecastDays) {
        AppConfigurations.forecastDays = forecastDays;
    }

    public static EnumTemperatureUnits getTemperatureUnits() {
        return temperatureUnits;
    }

    public static void setTemperatureUnits(EnumTemperatureUnits temperatureUnits) {
        AppConfigurations.temperatureUnits = temperatureUnits;
    }

    public static List<City> getCityList() {
        return cityList;
    }

    public static void setCityList(List<City> cityList) {
        AppConfigurations.cityList = cityList;
    }

    public static City getCityLocation() {
        return cityLocation;
    }

    public static void setCityLocation(City cityLocation) {
        AppConfigurations.cityLocation = cityLocation;
    }

}
