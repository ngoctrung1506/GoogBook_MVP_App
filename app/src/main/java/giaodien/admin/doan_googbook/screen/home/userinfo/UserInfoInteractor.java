package giaodien.admin.doan_googbook.screen.home.userinfo;

import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.network.NetWorkController;
import giaodien.admin.doan_googbook.result.UserInfo;
import giaodien.admin.mylibrary.base.viper.Interactor;
import retrofit2.Callback;

/**
 * The UserInfo interactor
 */
class UserInfoInteractor extends Interactor<UserInfoContract.Presenter>
    implements UserInfoContract.Interactor {

  UserInfoInteractor(UserInfoContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getUserInfoById(int userId, Callback<UserInfo> callback) {
    NetWorkController.getUserInfoById(userId, callback);
  }

  @Override
  public void onUpdate(int userId, String title, String value, Callback<SimpleResult> callback) {
    NetWorkController.onUpdate(userId, title, value,callback);
  }
}
