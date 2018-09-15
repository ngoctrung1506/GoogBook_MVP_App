package giaodien.admin.doan_googbook.screen.home.signup;

import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.mylibrary.base.viper.interfaces.IInteractor;
import giaodien.admin.mylibrary.base.viper.interfaces.IPresenter;
import giaodien.admin.mylibrary.base.viper.interfaces.PresentView;
import retrofit2.Callback;

/**
 * The SignUp Contract
 */
interface SignUpContract {

  interface Interactor extends IInteractor<Presenter> {
    void signUp(String account, String password, String userName, String email, String phone, String address, String birthDay, String sex, String mImageBase64Avatar, String mImageBase64BackGround, Callback<SimpleResult> callback);
  }

  interface View extends PresentView<Presenter> {
    void moveToLogIn(String msg, int userId);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void onMoveToLogin();

    void signUp(String edtText, String edtText1, String edtText2, String edtText3, String edtText4, String edtText5, String edtText6, String spinnerChoice, String mImageBase64Avatar, String mImageBase64BackGround);
  }
}



