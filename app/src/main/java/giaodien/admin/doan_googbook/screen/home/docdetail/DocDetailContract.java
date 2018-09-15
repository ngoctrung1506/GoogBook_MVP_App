package giaodien.admin.doan_googbook.screen.home.docdetail;

import giaodien.admin.doan_googbook.model.Post;
import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.result.PostResponse;
import giaodien.admin.mylibrary.base.viper.interfaces.IInteractor;
import giaodien.admin.mylibrary.base.viper.interfaces.IPresenter;
import giaodien.admin.mylibrary.base.viper.interfaces.PresentView;
import retrofit2.Callback;

/**
 * The DocDetail Contract
 */
interface DocDetailContract {

  interface Interactor extends IInteractor<Presenter> {
    void getDocDetail(int mIdPost, Callback<PostResponse> callback);

    void onSave(int userId, int postId, int isSaved, Callback<SimpleResult> callback);
  }

  interface View extends PresentView<Presenter> {
    void setUpData(int mIdPost);

    void setUpView(Post data);

    void showError(String msg);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void sentToEditScreen(Post post);

    void getDocDetail(int mIdPost);

    void onSave(int userId, int postId, int aTrue);
  }
}



