package mangrove.soares.diogo.weatherapp.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by diogo.soares on 28/02/2018.
 */

public enum EnumTemperatureUnits {
    FAHRENHEIT(0),
    CELSIUS(1);

    private int value;
    private static Map map = new HashMap<>();

    EnumTemperatureUnits(int value) {
        this.value = value;
    }

    static {
        for (EnumTemperatureUnits temperatureUnits : EnumTemperatureUnits.values()) {
            map.put(temperatureUnits.value, temperatureUnits);
        }
    }

    public static EnumTemperatureUnits valueOf(int temperatureUnits) {
        return (EnumTemperatureUnits) map.get(temperatureUnits);
    }

    public int getValue() {
        return value;
    }
}
