package giaodien.admin.doan_googbook.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import giaodien.admin.doan_googbook.R;

/**
 * Created by Admin on 28/09/2017.
 */

public class CustomHeaderView extends LinearLayout{

  @BindView(R.id.custom_header_view_img_left_toggle)
  ImageView mImgLeftToggle;

  @BindView(R.id.custom_header_view_img_search)
  ImageView mImgSearch;

  @BindView(R.id.custom_header_view_edt_input)
  EditText mEdtInput;

  @BindView(R.id.custom_header_view_txt_tittle)
  TextView mTxtTittle;

  @BindView(R.id.custom_header_view_root_view)
  CardView mRootView;

  public CustomHeaderView(Context context) {
    super(context);
    init(null, 0);
  }

  public CustomHeaderView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(attrs, 0);
  }

  public CustomHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(attrs, defStyleAttr);
  }

  public void init(AttributeSet attributeSet, int defStyle){

    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.layout_custom_header_view, this, true);

    ButterKnife.bind(this);

    final TypedArray a = getContext().obtainStyledAttributes(
        attributeSet, R.styleable.CustomHeaderView, defStyle, 0);

    if(a.hasValue(R.styleable.CustomHeaderView_leftToggle)){
      mImgLeftToggle.setVisibility(VISIBLE);
      mImgLeftToggle.setImageResource(a.getResourceId(R.styleable.CustomHeaderView_leftToggle, defStyle));
    }
    else mImgLeftToggle.setVisibility(GONE);

    if(a.hasValue(R.styleable.CustomHeaderView_title)){
      mEdtInput.setVisibility(GONE);
      mImgSearch.setVisibility(GONE);
      mTxtTittle.setVisibility(VISIBLE);
      mTxtTittle.setText(a.getText(R.styleable.CustomHeaderView_title));
    }
    else mTxtTittle.setVisibility(GONE);

    if(a.hasValue(R.styleable.CustomHeaderView_bgColor)){
      mRootView.setCardBackgroundColor(a.getColor(R.styleable.CustomHeaderView_bgColor, defStyle));
    }

    a.recycle();

  }

  public void setOnSearchListener(OnClickListener onSearchListener){
    mImgSearch.setOnClickListener(onSearchListener);
  }

  public void setOnLeftToggleListener(OnClickListener onLeftBtnListener){
    mImgLeftToggle.setOnClickListener(onLeftBtnListener);
  }

  public EditText getmEdtInput() {
    return mEdtInput;
  }

  public TextView getmTxtTittle() {
    return mTxtTittle;
  }

  public void setText(String s){
    mTxtTittle.setText(s);
  }
}
