package giaodien.admin.doan_googbook.screen.home.upload;

import android.util.Log;
import android.widget.Toast;

import giaodien.admin.doan_googbook.model.Post;
import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.mylibrary.base.viper.Presenter;
import giaodien.admin.mylibrary.base.viper.interfaces.ContainerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The Upload Presenter
 */
public class UploadPresenter extends Presenter<UploadContract.View, UploadContract.Interactor>
    implements UploadContract.Presenter {

  private Post mPost;
  public UploadPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public UploadContract.View onCreateView() {
    return UploadFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    if(mPost != null) mView.setUpView(mPost);
  }

  @Override
  public UploadContract.Interactor onCreateInteractor() {
    return new UploadInteractor(this);
  }

  public UploadPresenter getPostInfomation(Post post) {
    this.mPost = post;
    return this;
  }

  @Override
  public void uploadDoc(int userId, String docSubject, String docName, String docType, String docDes, String docLevel, String docUniversity, String docYear, String listImageToUpload, String fileList) {

    mInteractor.uploadDoc(userId, docSubject, docName, docType, docDes, docLevel, docUniversity, docYear, listImageToUpload, fileList, new Callback<SimpleResult>() {
      @Override
      public void onResponse(Call<SimpleResult> call, Response<SimpleResult> response) {

        if(response.isSuccessful() && response.body() != null){
          if(response.body().getErrrorCode() == 0) {
            mView.clearView(response.body().getMsg());
            Log.d("view", "true");
            Toast.makeText(getViewContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
          }
          else Toast.makeText(getViewContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();

        }
      }

      @Override
      public void onFailure(Call<SimpleResult> call, Throwable t) {
      if(t.getMessage()!=null) Log.d("error", t.getMessage());
      }
    });
  }
}
