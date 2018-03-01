package mangrove.soares.diogo.weatherapp.data.remote;

import java.util.Locale;

import mangrove.soares.diogo.weatherapp.util.AppConfigurations;
import mangrove.soares.diogo.weatherapp.util.EnumTemperatureUnits;

/**
 * Created by diogo.soares on 23/02/2018.
 */

public class EndPointBuilder {


    private static final String HTTPS = "https://";
    private static final String API_BASE_URL = "api.openweathermap.org";
    private static final String API_DATA_VERSION = "/data/2.5/";
    private static final String API_KEY = "APPID=a72678e6988ca8d6dc4048fb5d574435";
    private static final String CURRENT_WEATHER_ENDPOINT = "weather?";
    private static final String FIVE_DAYS_ENDPOINT = "forecast?";
    private static final String QUERY_PARAM_ID = "id=";
    private static final String QUERY_PARAM_UNITS_FAHRENHEIT = "units=imperial";
    private static final String QUERY_PARAM_UNITS_CELSIUS = "units=metric";
    private static final String QUERY_PARAM_LANGUAGE_PT = "lang=pt";
    private static final String QUERY_PARAM_LANGUAGE_EN = "lang=en";
    private static final String AND = "&";

    protected static String getEndpointCurrentWeather(String cityId){
        StringBuilder url = new StringBuilder();
        url.append(HTTPS);
        url.append(API_BASE_URL);
        url.append(API_DATA_VERSION);
        url.append(CURRENT_WEATHER_ENDPOINT);
        url.append(QUERY_PARAM_ID);
        url.append(cityId);
        url.append(getTemperatureUnitsQueryParam());
        url.append(getLanguageQueryParam());
        url.append(AND);
        url.append(API_KEY);
        return url.toString();
    }

    protected static String getEndpointFiveDaysWeather(String cityId){
        StringBuilder url = new StringBuilder();
        url.append(HTTPS);
        url.append(API_BASE_URL);
        url.append(API_DATA_VERSION);
        url.append(FIVE_DAYS_ENDPOINT);
        url.append(QUERY_PARAM_ID);
        url.append(cityId);
        url.append(getTemperatureUnitsQueryParam());
        url.append(getLanguageQueryParam());
        url.append(AND);
        url.append(API_KEY);
        return url.toString();
    }

    private static String getTemperatureUnitsQueryParam(){
        if(EnumTemperatureUnits.FAHRENHEIT.equals(AppConfigurations.getTemperatureUnits())){
            return AND + QUERY_PARAM_UNITS_FAHRENHEIT;
        }else if(EnumTemperatureUnits.CELSIUS.equals(AppConfigurations.getTemperatureUnits())) {
            return AND + QUERY_PARAM_UNITS_CELSIUS;
        }
        else{
            return "";
        }
    }

    private static String getLanguageQueryParam(){
        String s = Locale.getDefault().getLanguage();
        if(Locale.getDefault().getLanguage().equals("en")){
            return AND + QUERY_PARAM_LANGUAGE_EN;
        }else if(Locale.getDefault().getLanguage().equals("pt")){
            return AND + QUERY_PARAM_LANGUAGE_PT;
        }else {
            return AND + QUERY_PARAM_LANGUAGE_EN;
        }
    }

}