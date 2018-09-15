package giaodien.admin.doan_googbook.screen.home.signup;

import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.network.NetWorkController;
import giaodien.admin.mylibrary.base.viper.Interactor;
import retrofit2.Callback;

/**
 * The SignUp interactor
 */
class SignUpInteractor extends Interactor<SignUpContract.Presenter>
    implements SignUpContract.Interactor {

  SignUpInteractor(SignUpContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void signUp(String account, String password, String userName, String email, String phone, String address, String birthDay, String sex, String mImageBase64Avatar, String mImageBase64BackGround, Callback<SimpleResult> callback) {
    NetWorkController.signUp(account, password, userName, email, phone, address, birthDay, sex, mImageBase64Avatar, mImageBase64BackGround, callback);
  }
}
