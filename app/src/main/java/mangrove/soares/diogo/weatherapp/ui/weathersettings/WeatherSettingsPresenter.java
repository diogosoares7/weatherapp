package mangrove.soares.diogo.weatherapp.ui.weathersettings;

import java.util.ArrayList;
import java.util.List;

import mangrove.soares.diogo.weatherapp.data.DataRepository;
import mangrove.soares.diogo.weatherapp.ui.base.BasePresenter;
import mangrove.soares.diogo.weatherapp.util.EnumSettingsType;

/**
 * Created by diogo.soares on 22/02/2018.
 */

public class WeatherSettingsPresenter extends BasePresenter<WeatherSettingsContract.View> implements WeatherSettingsContract.Presenter {

    public WeatherSettingsPresenter(WeatherSettingsContract.View view) {
        this.view = view;
    }

    @Override
    public void getSettings() {
        if (view == null) {
            return;
        }
        view.showSettings(createSettingsList());
    }

    private List<EnumSettingsType> createSettingsList() {
        List<EnumSettingsType> settingsTypeList = new ArrayList<>();
        settingsTypeList.add(EnumSettingsType.LOCATION);
        settingsTypeList.add(EnumSettingsType.TEMPERATURE_UNITS);
        settingsTypeList.add(EnumSettingsType.FORECAST_DAYS);
        return settingsTypeList;
    }

}