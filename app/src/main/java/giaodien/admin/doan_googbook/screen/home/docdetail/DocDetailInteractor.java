package giaodien.admin.doan_googbook.screen.home.docdetail;

import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.network.NetWorkController;
import giaodien.admin.doan_googbook.result.PostResponse;
import giaodien.admin.mylibrary.base.viper.Interactor;
import retrofit2.Callback;

/**
 * The DocDetail interactor
 */
class DocDetailInteractor extends Interactor<DocDetailContract.Presenter>
    implements DocDetailContract.Interactor {

  DocDetailInteractor(DocDetailContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getDocDetail(int mIdPost, Callback<PostResponse> callback) {
    NetWorkController.getDocDetail(mIdPost, callback);
  }

  @Override
  public void onSave(int userId, int postId, int isSaved, Callback<SimpleResult> callback) {
    NetWorkController.onSave(userId, postId, isSaved, callback);
  }
}
