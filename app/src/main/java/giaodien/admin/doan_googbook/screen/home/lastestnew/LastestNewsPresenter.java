package giaodien.admin.doan_googbook.screen.home.lastestnew;

import android.util.Log;
import android.widget.Toast;

import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.result.ListPostsResponse;
import giaodien.admin.doan_googbook.screen.home.docdetail.DocDetailPresenter;
import giaodien.admin.doan_googbook.screen.home.upload.UploadPresenter;
import giaodien.admin.mylibrary.base.viper.Presenter;
import giaodien.admin.mylibrary.base.viper.interfaces.ContainerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The LastNews Presenter
 */
public class LastestNewsPresenter extends Presenter<LastestNewsContract.View, LastestNewsContract.Interactor>
    implements LastestNewsContract.Presenter {

  public LastestNewsPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public LastestNewsContract.View onCreateView() {
    return LastestNewsFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public LastestNewsContract.Interactor onCreateInteractor() {
    return new LastestNewsInteractor(this);
  }

  @Override
  public void onMoveToUpLoadView() {
    new UploadPresenter(mContainerView).pushView();
  }

  @Override
  public void getLastestNewData() {
    mInteractor.getLastestNewData(new Callback<ListPostsResponse>() {
      @Override
      public void onResponse(Call<ListPostsResponse> call, Response<ListPostsResponse> response) {
        if (response.isSuccessful() && response.body() != null){
          if(response.body().getErrrorCode() == 0){
            mView.setLastestNewData(response.body().getmListPosts());
          }
          else mView.showAlertDialog("Fail");
        }
        else Log.d("error", "Body is null");
      }

      @Override
      public void onFailure(Call<ListPostsResponse> call, Throwable t) {
          Log.d("error", t.toString());
      }
    });
  }

  @Override
  public void moveToDocDetailScreen(int idPost) {
    new DocDetailPresenter(mContainerView).setIdPost(idPost).pushView();
  }

  @Override
  public void onActionSave(int userId, int postId, int isSaved) {
    mInteractor.onSave(userId, postId, isSaved, new Callback<SimpleResult>() {
      @Override
      public void onResponse(Call<SimpleResult> call, Response<SimpleResult> response) {
             if(response.isSuccessful() && response.body() != null)
               Toast.makeText(getViewContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
        else  Toast.makeText(getViewContext(), "Fail", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onFailure(Call<SimpleResult> call, Throwable t) {
           Log.d("error", t.getMessage());
      }
    });
  }
}
