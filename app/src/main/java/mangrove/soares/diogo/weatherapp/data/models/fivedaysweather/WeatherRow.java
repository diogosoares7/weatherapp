package mangrove.soares.diogo.weatherapp.data.models.fivedaysweather;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by diogo.soares on 26/02/2018.
 */

public class WeatherRow {

    @SerializedName("week_day")
    Date weekDay;
    @SerializedName("icon")
    String icon;
    @SerializedName("temp_min")
    double tempMin;
    @SerializedName("temp_max")
    double tempMax;

    public Date getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Date weekDay) {
        this.weekDay = weekDay;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }
}
