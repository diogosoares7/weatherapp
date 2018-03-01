package mangrove.soares.diogo.weatherapp.data.local;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.City;
import mangrove.soares.diogo.weatherapp.util.Utils;

/**
 * Created by diogo.soares on 28/02/2018.
 */

public class RequestLocalDataTask extends AsyncTask<String, Void, List<City>> {

    public interface AsyncResponse {
        void onResponse(List<City> cityList, int statusCode, String statusMessage);
        void onFailure(int statusCode, String statusMessage);
    }

    public AsyncResponse responseCallback = null;
    private Context ctx;
    private int statusCode;
    private String statusMessage;
    private final static String TAG = "request_log";

    public RequestLocalDataTask(Context ctx, AsyncResponse asyncResponse) {
        responseCallback = asyncResponse;
        this.ctx = ctx;
    }

    @Override
    protected List<City> doInBackground(String... params) {
        List<City> cityList = Utils.getCityListFromJsonFile(ctx);
        return cityList;
    }

    @Override
    protected void onPostExecute(List<City> output) {
        super.onPostExecute(output);
        if(output != null){
            responseCallback.onResponse(output, statusCode, statusMessage);
            Log.v(TAG, "response: " + output + " status code: " + statusCode + " status message: " + statusMessage);
        }
        else{
            responseCallback.onFailure(statusCode, statusMessage);
            Log.v(TAG, "failure: " + " status code: " + statusCode + " status message: " + statusMessage);
        }
    }

}