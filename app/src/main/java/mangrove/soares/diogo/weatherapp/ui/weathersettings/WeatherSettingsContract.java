package mangrove.soares.diogo.weatherapp.ui.weathersettings;

import java.util.List;

import mangrove.soares.diogo.weatherapp.ui.base.IBasePresenter;
import mangrove.soares.diogo.weatherapp.util.EnumSettingsType;

/**
 * Created by diogo.soares on 22/02/2018.
 */

public interface WeatherSettingsContract {
    interface View {
        void showSettings(List<EnumSettingsType> settingsTypeList);
    }

    interface Presenter extends IBasePresenter<View> {
        void getSettings();
    }
}
