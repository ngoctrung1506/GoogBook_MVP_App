package giaodien.admin.doan_googbook.screen.home.allbook;

import java.util.ArrayList;

import giaodien.admin.doan_googbook.model.ItemPost;
import giaodien.admin.doan_googbook.model.ListItemPost;
import giaodien.admin.mylibrary.base.viper.interfaces.IInteractor;
import giaodien.admin.mylibrary.base.viper.interfaces.IPresenter;
import giaodien.admin.mylibrary.base.viper.interfaces.PresentView;
import retrofit2.Callback;

/**
 * The SeeingAllBook Contract
 */
interface SeeingAllBookContract {

  interface Interactor extends IInteractor<Presenter> {
    void showAllByType(String type, Callback<ListItemPost> callback);
  }

  interface View extends PresentView<Presenter> {
    void setUpView(String type);

    void createData(ArrayList<ItemPost> itemPosts);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void showAllByType(String type);

    void showPostById(int postId);
  }
}



