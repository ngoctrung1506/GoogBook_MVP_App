package giaodien.admin.doan_googbook.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.adapter.DocListAdapter;

/**
 * Created by Admin on 26/10/2017.
 */

public class LastRowDialog extends Dialog {


  @BindView(R.id.dialog_delele_last_row_true)
  TextView mBtnTrue;

  @BindView(R.id.dialog_delele_last_row_wrong)
  TextView mBtnWrong;

  private Context mContext;
  private DocListAdapter.OnUpLoadDocFile mOnUpLoadDocFile;

  public LastRowDialog(@NonNull Context context, DocListAdapter.OnUpLoadDocFile mOnUpLoadDocFile) {
    super(context);
    this.mContext = context;
    this.mOnUpLoadDocFile = mOnUpLoadDocFile;
    initView();
  }

  public void initView() {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.dialog_delele_last_row);
    getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    ButterKnife.bind(this);
  }

  @OnClick(R.id.dialog_delele_last_row_true)
  public void onTrueEvent(){
    mOnUpLoadDocFile.hideUpLoadDocFileLayout();
    hide();
  }

 @OnClick(R.id.dialog_delele_last_row_wrong)
  public void onWrongEvent(){
    hide();
  }

}
