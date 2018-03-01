package mangrove.soares.diogo.weatherapp.ui.information;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mangrove.soares.diogo.weatherapp.R;

/**
 * Created by diogo.soares on 22/02/2018.
 */

public class InformationFragment extends Fragment {

    @BindView(R.id.tvHeader)
    TextView tvHeader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_information, container, false);
        final ScrollView svInfo = (ScrollView) rootView.findViewById(R.id.svInfo);
        final View footerLayout1 = (View) rootView.findViewById(R.id.footerLayout1);
        final View footerLayout2 = (View) rootView.findViewById(R.id.footerLayout2);
        ViewTreeObserver observer = svInfo.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int viewHeight = svInfo.getMeasuredHeight();
                int contentHeight = svInfo.getChildAt(0).getHeight();
                if (viewHeight - contentHeight < 0) {
                    footerLayout1.setVisibility(View.VISIBLE);
                    footerLayout2.setVisibility(View.GONE);
                } else {
                    footerLayout2.setVisibility(View.VISIBLE);
                    footerLayout1.setVisibility(View.GONE);
                }
            }
        });
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvHeader.setText(getResources().getString(R.string.information_screen));
    }
}