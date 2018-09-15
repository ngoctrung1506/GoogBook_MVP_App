package giaodien.admin.doan_googbook.screen.home.login;

import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.mylibrary.base.viper.interfaces.IInteractor;
import giaodien.admin.mylibrary.base.viper.interfaces.IPresenter;
import giaodien.admin.mylibrary.base.viper.interfaces.PresentView;
import retrofit2.Callback;

/**
 * The LogIn Contract
 */
interface LogInContract {

  interface Interactor extends IInteractor<Presenter> {
    void login(String account, String password, Callback<SimpleResult> callback);

    void onTest(SimpleResult simpleResult, Callback<SimpleResult> callback);
  }

  interface View extends PresentView<Presenter> {
    void moveToLastestNew(String msg, int userId, String userAvatar, String userBackground);

    void setUpView();
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void moveToSignUp();

    void checkLogin(String account, String password);

    void moveToLastestNew();

    void onTest(SimpleResult simpleResult);
  }
}



