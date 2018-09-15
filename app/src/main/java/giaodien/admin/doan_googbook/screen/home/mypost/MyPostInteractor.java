package giaodien.admin.doan_googbook.screen.home.mypost;

import giaodien.admin.doan_googbook.network.NetWorkController;
import giaodien.admin.doan_googbook.result.MyPostResponse;
import giaodien.admin.mylibrary.base.viper.Interactor;
import retrofit2.Callback;

/**
 * The MyPost interactor
 */
class MyPostInteractor extends Interactor<MyPostContract.Presenter>
    implements MyPostContract.Interactor {

  MyPostInteractor(MyPostContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getAllMyPost(int userId, Callback<MyPostResponse> callback) {
    NetWorkController.getUserPostByUserId(userId, callback);
  }

  @Override
  public void getAllMySavedPost(int userId, Callback<MyPostResponse> callback) {
    NetWorkController.getSavedPostByUserId(userId, callback);

  }
}
