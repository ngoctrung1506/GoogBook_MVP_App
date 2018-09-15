package giaodien.admin.doan_googbook.screen.home.notification;

import giaodien.admin.mylibrary.base.viper.interfaces.IInteractor;
import giaodien.admin.mylibrary.base.viper.interfaces.IPresenter;
import giaodien.admin.mylibrary.base.viper.interfaces.PresentView;

/**
 * The Notification Contract
 */
interface NotificationContract {

  interface Interactor extends IInteractor<Presenter> {
  }

  interface View extends PresentView<Presenter> {
  }

  interface Presenter extends IPresenter<View, Interactor> {
  }
}



