package mangrove.soares.diogo.weatherapp.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.City;

/**
 * Created by diogo.soares on 26/02/2018.
 */

public class Utils {

    public static Date convertStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date convertedDate;
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            convertedDate = null;
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static String convertDateToString(Date date){
        SimpleDateFormat simpleDate = new SimpleDateFormat("EEE MMM dd");
        return simpleDate.format(date);
    }

    public static int getDayOfMonth(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getDayOfWeek(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        return dayFormat.format(calendar.getTime());
    }

    public static List<City> getCityListFromJsonFile(Context ctx) {
        List<City> cityList = new ArrayList<>();
        try {
            InputStream inputStream = ctx.getAssets().open("Citylist.json");
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            Gson gson = new GsonBuilder().create();
            reader.beginArray();
            while (reader.hasNext()) {
                City city = gson.fromJson(reader, City.class);
                cityList.add(city);
            }
            reader.close();
        } catch (UnsupportedEncodingException ex) {
        ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cityList;
    }
}