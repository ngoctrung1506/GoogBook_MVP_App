package giaodien.admin.doan_googbook.screen.home.login;

import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.network.NetWorkController;
import giaodien.admin.mylibrary.base.viper.Interactor;
import retrofit2.Callback;

/**
 * The LogIn interactor
 */
class LogInInteractor extends Interactor<LogInContract.Presenter>
    implements LogInContract.Interactor {

  LogInInteractor(LogInContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void login(String account, String password, Callback<SimpleResult> callback) {
    NetWorkController.login(account, password, callback);
  }

  @Override
  public void onTest(SimpleResult simpleResult, Callback<SimpleResult> callback) {
    NetWorkController.onTest(simpleResult, callback);
  }
}
