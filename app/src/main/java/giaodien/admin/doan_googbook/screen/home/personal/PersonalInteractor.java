package giaodien.admin.doan_googbook.screen.home.personal;

import giaodien.admin.mylibrary.base.viper.Interactor;

/**
 * The Personal interactor
 */
class PersonalInteractor extends Interactor<PersonalContract.Presenter>
    implements PersonalContract.Interactor {

  PersonalInteractor(PersonalContract.Presenter presenter) {
    super(presenter);
  }
}
