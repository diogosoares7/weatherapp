package mangrove.soares.diogo.weatherapp.ui.weatherforecast;

import java.util.List;

import mangrove.soares.diogo.weatherapp.data.models.currentweather.CurrentWeather;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.WeatherRow;
import mangrove.soares.diogo.weatherapp.ui.base.IBasePresenter;

/**
 * Created by diogo.soares on 22/02/2018.
 */

public interface WeatherForecastContract {

    interface View {
        void showCurrentWeather(CurrentWeather currentWeather);
        void showFiveDaysWeather(List<WeatherRow> weatherRowList);
    }

    interface Presenter extends IBasePresenter<View> {
        void getWeather();
    }

}