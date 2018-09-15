package giaodien.admin.mylibrary.base.viper.interfaces;


import android.app.Activity;

import giaodien.admin.mylibrary.base.BaseActivity;

/**
 * Base View
 * Created by neo on 2/5/2016.
 */
public interface IView<P extends IPresenter> {
  void initLayout();

  BaseActivity getBaseActivity();

  Activity getViewContext();

  void setPresenter(P presenter);

  P getPresenter();
}
