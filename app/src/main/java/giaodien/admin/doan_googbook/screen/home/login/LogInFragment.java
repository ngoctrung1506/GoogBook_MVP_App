package giaodien.admin.doan_googbook.screen.home.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.constants.Constants;
import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.utils.NetworkUtils;
import giaodien.admin.mylibrary.base.viper.ViewFragment;

/**
 * The LogIn Fragment
 */
public class LogInFragment extends ViewFragment<LogInContract.Presenter> implements LogInContract.View {

  @BindView(R.id.log_in_edt_user_name)
  EditText mUserName;

  @BindView(R.id.log_in_edt_password)
  EditText mPassWord;

  @BindView(R.id.log_in_btn_login)
  Button mBtnLogin;

  @BindView(R.id.log_in_cbx_remember)
  CheckBox mCbxRemember;

  @BindView(R.id.log_in_btn_sign_up)
  TextView mBtnSignUp;

  @BindView(R.id.log_in_btn_forget_pass)
  TextView mBtnForgetPass;

  private SharedPreferences loginSharedPreferences;
  private SharedPreferences.Editor loginEditor;
  public boolean isSaved = false;
  public boolean isLogedIn = false;

  public static LogInFragment getInstance() {
    return new LogInFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_log_in;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mUserName.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if(s.length() > 0 && mPassWord.getText().length() > 0) mBtnLogin.setEnabled(true);
        else mBtnLogin.setEnabled(false);
      }
    });

    mPassWord.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if(s.length() > 0 && mUserName.getText().length() > 0) mBtnLogin.setEnabled(true);
        else mBtnLogin.setEnabled(false);
      }
    });

    mBtnSignUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.moveToSignUp();
      }
    });

    mBtnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        login();
      }
    });
    checkLogin();
    final SimpleResult simpleResult = new SimpleResult();
    simpleResult.setErrrorCode(1);
    simpleResult.setMsg("AAAAAAAAAAAAAAa");
    simpleResult.setUserId(1);

    mBtnForgetPass.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.onTest(simpleResult);
      }
    });


  }

  public void login() {
    if(NetworkUtils.isConnect(getContext())){
      mPresenter.checkLogin(mUserName.getText().toString(), mPassWord.getText().toString());

    }
    else Toast.makeText(getContext(), getString(R.string.app_no_intenet_connection), Toast.LENGTH_SHORT).show();
  }

  public void checkLogin() {
    loginSharedPreferences = getViewContext().getSharedPreferences("loginSharedPreferences", Context.MODE_PRIVATE);
    loginEditor = loginSharedPreferences.edit();
    isSaved = loginSharedPreferences.getBoolean(Constants.SAVED , false);
    if(isSaved) fillData();
    else clearData();
  }

  public void fillData(){
    String userName = loginSharedPreferences.getString(Constants.USER_ACCOUNT, "");
    String userPassWord = loginSharedPreferences.getString(Constants.USER_PASSWORD, "");
    mUserName.setText(userName);
    mPassWord.setText(userPassWord);
    mCbxRemember.setChecked(isSaved);
  }

  public void clearData() {
    mUserName.setText("");
    mPassWord.setText("");
  }

  public boolean checkSaveOption(String account, String password) {
       if(mCbxRemember.isChecked()){
          isSaved = true;
          saveData(account, password);
       }
       else {
         loginEditor.putString(Constants.USER_ACCOUNT, "");
         loginEditor.putString(Constants.USER_PASSWORD, "");
         loginEditor.commit();
         isSaved = false;
       }
       return isSaved;
  }

  public void saveData(String account, String password) {
         loginEditor.putString(Constants.USER_ACCOUNT, account);
         loginEditor.putString(Constants.USER_PASSWORD, password);
         loginEditor.putBoolean(Constants.SAVED, isSaved);
         loginEditor.commit();
  }

  @Override
  public void moveToLastestNew(String msg, final int userId, final String userAvatar, final String userBackground) {
    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    new Thread(new Runnable() {
      @Override
      public void run() {
        checkSaveOption(mUserName.getText().toString(), mPassWord.getText().toString());
        loginEditor.putString(Constants.USER_AVATAR, userAvatar);
        loginEditor.putString(Constants.USER_BACKGROUND, userBackground);
        loginEditor.putInt(Constants.USER_ID, userId);
        loginEditor.putString(Constants.IS_LOGED_IN, "yes");
        loginEditor.commit();
      }
    }).start();

    mPresenter.moveToLastestNew();
  }

  @Override
  public void setUpView() {
  }
}
