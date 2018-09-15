package giaodien.admin.mylibrary.base.viper.interfaces;

import android.support.v4.app.FragmentManager;

import giaodien.admin.mylibrary.base.viper.ViewFragment;


/**
 * Container view that place fragments on it
 * Created by neo on 9/15/2016.
 */
public interface ContainerView extends IView {
  ViewFragment onCreateFirstFragment();

  void addView(IView view);

  void pushView(IView view);

  void popView(IView mView);

  void pushView(IView view, int frameId);

  void loadView(IView view, int frameId);

  void replaceView(IView view);

  void loadChildView(IView view, int frameId, FragmentManager childFragmentManager);

  void pushChildView(IView view, int frameId, FragmentManager childFragmentManager);

  void presentView(IView view);

  void back();
}
