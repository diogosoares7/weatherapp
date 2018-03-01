package mangrove.soares.diogo.weatherapp.ui.base;

/**
 * Created by diogo.soares on 22/02/2018.
 */


public interface IBasePresenter<ViewT> {

    void onViewActive(ViewT view);
    void onViewInactive();

}