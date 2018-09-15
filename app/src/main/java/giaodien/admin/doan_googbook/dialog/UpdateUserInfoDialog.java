package giaodien.admin.doan_googbook.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import giaodien.admin.doan_googbook.R;

/**
 * Created by Admin on 16/11/2017.
 */

public class UpdateUserInfoDialog extends Dialog {

  @BindView(R.id.dialog_update_btn_cancel)
  TextView mBtnCancel;

  @BindView(R.id.dialog_update_btn_update)
  TextView mBtnUpdate;

  @BindView(R.id.dialog_update_title)
  TextView mTxtTitle;

  @BindView(R.id.dialog_update_value)
  TextView mTxtValue;

  private String mTitle;
  private String mValue;
  private Context mContext;
  private int mPosition;
  private OnUpdateListener mOnUpdate;

  public UpdateUserInfoDialog(@NonNull Context context) {
    super(context);
  }

  public UpdateUserInfoDialog(@NonNull Context context, OnUpdateListener mOnUpdate, String title, String value, int postion) {
    super(context);
    this.mContext = context;
    this.mOnUpdate = mOnUpdate;
    this.mTitle = title;
    this.mValue = value;
    this.mPosition = postion;
    initLayout();
  }

  public void initLayout() {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.dialog_update_user_info);
    getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    ButterKnife.bind(this);

    mTxtTitle.setText(mTitle);
    mTxtValue.setText(mValue);
  }

  @OnClick(R.id.dialog_update_btn_cancel)
  public void cancel(){
    dismiss();
  }

  @OnClick(R.id.dialog_update_btn_update)
  public void update(){
    String text = mTxtValue.getText().toString();
    mOnUpdate.onUpdate(mTitle, text, mPosition);
    dismiss();
  }


  public interface OnUpdateListener{
    public void onUpdate(String title, String value, int postion);
  }

}
