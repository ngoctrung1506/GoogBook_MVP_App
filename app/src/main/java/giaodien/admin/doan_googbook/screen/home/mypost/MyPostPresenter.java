package giaodien.admin.doan_googbook.screen.home.mypost;

import android.util.Log;
import android.widget.Toast;

import giaodien.admin.doan_googbook.result.MyPostResponse;
import giaodien.admin.doan_googbook.screen.home.docdetail.DocDetailPresenter;
import giaodien.admin.mylibrary.base.viper.Presenter;
import giaodien.admin.mylibrary.base.viper.interfaces.ContainerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The MyPost Presenter
 */
public class MyPostPresenter extends Presenter<MyPostContract.View, MyPostContract.Interactor>
    implements MyPostContract.Presenter {

  private String type;

  public MyPostPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public MyPostContract.View onCreateView() {
    return MyPostFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    mView.showViewByType(type);
  }

  @Override
  public MyPostContract.Interactor onCreateInteractor() {
    return new MyPostInteractor(this);
  }

  @Override
  public void getAllMyPost(int userId) {
    mInteractor.getAllMyPost(userId, new Callback<MyPostResponse>() {
      @Override
      public void onResponse(Call<MyPostResponse> call, Response<MyPostResponse> response) {
        if(response.isSuccessful() && response.body() != null){
          if(response.body().getErrrorCode() == 0){
            mView.setUpView(response.body().getMyPostList());
//            Log.d("error", response.body().getMyPostList().get(0).getmDocLevel());
          }
         else Log.d("error", "error code = 1");
        }
        else Log.d("error", "Body is null !");

      }

      @Override
      public void onFailure(Call<MyPostResponse> call, Throwable t) {
        Log.d("error", t.getMessage());
      }
    });
  }

  @Override
  public void moveToDocDetail(int idPost) {
    new DocDetailPresenter(mContainerView).setIdPost(idPost).pushView();
  }

  @Override
  public void getAllSavedPost(int userId) {
    mInteractor.getAllMySavedPost(userId, new Callback<MyPostResponse>() {
      @Override
      public void onResponse(Call<MyPostResponse> call, Response<MyPostResponse> response) {
        if(response.isSuccessful() && response.body() != null){
          if(response.body().getErrrorCode() == 0){
            mView.setUpView(response.body().getMyPostList());
          }
          else Toast.makeText(getViewContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getViewContext(), "Error", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onFailure(Call<MyPostResponse> call, Throwable t) {
        Log.d("error", t.getMessage());
      }
    });
  }

  public MyPostPresenter setType(String type){
    this.type = type;
    return this;
  }
}
