package giaodien.admin.doan_googbook.screen.home;

import android.util.Log;

import giaodien.admin.doan_googbook.base.GoogBaseActivity;
import giaodien.admin.doan_googbook.constants.Constants;
import giaodien.admin.doan_googbook.screen.home.login.LogInPresenter;
import giaodien.admin.doan_googbook.utils.SharedPreferUtils;
import giaodien.admin.mylibrary.base.viper.ViewFragment;

public class HomeActivity extends GoogBaseActivity {

  @Override
  public ViewFragment onCreateFirstFragment() {
//    loginEditor.putString(Constants.IS_LOGED_IN, "");
//    SharedPreferUtils.putString(getViewContext(), Constants.IS_LOGED_IN, "no");
    String isLogined = SharedPreferUtils.getString(getViewContext(), Constants.IS_LOGED_IN);
    Log.d("login", isLogined);
    if(isLogined.equals("yes")) {
      Log.d("login", isLogined);
      return (ViewFragment) new HomePresenter(this).getFragment();
    }
      else
    return (ViewFragment) new LogInPresenter(this).getFragment();
  }


}
