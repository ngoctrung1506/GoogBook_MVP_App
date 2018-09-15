package giaodien.admin.doan_googbook.screen.home;

import android.support.v4.app.Fragment;

import giaodien.admin.doan_googbook.screen.home.bookstore.BookStorePresenter;
import giaodien.admin.doan_googbook.screen.home.lastestnew.LastestNewsPresenter;
import giaodien.admin.doan_googbook.screen.home.notification.NotificationPresenter;
import giaodien.admin.doan_googbook.screen.home.righttoggle.RightTogglePresenter;
import giaodien.admin.mylibrary.base.viper.Presenter;
import giaodien.admin.mylibrary.base.viper.interfaces.ContainerView;

/**
 * The Home Presenter
 */
public class HomePresenter extends Presenter<HomeContract.View, HomeContract.Interactor>
    implements HomeContract.Presenter {

  public HomePresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public HomeContract.View onCreateView() {
    return HomeFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
    mView.setUpView();
  }

  @Override
  public HomeContract.Interactor onCreateInteractor() {
    return new HomeInteractor(this);
  }

  @Override
  public Fragment changeFragment(int position){
    Presenter presenter;
    switch (position){
      case 0:
        presenter = new LastestNewsPresenter(mContainerView);
        break;
      case 1:
        presenter = new NotificationPresenter(mContainerView);
        break;
      case 2:
        presenter = new BookStorePresenter(mContainerView);
        break;
      case 3:
        presenter = new RightTogglePresenter(mContainerView);
        break;
      default:
        presenter = new LastestNewsPresenter(mContainerView);
    }

    return presenter.getFragment();

  }
}
