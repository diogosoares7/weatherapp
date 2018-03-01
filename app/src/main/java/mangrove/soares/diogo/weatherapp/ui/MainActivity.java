package mangrove.soares.diogo.weatherapp.ui;

import android.os.Bundle;

import mangrove.soares.diogo.weatherapp.R;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.City;
import mangrove.soares.diogo.weatherapp.ui.base.BaseActivity;
import mangrove.soares.diogo.weatherapp.ui.weatherforecast.WeatherForecastFragment;
import mangrove.soares.diogo.weatherapp.util.AppConfigurations;
import mangrove.soares.diogo.weatherapp.util.EnumTemperatureUnits;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            setAppDefaultConfigurations();
            replaceFragment(new WeatherForecastFragment(), R.id.frame_container, false);
        }
    }

    private void setAppDefaultConfigurations() {
        AppConfigurations.setForecastDays(5);
        AppConfigurations.setTemperatureUnits(EnumTemperatureUnits.CELSIUS);
        City defaultCity = new City();
        defaultCity.setName("Distrito do Porto");
        defaultCity.setCountry("PT");
        defaultCity.setId(2735941);
        AppConfigurations.setCityLocation(defaultCity);
    }
}
