package mangrove.soares.diogo.weatherapp.ui.weathersettings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import mangrove.soares.diogo.weatherapp.R;
import mangrove.soares.diogo.weatherapp.ui.Dialog;
import mangrove.soares.diogo.weatherapp.util.AppConfigurations;
import mangrove.soares.diogo.weatherapp.util.EnumSettingsType;
import mangrove.soares.diogo.weatherapp.util.EnumTemperatureUnits;

/**
 * Created by diogo.soares on 27/02/2018.
 */

public class WeatherSettingsListAdapter extends RecyclerView.Adapter<WeatherSettingsListAdapter.MyViewHolder> {

    private List<EnumSettingsType> settingsTypeList;
    private Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSettingsLabel, tvSettingsValue;
        private Spinner spinner;
        private LinearLayout llRoot;

        public MyViewHolder(View view) {
            super(view);
            tvSettingsLabel = (TextView) view.findViewById(R.id.tvSettingsLabel);
            tvSettingsValue = (TextView) view.findViewById(R.id.tvSettingsValue);
            spinner = (Spinner) view.findViewById(R.id.spinner);
            llRoot = (LinearLayout) view.findViewById(R.id.llRoot);
        }

    }

    public WeatherSettingsListAdapter(Context ctx, List<EnumSettingsType> settingsTypeList) {
        this.ctx = ctx;
        this.settingsTypeList = settingsTypeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_settings_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        EnumSettingsType item = settingsTypeList.get(position);
        if (item != null) {
            if (item.equals(EnumSettingsType.LOCATION)) {
                holder.spinner.setVisibility(View.GONE);
                holder.tvSettingsValue.setVisibility(View.VISIBLE);
                holder.tvSettingsValue.setText(AppConfigurations.getCityLocation().getName() + " " + AppConfigurations.getCityLocation().getCountry());
                holder.tvSettingsLabel.setText(ctx.getResources().getString(R.string.location));
                holder.llRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(new Dialog.DialogClick() {
                            @Override
                            public void onPositiveClick() {
                                holder.tvSettingsValue.setText(AppConfigurations.getCityLocation().getName() + " " + AppConfigurations.getCityLocation().getCountry());
                            }
                        });
                        dialog.show(ctx);
                    }
                });
            } else if (item.equals(EnumSettingsType.TEMPERATURE_UNITS)) {
                holder.tvSettingsLabel.setText(ctx.getResources().getString(R.string.temperature_units));
                String[] items = new String[]{"Fahrenheit", "Celsius"};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_dropdown_item, items);
                holder.spinner.setAdapter(adapter);
                holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        EnumTemperatureUnits temperatureUnits = EnumTemperatureUnits.valueOf(position);
                        AppConfigurations.setTemperatureUnits(temperatureUnits);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                holder.spinner.setSelection(AppConfigurations.getTemperatureUnits().getValue());
            } else if (item.equals(EnumSettingsType.FORECAST_DAYS)) {
                holder.tvSettingsLabel.setText(ctx.getResources().getString(R.string.forecast_days));
                String[] items = new String[]{"1", "2", "3", "4", "5"};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_dropdown_item, items);
                holder.spinner.setAdapter(adapter);
                holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        AppConfigurations.setForecastDays(position + 1);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                holder.spinner.setSelection(AppConfigurations.getForecastDays() - 1);
            }

        }
    }

    @Override
    public int getItemCount() {
        return settingsTypeList.size();
    }

}