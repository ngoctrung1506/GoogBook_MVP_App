package giaodien.admin.doan_googbook.screen.home.upload;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.frosquivel.magicalcamera.Utilities.ConvertSimpleImage;
import com.frosquivel.magicalcamera.Utilities.PictureUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.adapter.DocListAdapter;
import giaodien.admin.doan_googbook.adapter.ImageAdapter;
import giaodien.admin.doan_googbook.constants.Constants;
import giaodien.admin.doan_googbook.model.Doc;
import giaodien.admin.doan_googbook.model.FileUpload;
import giaodien.admin.doan_googbook.model.Post;
import giaodien.admin.doan_googbook.utils.RecyclerUtils;
import giaodien.admin.doan_googbook.utils.SharedPreferUtils;
import giaodien.admin.doan_googbook.widget.CustomDocRow;
import giaodien.admin.doan_googbook.widget.CustomHeaderView;
import giaodien.admin.mylibrary.base.viper.ViewFragment;

/**
 * The Upload Fragment
 */
public class UploadFragment extends ViewFragment<UploadContract.Presenter>
    implements UploadContract.View, ImageAdapter.OnCameraAction , DocListAdapter.OnUpLoadDocFile {

  @BindView(R.id.upload_btn_back)
  CustomHeaderView mHeader;

  @BindView(R.id.upload_doc_name)
  CustomDocRow mDocName;

  @BindView(R.id.upload_doc_level)
  CustomDocRow mDocLevel;

  @BindView(R.id.upload_doc_subject)
  CustomDocRow mDocSubject;

  @BindView(R.id.upload_doc_university)
  CustomDocRow mDocUniversity;

  @BindView(R.id.upload_doc_des)
  CustomDocRow mDocDes;

  @BindView(R.id.upload_doc_year)
  CustomDocRow mDocYear;

  @BindView(R.id.upload_doc_type)
  CustomDocRow mDocType;

  @BindView(R.id.upload_btn_upload)
  Button mBtnUpload;

  @BindView(R.id.upload_first_divider)
  View mFirstDivider;

  @BindView(R.id.upload_second_divider)
  View mSecondDivider;

  @BindView(R.id.upload_recycler_img)
  RecyclerView mRecyclerView;

  @BindView(R.id.upload_btn_add)
  ImageView mBtnAdd;

  @BindView(R.id.upload_layout_doc_file)
  RelativeLayout mLayoutDocFile;

  @BindView(R.id.upload_recycler_doc_file)
  RecyclerView mRecyclerViewDoc;

  @BindView(R.id.upload_doc_file_option)
  CustomDocRow mDocFileOption;

  private static final int REQUEST_ID_IMAGE_CAPTURE = 100;
  private static final int REQUEST_ID_IMAGE_FROM_DEVICE = 1;
  private static final int CHOOSE_FILE_REQUESTCODE = 2 ;

  private List<String> listLevel;
  private List<String> listYear;
  private List<String> listOption;
  private List<String> listType;
  private List<Doc> listDoc;
  private List<String> listImage;
  private ImageAdapter imageAdapter;
  private DocListAdapter docListAdapter;
  private MagicalCamera magicalCamera;
  private MagicalPermissions magicalPermissions;
  private int RESIZE_PHOTO_PIXELS_PERCENTAGE = 80;
  String[] permissions = new String[]{
      Manifest.permission.CAMERA,
      Manifest.permission.READ_EXTERNAL_STORAGE,
      Manifest.permission.WRITE_EXTERNAL_STORAGE,
  };

  private int position;
  private DocListAdapter.OnUpLoadDocFile mOnUpLoadDocFile;
  int docNameLength = 0;
  int docSubjectLength;
  private Uri mUri;
  private List<FileUpload> fileList;

  public static UploadFragment getInstance() {
    return new UploadFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_upload;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    fileList = new ArrayList<>();
    mBtnUpload.setText(getString(R.string.upload_now));
    magicalPermissions = new MagicalPermissions(this, permissions);
    magicalCamera = new MagicalCamera(this.getViewContext(), RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

    mHeader.setOnLeftToggleListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.back();
      }
    });

    mOnUpLoadDocFile = this;
    createData();
    listImage = new ArrayList<>();
    listImage.add("");
    listImage.add("");
    listImage.add("");
    imageAdapter = new ImageAdapter(getContext(), listImage, this);
    RecyclerUtils.setUpGridRecyclerView(getContext(), mRecyclerView, 3);
    mRecyclerView.setAdapter(imageAdapter);

    mDocLevel.getmSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String level = mDocLevel.getmSpinner().getSelectedItem().toString();
        if (level.equals("Đại học")) {
          mDocUniversity.setVisibility(View.VISIBLE);
          mDocYear.setVisibility(View.VISIBLE);
          mFirstDivider.setVisibility(View.VISIBLE);
          mSecondDivider.setVisibility(View.VISIBLE);
        } else {
          mFirstDivider.setVisibility(View.GONE);
          mSecondDivider.setVisibility(View.GONE);
          mDocUniversity.setVisibility(View.GONE);
          mDocYear.setVisibility(View.GONE);
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }

    });

    mDocFileOption.getmSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(mDocFileOption.getmSpinner().getSelectedItem().equals("Có")){
          showListDoc();
        }
        else {
          mLayoutDocFile.setVisibility(View.GONE);
          if(docListAdapter != null) docListAdapter.clearData();
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    mBtnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        addRow();
      }
    });

    mDocName.setTextWatcher(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(mDocName.getEdtInputLength() > 0 && mDocSubject.getEdtInputLength() > 0)
          mBtnUpload.setEnabled(true);
        else mBtnUpload.setEnabled(false);
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });

    mDocSubject.setTextWatcher(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(mDocName.getEdtInputLength() > 0 && mDocSubject.getEdtInputLength() > 0)
          mBtnUpload.setEnabled(true);
        else mBtnUpload.setEnabled(false);

      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });

    mBtnUpload.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        uploadDoc("Upload");
      }
    });

  }

  public void addRow() {
    docListAdapter.addRow();
  }

  public void showListDoc() {
    mLayoutDocFile.setVisibility(View.VISIBLE);
    RecyclerUtils.setUpVerticalRecyclerView(getContext(), mRecyclerViewDoc);
    listDoc = new ArrayList<Doc>();
    listDoc.add(new Doc(getString(R.string.upload_no_doc_choosen)));
    docListAdapter = new DocListAdapter(getContext(), listDoc, mOnUpLoadDocFile);
    mRecyclerViewDoc.setAdapter(docListAdapter);
  }

  private void createData() {
    creatLevelData();
    creatYearData();
    createDocData();
    createDocType();
    mDocFileOption.setDataForSpinner(listOption);
    mDocLevel.setDataForSpinner(listLevel);
    mDocYear.setDataForSpinner(listYear);
    mDocType.setDataForSpinner(listType);
  }

  public void createDocType() {
    listType = new ArrayList<>();
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
  }

  public void creatLevelData() {
    listLevel = new ArrayList<>();
    listLevel.add("Tiểu học");
    listLevel.add("Trung học Cơ sở");
    listLevel.add("Trung học Phổ thông");
    listLevel.add("Đại học");
    listLevel.add("Khác");
  }

  public void creatYearData() {
    listYear = new ArrayList<>();
    listYear.add("Năm thứ nhất");
    listYear.add("Năm thứ hai");
    listYear.add("Năm thứ ba");
    listYear.add("Năm thứ tư");
    listYear.add("Năm thứ năm");
    listYear.add("Khác");
  }

  public void createDocData() {
    listOption = new ArrayList<>();
    listOption.add("Không có");
    listOption.add("Có");

  }

  @Override
  public void onCameraAction(String action, int position) {
    this.position = position;
    if (action.equals(getString(R.string.upload_action_take_picture))) {
      takePhoto();
    } else if (action.equals(getString(R.string.upload_action_choose_from_phone))) {
      magicalCamera.selectFragmentPicture(UploadFragment.this, getString(R.string.upload_choose_your_file));
    }
  }

  public void takePhoto() {
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(intent, REQUEST_ID_IMAGE_CAPTURE);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    String imageBase64;
    magicalCamera.resultPhoto(requestCode, resultCode, data);

    // Todo: rolate photo
    if(resultCode == Activity.RESULT_OK) switch (requestCode) {
      case REQUEST_ID_IMAGE_CAPTURE:
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        // Code for rolating photo here
        byte[] imageBytes = ConvertSimpleImage.bitmapToBytes(PictureUtils.resizePhoto(bitmap, 400, false), MagicalCamera.PNG);
        imageBase64 = ConvertSimpleImage.bytesToStringBase64(imageBytes);
        imageAdapter.setImage(imageBase64, position);
        break;

      case REQUEST_ID_IMAGE_FROM_DEVICE:
        Bitmap bitmap1 = (Bitmap) magicalCamera.getPhoto();
        // Code for rolating photo here
        byte[] imageBytes1 = ConvertSimpleImage.bitmapToBytes(PictureUtils.resizePhoto(bitmap1, 400, false), MagicalCamera.PNG);
        imageBase64 = ConvertSimpleImage.bytesToStringBase64(imageBytes1);
        imageAdapter.setImage(imageBase64, position);
        break;

      case CHOOSE_FILE_REQUESTCODE:
        Uri fileUri = data.getData();
        setTxtDocName(fileUri);
//          File tempFile = new File(videoUrl.getFile());
//          String fileName = tempFile.getName();
        break;
    }

  }

  public void setTxtDocName(Uri fileUri) {
    this.mUri = fileUri;
    ContentResolver contentResolver = getViewContext().getContentResolver();
    Cursor returnCursor = contentResolver.query(fileUri, null, null, null, null);
    String mType = contentResolver.getType(fileUri);
//    Log.d("type", mType);
    if (mType.contains("image") || mType.contains("video") || mType.contains("apk") || mType.contains("audio"))  {
      Toast.makeText(getContext(), "Ban chi co the up file tai lieu(định dạng .doc, .pdf, .zip)", Toast.LENGTH_SHORT).show();
    } else {
      int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
      returnCursor.moveToFirst();
      if(returnCursor.getLong(sizeIndex)/(double)(Math.pow(2,20)) > 15){
        Toast.makeText(getContext(), getString(R.string.upload_doc_max_size), Toast.LENGTH_SHORT).show();
      }
      else {
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        docListAdapter.setTxtDocName(returnCursor.getString(nameIndex), position);
        File file = new File(fileUri.getPath());
        FileUpload fileUpload = new FileUpload(returnCursor.getString(nameIndex), file);
        fileList.add(fileUpload);
      }
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    Map<String, Boolean> map = magicalPermissions.permissionResult(requestCode, permissions, grantResults);
    for (String permission : map.keySet()) {
      Log.d("PERMISSIONS", permission + " was: " + map.get(permission));
    }
    //Following the example you could also
    //locationPermissions(requestCode, permissions, grantResults);
  }

//  public Bitmap rolateImage(Bitmap sourceImage){
//    ExifInterface exifInterface = null;
//    try {
//      exifInterface = new ExifInterface(mPath);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    int oriention = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
//    Matrix matrix = new Matrix();
//    switch (oriention){
//      case ExifInterface.ORIENTATION_ROTATE_90:
//        matrix.setRotate(90);
//        break;
//      case ExifInterface.ORIENTATION_ROTATE_180:
//        matrix.setRotate(180);
//        break;
//      default:
//    }
//    return Bitmap.createBitmap(sourceImage, 0, 0, sourceImage.getWidth(), sourceImage.getHeight(), matrix, true);
//  }

  @Override
  public void hideUpLoadDocFileLayout() {
       mLayoutDocFile.setVisibility(View.GONE);
       mDocFileOption.getmSpinner().setSelection(0);
  }

  @Override
  public void onSelectFileFromDevice(int position) {
    this.position = position;
    String minmeType ="*/*";
    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
    intent.setType(minmeType);
    intent.addCategory(Intent.CATEGORY_OPENABLE);

    // special intent for Samsung file manager
    Intent sIntent = new Intent("com.sec.android.app.myfiles.PICK_DATA");
    // if you want any file type, you can skip next line
    sIntent.putExtra("CONTENT_TYPE", minmeType);
    sIntent.addCategory(Intent.CATEGORY_DEFAULT);

    Intent chooserIntent;
//    if (getPackageManager().resolveActivity(sIntent, 0) != null){
//      // it is device with samsung file manager
    sIntent = Intent.createChooser(sIntent, "Open file");
    sIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { intent});
//    }
//    else {
//      chooserIntent = Intent.createChooser(intent, "Open file");
//    }

    try {
      startActivityForResult(sIntent, CHOOSE_FILE_REQUESTCODE);
    } catch (android.content.ActivityNotFoundException ex) {
      Toast.makeText(getContext(), "No suitable File Manager was found.", Toast.LENGTH_SHORT).show();
    }

  }

  @Override
  public void setUpView(Post post) {
    if(post.getmDocName() != null) mDocName.setText(post.getmDocName());
    if(post.getmDocType() != null) mDocType.setSelectionForSpinner(getPostionOfTextInList(post.getmDocType(), listType));
    if(post.getmDocLevel() != null) mDocLevel.setSelectionForSpinner(getPostionOfTextInList(post.getmDocLevel(), listLevel));
    if(post.getmDocYear() != null) mDocYear.setSelectionForSpinner(getPostionOfTextInList(post.getmDocYear(), listYear));
    // Todo : list doc file
    if(post.getmDocSubject() != null) mDocSubject.setText(post.getmDocSubject());
    if(post.getmDocUniversity() != null) mDocUniversity.setText(post.getmDocUniversity());
    if(post.getmDocDes() != null) mDocDes.setText(post.getmDocDes());
    mBtnUpload.setText(getString(R.string.upload_edit_post));
    mHeader.setText(getString(R.string.upload_edit_post));
  }

  @Override
  public void clearView(String msg) {
    Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    mDocName.setText("");
    mDocSubject.setText("");
    mDocDes.setText("");
    if(listImage != null ){
      listImage.clear();
      listImage.add("");
      listImage.add("");
      listImage.add("");
      imageAdapter.updateList(listImage);
    }
    if(listDoc != null ) {
      mDocFileOption.setSelectionForSpinner(0);
      listDoc.clear();
      docListAdapter.updateData(listDoc);
    }
    mDocUniversity.setText("");
    mDocType.setSelectionForSpinner(0);
    mDocLevel.setSelectionForSpinner(0);
    mDocYear.setSelectionForSpinner(0);
  }

  public int getPostionOfTextInList(String text, List<String> list){
    int position = -1;
    if(list.contains(text)) position = list.indexOf(text);
    return position;
  }

  public void uploadDoc(String action) {

    String docUniversity, docYear;
    if(mDocLevel.getSpinnerText().equals("Đại học")){
      docUniversity = mDocUniversity.getEdtText();
      docYear = mDocYear.getEdtText();
    }
    else {
      docUniversity = null;
      docYear = null;
    }

    mPresenter.uploadDoc(SharedPreferUtils.getInt(getContext(), Constants.USER_ID), mDocSubject.getEdtText(), mDocName.getEdtText(),
        mDocType.getSpinnerText(), mDocDes.getEdtText(), mDocLevel.getSpinnerText(), docUniversity, docYear, turnListToOneString(imageAdapter.getListImages()), getFileName(fileList));
  }

  public String getFileName(List<FileUpload> fileList) {
    if(!fileList.isEmpty()){
    StringBuilder builder = new StringBuilder();
    for(int i = 0; i< fileList.size(); i++){
      String fileName = fileList.get(i).getName();
      builder.append(fileName + "   ");
    }
    return builder.toString();
    }
    else return null;
  }

  public String turnListToOneString(List<String> listImageToUpload) {
    if(!listImageToUpload.isEmpty()) {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < listImageToUpload.size(); i++) {
        String image = listImageToUpload.get(i);
        if (image.equals("")) listImageToUpload.remove(i);
        else builder.append(image + " ");
      }
      return builder.toString();
    }
    else return null;
  }


}
