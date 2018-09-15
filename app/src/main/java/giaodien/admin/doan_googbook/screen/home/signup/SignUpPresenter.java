package giaodien.admin.doan_googbook.screen.home.signup;

import android.util.Log;
import android.widget.Toast;

import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.screen.home.login.LogInPresenter;
import giaodien.admin.mylibrary.base.viper.Presenter;
import giaodien.admin.mylibrary.base.viper.interfaces.ContainerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The SignUp Presenter
 */
public class SignUpPresenter extends Presenter<SignUpContract.View, SignUpContract.Interactor>
    implements SignUpContract.Presenter {

  public SignUpPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SignUpContract.View onCreateView() {
    return SignUpFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public SignUpContract.Interactor onCreateInteractor() {
    return new SignUpInteractor(this);
  }

  @Override
  public void onMoveToLogin() {
    new LogInPresenter(mContainerView).pushView();
  }

  @Override
  public void signUp(String account, String password, String userName, String email, String phone, String address, String birthDay, String sex, String mImageBase64Avatar, String mImageBase64BackGround) {
     mInteractor.signUp(account, password, userName, email, phone, address, birthDay, sex, mImageBase64Avatar, mImageBase64BackGround, new Callback<SimpleResult>() {
       @Override
       public void onResponse(Call<SimpleResult> call, Response<SimpleResult> response) {
          if(response.isSuccessful() && response.body() != null){
            if(response.body().getErrrorCode() == 0)
            mView.moveToLogIn(response.body().getMsg(), response.body().getUserId());
            else Toast.makeText(getViewContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
          }
          else Toast.makeText(getViewContext(), "Error", Toast.LENGTH_SHORT).show();
       }

       @Override
       public void onFailure(Call<SimpleResult> call, Throwable t) {
         Log.d("error", t.getMessage());
       }
     });
  }
}
