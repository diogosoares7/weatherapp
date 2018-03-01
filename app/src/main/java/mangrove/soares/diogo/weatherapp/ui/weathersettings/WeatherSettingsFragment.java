package mangrove.soares.diogo.weatherapp.ui.weathersettings;

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
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mangrove.soares.diogo.weatherapp.R;
import mangrove.soares.diogo.weatherapp.util.EnumSettingsType;

/**
 * Created by diogo.soares on 22/02/2018.
 */

public class WeatherSettingsFragment extends Fragment implements WeatherSettingsContract.View {

    @BindView(R.id.recyclerViewSettings)
    RecyclerView recyclerViewSettings;
    @BindView(R.id.tvHeader)
    TextView tvHeader;

    private WeatherSettingsContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new WeatherSettingsPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_settings, container, false);
        ButterKnife.bind(this, rootView);
        presenter.getSettings();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvHeader.setText(getResources().getString(R.string.settings_screen));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewActive(this);
    }

    @Override
    public void onPause() {
        presenter.onViewInactive();
        super.onPause();
    }

    @Override
    public void showSettings(List<EnumSettingsType> settingsTypeList) {
        WeatherSettingsListAdapter mAdapter = new WeatherSettingsListAdapter(getContext(), settingsTypeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewSettings.setLayoutManager(mLayoutManager);
        recyclerViewSettings.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSettings.setAdapter(mAdapter);
    }

}