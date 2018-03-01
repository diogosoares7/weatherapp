package mangrove.soares.diogo.weatherapp.ui.weatherforecast;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mangrove.soares.diogo.weatherapp.R;
import mangrove.soares.diogo.weatherapp.data.models.currentweather.CurrentWeather;
import mangrove.soares.diogo.weatherapp.data.models.fivedaysweather.WeatherRow;
import mangrove.soares.diogo.weatherapp.ui.MainActivity;
import mangrove.soares.diogo.weatherapp.ui.information.InformationFragment;
import mangrove.soares.diogo.weatherapp.ui.weathersettings.WeatherSettingsFragment;
import mangrove.soares.diogo.weatherapp.util.Utils;

/**
 * Created by diogo.soares on 22/02/2018.
 */

public class WeatherForecastFragment extends Fragment implements WeatherForecastContract.View {

    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.tvWeather)
    TextView tvWeather;
    @BindView(R.id.tvCurrentTemp)
    TextView tvCurrentTemp;
    @BindView(R.id.tvDay)
    TextView tvDay;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvTempMin)
    TextView tvTempMin;
    @BindView(R.id.tvTempMax)
    TextView tvTempMax;
    @BindView(R.id.ivWeatherIcon)
    ImageView ivWeatherIcon;
    @BindView(R.id.recyclerViewWeather)
    RecyclerView recyclerViewWeather;
    @BindView(R.id.ivSettings)
    ImageView ivSettings;
    @BindView(R.id.ivInfo)
    ImageView ivInfo;

    private WeatherForecastContract.Presenter presenter;
    private CurrentWeather currentWeather;
    private List<WeatherRow> weatherRowList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new WeatherForecastPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_forecast, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (this.currentWeather != null) {
            showCurrentWeather(this.currentWeather);
        }
        if (this.weatherRowList != null) {
            showFiveDaysWeather(this.weatherRowList);
        }
        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(new WeatherSettingsFragment(), R.id.frame_container, true);
            }
        });
        ivInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).replaceFragment(new InformationFragment(), R.id.frame_container, true);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewActive(this);
        presenter.getWeather();
    }

    @Override
    public void onPause() {
        presenter.onViewInactive();
        super.onPause();
    }

    @Override
    public void showCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
        tvCity.setText(currentWeather.getName());
        tvCurrentTemp.setText(String.valueOf(currentWeather.getMain().getTemp() + "º"));
        tvTempMin.setText(String.valueOf(currentWeather.getMain().getTempMin()));
        tvTempMax.setText(String.valueOf(currentWeather.getMain().getTempMax()));
        tvDay.setText(getResources().getString(R.string.today));
        tvDate.setText(Utils.convertDateToString(new Date()));
        tvWeather.setText(currentWeather.getWeather().get(0).getDescription());
        Picasso.with(ivWeatherIcon.getContext())
                .load("http://openweathermap.org/img/w/" + currentWeather.getWeather().get(0).getIcon() + ".png")
                .priority(Picasso.Priority.HIGH)
                .into(ivWeatherIcon);
    }

    @Override
    public void showFiveDaysWeather(List<WeatherRow> weatherRowList) {
        this.weatherRowList = weatherRowList;
        WeatherForecastListAdapter mAdapter = new WeatherForecastListAdapter(getContext(), weatherRowList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewWeather.setLayoutManager(mLayoutManager);
        recyclerViewWeather.setItemAnimator(new DefaultItemAnimator());
        recyclerViewWeather.setAdapter(mAdapter);
    }

}