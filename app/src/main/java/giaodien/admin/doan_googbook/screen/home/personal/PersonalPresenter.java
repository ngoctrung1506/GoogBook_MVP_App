package giaodien.admin.doan_googbook.screen.home.personal;

import giaodien.admin.doan_googbook.screen.home.login.LogInPresenter;
import giaodien.admin.doan_googbook.screen.home.mypost.MyPostPresenter;
import giaodien.admin.doan_googbook.screen.home.userinfo.UserInfoPresenter;
import giaodien.admin.mylibrary.base.viper.Presenter;
import giaodien.admin.mylibrary.base.viper.interfaces.ContainerView;

/**
 * The Personal Presenter
 */
public class PersonalPresenter extends Presenter<PersonalContract.View, PersonalContract.Interactor>
    implements PersonalContract.Presenter {

  public PersonalPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public PersonalContract.View onCreateView() {
    return PersonalFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public PersonalContract.Interactor onCreateInteractor() {
    return new PersonalInteractor(this);
  }

  @Override
  public void showViewByType(String type) {
    new MyPostPresenter(mContainerView).setType(type).pushView();
  }

  @Override
  public void logOut() {
    new LogInPresenter(mContainerView).logined(false).pushView();
  }

  @Override
  public void showUserInfo() {
    new UserInfoPresenter(mContainerView).pushView();
  }
}
