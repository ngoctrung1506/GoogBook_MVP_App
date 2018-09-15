package giaodien.admin.doan_googbook.screen.home;

import giaodien.admin.mylibrary.base.viper.Interactor;

/**
 * The Home interactor
 */
class HomeInteractor extends Interactor<HomeContract.Presenter>
    implements HomeContract.Interactor {

  HomeInteractor(HomeContract.Presenter presenter) {
    super(presenter);
  }
}
