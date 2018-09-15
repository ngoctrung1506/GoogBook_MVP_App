package giaodien.admin.doan_googbook.screen.home.personal;

import giaodien.admin.mylibrary.base.viper.interfaces.IInteractor;
import giaodien.admin.mylibrary.base.viper.interfaces.IPresenter;
import giaodien.admin.mylibrary.base.viper.interfaces.PresentView;

/**
 * The Personal Contract
 */
interface PersonalContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void showViewByType(String type);

    void logOut();

    void showUserInfo();
  }
}



