package giaodien.admin.doan_googbook.base;

import android.app.Activity;
import android.content.Context;

import giaodien.admin.mylibrary.base.ContainerActivity;

/**
 * Created by Admin on 28/09/2017.
 */

public abstract class GoogBaseActivity extends ContainerActivity {

//  public void showProgress() {
//    DialogUtils.showProgressDialog(this);
//  }
//
//  public void hideProgress() {
//    DialogUtils.dismissProgressDialog();
//  }

  @Override
  public void showAlertDialog(String message) {

  }

  @Override
  public void showProgress() {

  }

  @Override
  public void hideProgress() {

  }

  @Override
  public void onRequestError(String errorCode, String errorMessage) {

  }

  @Override
  public void showErrorAlert(Context context, String string) {

  }

  @Override
  public void showNetworkErrorDialog(Activity activity) {

  }
}
