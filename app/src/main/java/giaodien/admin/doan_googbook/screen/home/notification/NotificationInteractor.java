package giaodien.admin.doan_googbook.screen.home.notification;

import giaodien.admin.mylibrary.base.viper.Interactor;

/**
 * The Notification interactor
 */
class NotificationInteractor extends Interactor<NotificationContract.Presenter>
    implements NotificationContract.Interactor {

  NotificationInteractor(NotificationContract.Presenter presenter) {
    super(presenter);
  }
}
