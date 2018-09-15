package giaodien.admin.doan_googbook.screen.home.lastestnew;

import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.network.NetWorkController;
import giaodien.admin.doan_googbook.result.ListPostsResponse;
import giaodien.admin.mylibrary.base.viper.Interactor;
import retrofit2.Callback;

/**
 * The LastNews interactor
 */
class LastestNewsInteractor extends Interactor<LastestNewsContract.Presenter>
    implements LastestNewsContract.Interactor {

  LastestNewsInteractor(LastestNewsContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getLastestNewData(Callback<ListPostsResponse> listPostsResponseCallback) {
    NetWorkController.getLastestNewData(listPostsResponseCallback);
  }

  @Override
  public void onSave(int userId, int postId, int isSaved, Callback<SimpleResult> callback) {
    NetWorkController.onSave(userId, postId, isSaved, callback);
  }
}
