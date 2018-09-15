package giaodien.admin.doan_googbook.screen.home.bookstore;

import giaodien.admin.doan_googbook.model.ListOptions;
import giaodien.admin.doan_googbook.network.NetWorkController;
import giaodien.admin.doan_googbook.result.ListBooksResponse;
import giaodien.admin.mylibrary.base.viper.Interactor;
import retrofit2.Callback;


/**
 * The BookStore interactor
 */
class BookStoreInteractor extends Interactor<BookStoreContract.Presenter>
    implements BookStoreContract.Interactor {

  BookStoreInteractor(BookStoreContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void getDataForList(Callback<ListBooksResponse> listItemPostCallback) {
    NetWorkController.getDataForMainSrcreen(listItemPostCallback);
  }

  @Override
  public void sendConditionToServer(ListOptions listCondition, Callback<ListOptions> callback) {
    NetWorkController.getAllPostByCondition(listCondition, callback);
  }
}
