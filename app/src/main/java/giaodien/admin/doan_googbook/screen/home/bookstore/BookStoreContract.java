package giaodien.admin.doan_googbook.screen.home.bookstore;

import java.util.ArrayList;

import giaodien.admin.doan_googbook.model.ItemPost;
import giaodien.admin.doan_googbook.model.ListItemPost;
import giaodien.admin.doan_googbook.model.ListOptions;
import giaodien.admin.doan_googbook.result.ListBooksResponse;
import giaodien.admin.mylibrary.base.viper.interfaces.IInteractor;
import giaodien.admin.mylibrary.base.viper.interfaces.IPresenter;
import giaodien.admin.mylibrary.base.viper.interfaces.PresentView;
import retrofit2.Callback;

/**
 * The BookStore Contract
 */
interface BookStoreContract {

  interface Interactor extends IInteractor<Presenter> {
    void getDataForList(Callback<ListBooksResponse> listItemPostCallback);

    void sendConditionToServer(ListOptions listCondition, Callback<ListOptions> callback);
  }

  interface View extends PresentView<Presenter> {
    void setDataForMainScreen(ArrayList<ListItemPost>  listItemPosts);

    void setUpViewForChoosenOption(ArrayList<ItemPost> itemPosts);
  }

  interface Presenter extends IPresenter<View, Interactor> {
    void move(String type);

    void getDataForList();

    void showByPostId(int postId);

    void sendConditionToServer(ListOptions listCondition);
  }
}



