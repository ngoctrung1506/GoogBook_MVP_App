package giaodien.admin.doan_googbook.screen.home.userinfo;

import android.util.Log;
import android.widget.Toast;

import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.result.UserInfo;
import giaodien.admin.mylibrary.base.viper.Presenter;
import giaodien.admin.mylibrary.base.viper.interfaces.ContainerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The UserInfo Presenter
 */
public class UserInfoPresenter extends Presenter<UserInfoContract.View, UserInfoContract.Interactor>
    implements UserInfoContract.Presenter {

  private int userId;

  public UserInfoPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public UserInfoContract.View onCreateView() {
    return UserInfoFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    mView.setUpView(userId);
  }

  @Override
  public UserInfoContract.Interactor onCreateInteractor() {
    return new UserInfoInteractor(this);
  }

  @Override
  public void getUserInfo(int userId) {
     mInteractor.getUserInfoById(userId, new Callback<UserInfo>() {
       @Override
       public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
         if(response.isSuccessful() && response.body() != null){
           if(response.body().getErrrorCode() == 0){
             mView.setView(response.body().getUserId(), response.body().getPassword(), response.body().getAddress(), response.body().getEmail(),
                 response.body().getName(), response.body().getPhone(), response.body().getSex(), response.body().getYearBorn());
           }
           else Toast.makeText(getViewContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
         }
         Log.d("error", "Body is null");
       }

       @Override
       public void onFailure(Call<UserInfo> call, Throwable t) {
         Log.d("error", t.getMessage());
       }
     });
  }

  @Override
  public void onUpdate(int userId, String title, String value) {
    mInteractor.onUpdate(1, title, value, new Callback<SimpleResult>() {
      @Override
      public void onResponse(Call call, Response response) {
        if(response.isSuccessful() && response.body() != null){
          Log.d("result", "Đone");
        }
        else Log.d("result", "Body is null");
      }

      @Override
      public void onFailure(Call call, Throwable t) {
         Log.d("result", t.getMessage());
      }
    });
  }

  public UserInfoPresenter setUserId(int userId){
    this.userId = userId;
    return this;
  }
}
