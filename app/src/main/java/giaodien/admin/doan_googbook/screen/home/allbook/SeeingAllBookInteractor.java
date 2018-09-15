package giaodien.admin.doan_googbook.screen.home.allbook;

import giaodien.admin.doan_googbook.model.ListItemPost;
import giaodien.admin.doan_googbook.network.NetWorkController;
import giaodien.admin.mylibrary.base.viper.Interactor;
import retrofit2.Callback;


/**
 * The SeeingAllBook interactor
 */
class SeeingAllBookInteractor extends Interactor<SeeingAllBookContract.Presenter>
    implements SeeingAllBookContract.Interactor {

  SeeingAllBookInteractor(SeeingAllBookContract.Presenter presenter) {
    super(presenter);
  }

  @Override
  public void showAllByType(String type, Callback<ListItemPost> callback) {
    NetWorkController.showAllByType(type, callback);
  }
}
