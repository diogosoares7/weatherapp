package mangrove.soares.diogo.weatherapp.ui.base;

/**
 * Created by diogo.soares on 22/02/2018.
 */

public class BasePresenter<ViewT> implements IBasePresenter<ViewT> {

    protected ViewT view;

    @Override
    public void onViewActive(ViewT view) {
        this.view = view;
    }

    @Override
    public void onViewInactive() {
        view = null;
    }

}