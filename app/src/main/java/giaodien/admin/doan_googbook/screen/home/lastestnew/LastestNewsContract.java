package giaodien.admin.doan_googbook.screen.home.lastestnew;

import java.util.ArrayList;

import giaodien.admin.doan_googbook.model.Post;
import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.result.ListPostsResponse;
import giaodien.admin.mylibrary.base.viper.interfaces.IInteractor;
import giaodien.admin.mylibrary.base.viper.interfaces.IPresenter;
import giaodien.admin.mylibrary.base.viper.interfaces.PresentView;
import retrofit2.Callback;

/**
 * The LastNews Contract
 */
interface LastestNewsContract {

  interface Interactor extends IInteractor<Presenter> {
    void getLastestNewData(Callback<ListPostsResponse> listPostsResponseCallback);

    void onSave(int userId, int postId, int isSaved, Callback<SimpleResult> callback);
  }

  interface View extends PresentView<Presenter> {
    void setLastestNewData(ArrayList<Post> posts);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void onMoveToUpLoadView();

    void getLastestNewData();

    void moveToDocDetailScreen(int idPost);

    void onActionSave(int i, int idPost, int i1);
  }
}



