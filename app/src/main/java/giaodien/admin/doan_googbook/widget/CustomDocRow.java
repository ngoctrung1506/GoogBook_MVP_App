package giaodien.admin.doan_googbook.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import giaodien.admin.doan_googbook.R;

/**
 * Created by Admin on 10/10/2017.
 */

public class CustomDocRow extends LinearLayout {

  @BindView(R.id.layout_doc_row_txt_title)
  TextView mTitle;

  @BindView(R.id.layout_doc_row_edt_input)
  EditText mEdtInput;

  @BindView(R.id.layout_doc_row_spinner)
  Spinner mSpinner;

  public CustomDocRow(Context context) {
    super(context);
    init(null, 0);
  }

  public CustomDocRow(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(attrs, 0);
  }

  public CustomDocRow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(attrs, defStyleAttr);
  }

  public void init(AttributeSet attrs, int defStyle){

    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.layout_doc_row, this, true);

    ButterKnife.bind(this);

    final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomDocRow, defStyle, 0);

    if(a.hasValue(R.styleable.CustomDocRow_tittle)){
      mTitle.setVisibility(VISIBLE);
      mTitle.setText(a.getText(R.styleable.CustomDocRow_tittle));
    }
    else mTitle.setVisibility(GONE);

    if(a.hasValue(R.styleable.CustomDocRow_edtVisual)){
      if(a.getBoolean(R.styleable.CustomDocRow_edtVisual, true)){
         mEdtInput.setVisibility(VISIBLE);
      }
      else mEdtInput.setVisibility(GONE);
    }

    if(a.hasValue(R.styleable.CustomDocRow_spinnerVisual)){
      if(a.getBoolean(R.styleable.CustomDocRow_spinnerVisual, true)){
         mSpinner.setVisibility(VISIBLE);
      }
      else mSpinner.setVisibility(GONE);
    }

//    MarginLayoutParams marginLayoutParams = new MarginLayoutParams(convertDpToPx(mEdtInput, 15), convertDpToPx(mEdtInput, 15));
//    mEdtInput.setLayoutParams(marginLayoutParams);

    if(a.hasValue(R.styleable.CustomDocRow_margin)){
      if(mEdtInput.isShown()) addMarginsInDp(mEdtInput, a.getDimension(R.styleable.CustomDocRow_margin, defStyle),0, 0 ,0);
      else if(mSpinner.isShown()) addMarginsInDp(mSpinner, a.getDimension(R.styleable.CustomDocRow_margin, defStyle),0, 0 ,0);

      LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//      params.setMargins();
      MarginLayoutParams marginLayoutParams = new MarginLayoutParams(convertDpToPx(mEdtInput, 15), convertDpToPx(mEdtInput, 15));
      mEdtInput.setLayoutParams(marginLayoutParams);
    }

    a.recycle();
  }

  public void addMarginsInDp(View view, float leftInDp, int topInDp, int rightInDp, int bottomInDp) {
    DisplayMetrics dm = view.getResources().getDisplayMetrics();
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    lp.setMargins(convertDpToPx(leftInDp, dm), convertDpToPx(topInDp, dm), convertDpToPx(rightInDp, dm), convertDpToPx(bottomInDp, dm));
    view.setLayoutParams(lp);
  }

  private int convertDpToPx(View view, float dp) {
    DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
    float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
    return Math.round(pixels);
  }

  private int convertDpToPx( float dp, DisplayMetrics displayMetrics) {
    DisplayMetrics dm = getResources().getDisplayMetrics();
    float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
    return Math.round(pixels);
  }

  public void setDataForSpinner(List<String> stringList){
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, stringList);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    mSpinner.setAdapter(arrayAdapter);
    mSpinner.setFitsSystemWindows(true);
  }

  public Spinner getmSpinner() {
    return mSpinner;
  }

  public int getEdtInputLength() {
    return mEdtInput.getText().length();
  }

  public void setTextWatcher(TextWatcher textWatcher){
    mEdtInput.addTextChangedListener(textWatcher);
  }

  public void setText(String text){
    mEdtInput.setText(text);
  }

  public void setSelectionForSpinner(int position){
    mSpinner.setSelection(position);
  }

  public String getEdtText(){
    String result = null;
    if(mEdtInput.getText().length() > 0)
      result = mEdtInput.getText().toString();
    return result;
  }

  public String getSpinnerText(){
    return mSpinner.getSelectedItem().toString();
  }
}
