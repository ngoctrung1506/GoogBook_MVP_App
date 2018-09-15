package giaodien.admin.doan_googbook.screen.home.allbook;

import android.util.Log;

import giaodien.admin.doan_googbook.model.ListItemPost;
import giaodien.admin.doan_googbook.screen.home.docdetail.DocDetailPresenter;
import giaodien.admin.mylibrary.base.viper.Presenter;
import giaodien.admin.mylibrary.base.viper.interfaces.ContainerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * The SeeingAllBook Presenter
 */
public class SeeingAllBookPresenter extends Presenter<SeeingAllBookContract.View, SeeingAllBookContract.Interactor>
    implements SeeingAllBookContract.Presenter {

  private String type;

  public SeeingAllBookPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SeeingAllBookContract.View onCreateView() {
    return SeeingAllBookFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    mView.setUpView(type);
  }

  @Override
  public SeeingAllBookContract.Interactor onCreateInteractor() {
    return new SeeingAllBookInteractor(this);
  }

  public SeeingAllBookPresenter setType(String type){
    this.type = type;
    return this;
  }

  @Override
  public void showAllByType(String type) {
    mInteractor.showAllByType(type, new Callback<ListItemPost>() {
      @Override
      public void onResponse(Call<ListItemPost> call, Response<ListItemPost> response) {
        if (response.isSuccessful() && response.body() != null) {
          if (response.body().getErrrorCode() == 0) {
            mView.createData(response.body().getmItemPosts());
          } else Log.d("error", "errorCode = 1");
        } else Log.d("error", "Body is null");
      }

      @Override
      public void onFailure(Call<ListItemPost> call, Throwable t) {
          Log.d("error" , t.getMessage());
        }
      });
    }

  @Override
  public void showPostById(int postId) {
    new DocDetailPresenter(mContainerView).setIdPost(postId).pushView();
  }
}

