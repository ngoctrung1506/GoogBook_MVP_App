package giaodien.admin.doan_googbook.screen.home.mypost;

import java.util.List;

import giaodien.admin.doan_googbook.model.MyPost;
import giaodien.admin.doan_googbook.result.MyPostResponse;
import giaodien.admin.mylibrary.base.viper.interfaces.IInteractor;
import giaodien.admin.mylibrary.base.viper.interfaces.IPresenter;
import giaodien.admin.mylibrary.base.viper.interfaces.PresentView;
import retrofit2.Callback;

/**
 * The MyPost Contract
 */
interface MyPostContract {

  interface Interactor extends IInteractor<Presenter> {
    void getAllMyPost(int userId, Callback<MyPostResponse> callback);

    void getAllMySavedPost(int userId, Callback<MyPostResponse> callback);
  }

  interface View extends PresentView<Presenter> {
    void setUpView(List<MyPost> myPostList);

    void showViewByType(String type);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void getAllMyPost(int userId);

    void moveToDocDetail(int idPost);

    void getAllSavedPost(int anInt);
  }
}



