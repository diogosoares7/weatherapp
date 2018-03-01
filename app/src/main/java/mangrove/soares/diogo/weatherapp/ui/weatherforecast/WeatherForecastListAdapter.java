package mangrove.soares.diogo.weatherapp.ui.weatherforecast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mangrove.soares.diogo.weatherapp.R;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.WeatherRow;
import mangrove.soares.diogo.weatherapp.util.AppConfigurations;
import mangrove.soares.diogo.weatherapp.util.Utils;

/**
 * Created by diogo.soares on 26/02/2018.
 */

public class WeatherForecastListAdapter extends RecyclerView.Adapter<WeatherForecastListAdapter.MyViewHolder> {

    private List<WeatherRow> weatherRowList;
    private Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvWeekDay, tvForecastTempMin, tvForecastTempMax;
        private ImageView ivWeatherIcon;

        public MyViewHolder(View view) {
            super(view);
            tvWeekDay = (TextView) view.findViewById(R.id.tvWeekDay);
            tvForecastTempMin = (TextView) view.findViewById(R.id.tvForecastTempMin);
            tvForecastTempMax = (TextView) view.findViewById(R.id.tvForecastTempMax);
            ivWeatherIcon = (ImageView) view.findViewById(R.id.ivWeatherIcon);
        }
    }

    public WeatherForecastListAdapter(Context ctx, List<WeatherRow> weatherRowList) {
        this.ctx = ctx;
        this.weatherRowList = weatherRowList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_forecast_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position < weatherRowList.size()) {
            WeatherRow item = weatherRowList.get(position);
            if (item != null) {
                holder.tvForecastTempMax.setText(String.valueOf(item.getTempMax()));
                holder.tvForecastTempMin.setText(String.valueOf(item.getTempMin()));
                if (position == 0)
                    holder.tvWeekDay.setText(ctx.getResources().getString(R.string.tomorrow));
                else
                    holder.tvWeekDay.setText(Utils.getDayOfWeek(item.getWeekDay()));
                Picasso.with(holder.ivWeatherIcon.getContext())
                        .load("http://openweathermap.org/img/w/" + item.getIcon() + ".png")
                        .priority(Picasso.Priority.HIGH)
                        .into(holder.ivWeatherIcon);
            }
        }
    }

    @Override
    public int getItemCount() {
        return AppConfigurations.getForecastDays();
    }

}