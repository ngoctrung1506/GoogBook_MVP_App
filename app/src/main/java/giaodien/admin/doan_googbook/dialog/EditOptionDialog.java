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
 * Created by Admin on 26/10/2017.
 */

public class EditOptionDialog extends Dialog {

  @BindView(R.id.edit_option_btn_delete)
  TextView mBtnDelete;

  @BindView(R.id.edit_option_btn_edit)
  TextView mBtnEdit;

  private Context mContext;
  private OnEditOptionListener mOptionListener;

  public EditOptionDialog(@NonNull Context context, OnEditOptionListener mOptionListener) {
    super(context);
    this.mContext = context;
    this.mOptionListener = mOptionListener;
    initLayout();
  }

  public void initLayout() {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.dialog_edit_option);
    getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.edit_option_btn_edit)
  public void editPost(){
      mOptionListener.editListener("edit");
      hide();
  }

  @OnClick(R.id.edit_option_btn_delete)
  public void deletePost(){
    mOptionListener.editListener("delete");
    hide();
  }

  public interface OnEditOptionListener{

    public void editListener(String action);

  }
}
