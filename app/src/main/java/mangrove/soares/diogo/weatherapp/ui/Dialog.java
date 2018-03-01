package mangrove.soares.diogo.weatherapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import mangrove.soares.diogo.weatherapp.R;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.City;
import mangrove.soares.diogo.weatherapp.util.AppConfigurations;

/**
 * Created by diogo.soares on 28/02/2018.
 */

public class Dialog {

    public interface DialogClick {
        void onPositiveClick();
    }

    public DialogClick callback = null;

    public Dialog(DialogClick dialogClickCallback) {
        callback = dialogClickCallback;
    }

    private static int position = 0;
    private static List<City> filteredCityList = new ArrayList<>();

    public void show(final Context ctx) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ctx);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog;
        alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);

        final EditText etSearch = dialogView.findViewById(R.id.etSearch);

        final Button btnOk = dialogView.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filteredCityList.size() > 0) {
                    AppConfigurations.setCityLocation(filteredCityList.get(position));
                    callback.onPositiveClick();
                    alertDialog.dismiss();
                }
            }
        });

        final Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        final Spinner spinner = dialogView.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Dialog.position = position;
                btnOk.setClickable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button btnSearch = dialogView.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilteredCities(ctx, etSearch.getText().toString(), spinner, btnOk);
            }
        });

        alertDialog.show();
    }

    private void showFilteredCities(Context ctx, String inputCity, Spinner spinner, Button btnOk) {
        filteredCityList.clear();
        searchCityByName(inputCity);
        if (filteredCityList.size() > 0) {
            String[] array = new String[filteredCityList.size()];
            List<String> cityNames = new ArrayList<String>();
            for (City city : filteredCityList) {
                cityNames.add(city.getName() + " " + city.getCountry());
            }
            cityNames.toArray(array);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_dropdown_item, array);
            spinner.setAdapter(adapter);
            spinner.setVisibility(View.VISIBLE);
            btnOk.setAlpha(1);
            btnOk.setClickable(true);
        }
    }

    private void searchCityByName(String cityName) {
        for (City city : AppConfigurations.getCityList()) {
            if (city.getName().contains(cityName)) {
                filteredCityList.add(city);
            }
        }
    }

}