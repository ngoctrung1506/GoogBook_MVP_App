package giaodien.admin.doan_googbook.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import giaodien.admin.doan_googbook.R;

/**
 * Created by Admin on 11/11/2017.
 */

public class CustomSignUpRow extends LinearLayout {

  @BindView(R.id.custom_sign_up_row_img)
  ImageView mImg;

  @BindView(R.id.custom_sign_up_row_edt)
  EditText mEdt;

  @BindView(R.id.custom_sign_up_row_spinner)
  Spinner mSpinner;

  public CustomSignUpRow(Context context) {
    super(context);
    init(null,0);
  }

  public CustomSignUpRow(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(attrs,0);

  }

  public CustomSignUpRow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(attrs,defStyleAttr);
  }

  public void init(AttributeSet attributeSet, int defStyle){

    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.custom_sign_up_row, this, true);

    ButterKnife.bind(this);

    final TypedArray a = getContext().obtainStyledAttributes(attributeSet, R.styleable.CustomSignUpRow, defStyle, 0);

    if(a.hasValue(R.styleable.CustomSignUpRow_leftImg)){
      mImg.setVisibility(VISIBLE);
      mImg.setImageResource(a.getResourceId(R.styleable.CustomSignUpRow_leftImg, defStyle));
    }
    else mImg.setVisibility(GONE);

    if(a.hasValue(R.styleable.CustomSignUpRow_hint)){
      mEdt.setVisibility(VISIBLE);
      mEdt.setHint(a.getString(R.styleable.CustomSignUpRow_hint));
      mSpinner.setVisibility(GONE);
    }
    else {
      mEdt.setText(getContext().getString(R.string.sign_up_sex));
      mEdt.setFocusable(false);
      mEdt.setClickable(false);
      mEdt.setVisibility(VISIBLE);
      mSpinner.setVisibility(VISIBLE);
    }

    if(a.hasValue(R.styleable.CustomSignUpRow_security)){
      mEdt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    a.recycle();

  }

  public String getEdtText(){
    return mEdt.getText().toString();
  }

  public void setDataForSpinner(List<String> list){
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    mSpinner.setAdapter(arrayAdapter);
    mSpinner.setFitsSystemWindows(true);
  }

  public String getSpinnerChoice(){
    return mSpinner.getSelectedItem().toString();
  }

  public void setTextWatcher(TextWatcher textWatcher){
    mEdt.addTextChangedListener(textWatcher);
  }
}
