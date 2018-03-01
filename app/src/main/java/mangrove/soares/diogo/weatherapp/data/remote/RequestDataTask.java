package mangrove.soares.diogo.weatherapp.data.remote;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by diogo.soares on 23/02/2018.
 */

public class RequestDataTask extends AsyncTask<String, Void, String> {

    public interface AsyncResponse {
        void onResponse(String output, int statusCode, String statusMessage);
        void onFailure(String output, int statusCode, String statusMessage);
    }

    public AsyncResponse responseCallback = null;

    public RequestDataTask(AsyncResponse asyncResponse) {
        responseCallback = asyncResponse;
    }

    private int statusCode;
    private String statusMessage;
    private final static String TAG = "request_log";

    /**
     * @params[0] = url
     */
    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String result = null;
        try {
            URL url = new URL(params[0]);
            Log.v(TAG, "URL - " + url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            statusCode = urlConnection.getResponseCode();
            statusMessage = urlConnection.getResponseMessage();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            result = buffer.toString();
            return result;
        } catch (IOException e) {
            Log.e(TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(TAG, "Error closing stream", e);
                }
            }
        }
    }

    @Override
    protected void onPostExecute(String output) {
        super.onPostExecute(output);
        if (output != null) {
            responseCallback.onResponse(output, statusCode, statusMessage);
            Log.v(TAG, "response: " + output + " status code: " + statusCode + " status message: " + statusMessage);
        } else {
            responseCallback.onFailure(output, statusCode, statusMessage);
            Log.v(TAG, "failure: " + output + " status code: " + statusCode + " status message: " + statusMessage);
        }
    }

}

