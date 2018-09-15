package giaodien.admin.doan_googbook.screen.home.notification;

import giaodien.admin.mylibrary.base.viper.ViewFragment;

import giaodien.admin.doan_googbook.R;

/**
 * The Notification Fragment
 */
public class NotificationFragment extends ViewFragment<NotificationContract.Presenter> implements NotificationContract.View {

  public static NotificationFragment getInstance() {
    return new NotificationFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_notification;
  }
}
