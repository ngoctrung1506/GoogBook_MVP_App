package giaodien.admin.doan_googbook.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.model.ListOptions;
import giaodien.admin.doan_googbook.model.Option;

/**
 * Created by Admin on 03/11/2017.
 */

public class DiologFilter extends Dialog {

  @BindView(R.id.filter_spinner_source)
  Spinner mSpSource;

  @BindView(R.id.filter_spinner_doc_level)
  Spinner mSpLevel;

  @BindView(R.id.filter_spinner_doc_type)
  Spinner mSpType;

  @BindView(R.id.filter_spinner_doc_year)
  Spinner mSpYear;

  @BindView(R.id.filter_edt_doc_name)
  EditText mDocName;

  @BindView(R.id.filter_edt_user_name)
  EditText mUserName;

  @BindView(R.id.filter_edt_doc_subject)
  EditText mDocSubject;

  @BindView(R.id.filter_edt_doc_university)
  EditText mDocUniversity;

  @BindView(R.id.filter_btn_find)
  Button mBtnFind;

  @BindView(R.id.filter_row_university)
  TableRow mRowUniversity;

  @BindView(R.id.filter_row_year)
  TableRow mRowYear;

  private Context mContext;
  private OnFilterListener mOnFilterListener;
  private List<String> listSource;
  private List<String> listLevel;
  private List<String> listType;
  private List<String> listYear;

  public DiologFilter(@NonNull Context context) {
    super(context);
    this.mContext = context;
    initView();
  }

  public DiologFilter(Context context, OnFilterListener onFilterListener) {
    super(context);
    this.mContext = context;
    this.mOnFilterListener = onFilterListener;
    initView();
  }

  public void initView() {
//    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.dialog_filter);
     getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    ButterKnife.bind(this);
    mRowUniversity.setVisibility(View.GONE);
    mRowYear.setVisibility(View.GONE);
    mSpLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(mSpLevel.getSelectedItem().toString().equals("Đại học")){
          mRowUniversity.setVisibility(View.VISIBLE);
          mRowYear.setVisibility(View.VISIBLE);
        }
        else{
          mRowUniversity.setVisibility(View.GONE);
          mRowYear.setVisibility(View.GONE);
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
    addData();
  }

  public List<String> createDocType() {
    listType = new ArrayList<>();
    listType.add("Tất cả");
    listType.add("Bài giảng");
    listType.add("Bài thuyết trình");
    listType.add("Bài viết");
    listType.add("Đề thi - Kiểm tra");
    listType.add("Giáo trình");
    listType.add("Luận văn - Đồ án");
    listType.add("Ngân hàng câu hỏi");
    listType.add("Tài liệu");
    listType.add("Sách");
    listType.add("Khác");
    return listType;
  }

  public List<String> creatLevelData() {
    listLevel = new ArrayList<>();
    listLevel.add("Tất cả");
    listLevel.add("Tiểu học");
    listLevel.add("Trung học Cơ sở");
    listLevel.add("Trung học Phổ thông");
    listLevel.add("Đại học");
    listLevel.add("Khác");
    return listLevel;
  }

  public List<String> creatSourceData() {
    listSource = new ArrayList<>();
    listSource.add("Tất cả");
    listSource.add("Bài đăng");
    listSource.add("Nguồn khác");
    return listSource;
  }

  public List<String> creatYearData() {
    listYear = new ArrayList<>();
    listYear.add("Tất cả");
    listYear.add("Năm thứ nhất");
    listYear.add("Năm thứ hai");
    listYear.add("Năm thứ ba");
    listYear.add("Năm thứ tư");
    listYear.add("Năm thứ năm");
    listYear.add("Khác");
    return listYear;
  }

  public void addData() {
    setDataForSpinnner(mSpSource, creatSourceData());
    setDataForSpinnner(mSpLevel, creatLevelData());
    setDataForSpinnner(mSpType, createDocType());
    setDataForSpinnner(mSpYear, creatYearData());
  }

  @OnClick(R.id.filter_btn_find)
  public void getFilterInfo(){
    List<Option> listCondition = new ArrayList<>();
    listCondition.add(new Option("doc_source",mSpSource.getSelectedItem().toString()));
    if(mDocName.getText().length() > 0){
      listCondition.add(new Option("doc_name",mDocName.getText().toString()));
    }
    else  listCondition.add(new Option("doc_name",null));
    if(mUserName.getText().length() > 0){
      listCondition.add(new Option("user_name",mUserName.getText().toString()));
    }
    else  listCondition.add(new Option("user_name",null));

    if(mDocSubject.getText().length() > 0){
      listCondition.add(new Option("doc_subject",mDocSubject.getText().toString()));
    }
    else listCondition.add(new Option("doc_subject",null));

    listCondition.add(new Option("doc_level", mSpLevel.getSelectedItem().toString()));
    listCondition.add(new Option("doc_type",mSpType.getSelectedItem().toString()));

    if(mDocUniversity.isShown() && mDocUniversity.getText().length() > 0){
      listCondition.add(new Option("doc_university",mDocUniversity.getText().toString()));
    }
    else listCondition.add(new Option("doc_university",null));

    if(mSpYear.isShown()) listCondition.add(new Option("doc_year",mSpYear.getSelectedItem().toString()));
    else listCondition.add(new Option("doc_year",null));
    mOnFilterListener.onFilter(new ListOptions(listCondition));
    hide();
  }

  public interface OnFilterListener{
    void onFilter(ListOptions listCondition);
  }

  public void setDataForSpinnner(Spinner spinner, List<String> list){
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, list);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(arrayAdapter);
    spinner.setFitsSystemWindows(true);
  }
}
