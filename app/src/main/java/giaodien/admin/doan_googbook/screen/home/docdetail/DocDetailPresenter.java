package giaodien.admin.doan_googbook.screen.home.docdetail;

import android.util.Log;
import android.widget.Toast;

import giaodien.admin.doan_googbook.model.Post;
import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.result.PostResponse;
import giaodien.admin.doan_googbook.screen.home.upload.UploadPresenter;
import giaodien.admin.mylibrary.base.viper.Presenter;
import giaodien.admin.mylibrary.base.viper.interfaces.ContainerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The DocDetail Presenter
 */
public class DocDetailPresenter extends Presenter<DocDetailContract.View, DocDetailContract.Interactor>
    implements DocDetailContract.Presenter {

  private int mIdPost;

  public DocDetailPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public DocDetailContract.View onCreateView() {
    return DocDetailFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    mView.setUpData(mIdPost);
  }

  @Override
  public DocDetailContract.Interactor onCreateInteractor() {
    return new DocDetailInteractor(this);
  }

  public DocDetailPresenter setIdPost(int idPost) {
    this.mIdPost = idPost;
    return this;
  }

  @Override
  public void sentToEditScreen(Post post) {
    new UploadPresenter(mContainerView).getPostInfomation(post).pushView();
  }

  @Override
  public void getDocDetail(int mIdPost) {
    mInteractor.getDocDetail(mIdPost, new Callback<PostResponse>() {
      @Override
      public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
        if(getFragment() != null && getFragment().isAdded())
        if(response.isSuccessful() && response.body() != null){
          if(response.body().getErrrorCode() == 0){
            mView.setUpView(response.body().getData());
          }
          else mView.showError(response.body().getMsg());
        }
        else Log.d("error", "Body is null");
//          mView.showError(response.body().getMsg());
      }

      @Override
      public void onFailure(Call<PostResponse> call, Throwable t) {
        mView.showError(t.getMessage());
      }
    });
  }

  @Override
  public void onSave(int userId, int postId, int isSaved) {
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
