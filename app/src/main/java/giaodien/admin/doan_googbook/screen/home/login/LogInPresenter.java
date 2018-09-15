package giaodien.admin.doan_googbook.screen.home.login;

import android.util.Log;
import android.widget.Toast;

import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.screen.home.HomePresenter;
import giaodien.admin.doan_googbook.screen.home.signup.SignUpPresenter;
import giaodien.admin.mylibrary.base.viper.Presenter;
import giaodien.admin.mylibrary.base.viper.interfaces.ContainerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The LogIn Presenter
 */
public class LogInPresenter extends Presenter<LogInContract.View, LogInContract.Interactor>
    implements LogInContract.Presenter {

  private boolean logined = false;

  public LogInPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public LogInContract.View onCreateView() {
    return LogInFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    }


  @Override
  public LogInContract.Interactor onCreateInteractor() {
    return new LogInInteractor(this);
  }

  @Override
  public void moveToSignUp() {
    new SignUpPresenter(mContainerView).pushView();
  }

  @Override
  public void checkLogin(String account, String password) {
    mInteractor.login(account, password, new Callback<SimpleResult>() {
      @Override
      public void onResponse(Call<SimpleResult> call, Response<SimpleResult> response) {
        if(response.isSuccessful() && response.body() != null){
          if(response.body().getErrrorCode() == 0){
            mView.moveToLastestNew(response.body().getMsg(), response.body().getUserId(), response.body().getUserAvatar(), response.body().getUserBackground());
          }
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

  @Override
  public void moveToLastestNew() {
    new HomePresenter(mContainerView).pushView();
  }

  @Override
  public void onTest(SimpleResult simpleResult) {
    mInteractor.onTest(simpleResult, new Callback<SimpleResult>() {
      @Override
      public void onResponse(Call<SimpleResult> call, Response<SimpleResult> response) {
        if(response.isSuccessful() && response.body()!=null){
          Log.d("msg", response.body().getMsg()+ "AAAAAAAAAAA");
        }
      }

      @Override
      public void onFailure(Call<SimpleResult> call, Throwable t) {
        Log.d("msg",t.getMessage());
      }
    });
  }

  @Override
  public void back() {
    super.back();
    if(logined == false) Toast.makeText(getViewContext(), "Bạn phải đăng nhập", Toast.LENGTH_SHORT).show();
  }

  public LogInPresenter logined(boolean b) {
    this.logined = b;
    return this;
  }
}
