package giaodien.admin.doan_googbook.screen.home.userinfo;

import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.result.UserInfo;
import giaodien.admin.mylibrary.base.viper.interfaces.IInteractor;
import giaodien.admin.mylibrary.base.viper.interfaces.IPresenter;
import giaodien.admin.mylibrary.base.viper.interfaces.PresentView;
import retrofit2.Callback;

/**
 * The UserInfo Contract
 */
interface UserInfoContract {

  interface Interactor extends IInteractor<Presenter> {
    void getUserInfoById(int userId, Callback<UserInfo> callback);

    void onUpdate(int userId, String title, String value, Callback<SimpleResult> callback);
  }

  interface View extends PresentView<Presenter> {
    void setUpView(int userId);

    void setView(int userId, String password, String address, String email, String name, String phone, String sex, String yearBorn);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void getUserInfo(int userId);

    void onUpdate(int userId, String title, String value);
  }
}



