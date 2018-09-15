package giaodien.admin.doan_googbook.screen.home.righttoggle;

import giaodien.admin.doan_googbook.screen.home.personal.PersonalPresenter;
import giaodien.admin.mylibrary.base.viper.Presenter;
import giaodien.admin.mylibrary.base.viper.interfaces.ContainerView;


/**
 * The Personal Presenter
 */
public class RightTogglePresenter extends Presenter<RightToggleContract.View, RightToggleContract.Interactor>
    implements RightToggleContract.Presenter {

  public RightTogglePresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public RightToggleContract.View onCreateView() {
    return RightToggleFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public RightToggleContract.Interactor onCreateInteractor() {
    return new RightToggleInteractor(this);
  }

  @Override
  public void moveToPersonal() {
    new PersonalPresenter(mContainerView).pushView();
  }
}
