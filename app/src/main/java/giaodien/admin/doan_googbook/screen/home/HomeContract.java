package giaodien.admin.doan_googbook.screen.home;

import android.support.v4.app.Fragment;

import giaodien.admin.mylibrary.base.viper.interfaces.IInteractor;
import giaodien.admin.mylibrary.base.viper.interfaces.IPresenter;
import giaodien.admin.mylibrary.base.viper.interfaces.PresentView;

/**
 * The Home Contract
 */
interface HomeContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
    void setUpView();
  }

  interface Presenter extends IPresenter<View, Interactor>{
    Fragment changeFragment(int position);
  }
}



