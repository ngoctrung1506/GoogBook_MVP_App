package giaodien.admin.doan_googbook.screen.home.signup;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.frosquivel.magicalcamera.Utilities.ConvertSimpleImage;
import com.frosquivel.magicalcamera.Utilities.PictureUtils;
import com.michael.easydialog.EasyDialog;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.utils.NetworkUtils;
import giaodien.admin.doan_googbook.widget.CustomSignUpRow;
import giaodien.admin.mylibrary.base.viper.ViewFragment;

;

/**
 * The SignUp Fragment
 */
public class SignUpFragment extends ViewFragment<SignUpContract.Presenter> implements SignUpContract.View {

  @BindView(R.id.sign_up_edt_account)
  CustomSignUpRow mEdtAccount;

  @BindView(R.id.sign_up_edt_password)
  CustomSignUpRow mEdtPassWord;

  @BindView(R.id.sign_up_edt_re_password)
  CustomSignUpRow mEdtRePassWord;

  @BindView(R.id.sign_up_edt_user_name)
  CustomSignUpRow mEdtUserName;

  @BindView(R.id.sign_up_edt_email)
  CustomSignUpRow mEdtEmail;

  @BindView(R.id.sign_up_edt_phone)
  CustomSignUpRow mEdtPhone;

  @BindView(R.id.sign_up_edt_sex)
  CustomSignUpRow mEdtSex;

  @BindView(R.id.sign_up_edt_address)
  CustomSignUpRow mEdtAddress;

  @BindView(R.id.sign_up_edt_year_born)
  CustomSignUpRow mEdtBirtDay;

  @BindView(R.id.sign_up_img_avatar)
  CircularImageView mBtnAvatar;

  @BindView(R.id.sign_up_img_add_background)
  CircularImageView mBtnAddBg;

  @BindView(R.id.sign_up_btn_sign_up)
  Button mBtnSignUp;

  @BindView(R.id.sign_up_btn_have_account)
  TextView mBtnHaveAccount;

  @BindView(R.id.sign_up_img_background)
  ImageView mImgBackGround;

  public List<String> listSex;

  private SharedPreferences loginSharedPreferences;
  private SharedPreferences.Editor loginEditor;

  private static final int REQUEST_ID_IMAGE_CAPTURE = 100;
  private static final int REQUEST_ID_IMAGE_FROM_DEVICE = 1;

  private MagicalCamera magicalCamera;
  private MagicalPermissions magicalPermissions;
  private int RESIZE_PHOTO_PIXELS_PERCENTAGE = 80;
  String[] permissions = new String[]{
      Manifest.permission.CAMERA,
      Manifest.permission.READ_EXTERNAL_STORAGE,
      Manifest.permission.WRITE_EXTERNAL_STORAGE,
  };

  private String clickedBtn;
  private String mImageBase64Avatar;
  private String mImageBase64BackGround;
  private EasyDialog easyDialog;

  public static SignUpFragment getInstance() {
    return new SignUpFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_sign_up;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mBtnHaveAccount.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.onMoveToLogin();
      }
    });

    magicalPermissions = new MagicalPermissions(this, permissions);
    magicalCamera = new MagicalCamera(this.getViewContext(), RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

    createSpinnerData();
    setClickToSelectImage(mBtnAvatar);
    setClickToSelectImage(mBtnAddBg);
    setClickToSelectImage(mImgBackGround);

    mEdtAccount.setTextWatcher(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
               if(s.length() > 0 && mEdtPassWord.getEdtText().length() > 0) mBtnSignUp.setEnabled(true);
        else mBtnSignUp.setEnabled(false);
      }
    });

    mEdtPassWord.setTextWatcher(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if(s.length() > 0 && mEdtAccount.getEdtText().length() > 0) mBtnSignUp.setEnabled(true);
        else mBtnSignUp.setEnabled(false);
      }
    });
     mBtnSignUp.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
         onSignUp();
       }
     });

    loginSharedPreferences = getViewContext().getSharedPreferences("loginSharedPreferences", Context.MODE_PRIVATE);
    loginEditor = loginSharedPreferences.edit();

  }

  public void onSignUp() {
    if(NetworkUtils.isConnect(getContext())) {
      mPresenter.signUp(mEdtAccount.getEdtText(), mEdtPassWord.getEdtText(), mEdtUserName.getEdtText(),
          mEdtEmail.getEdtText(), mEdtPhone.getEdtText(), mEdtAddress.getEdtText(), mEdtBirtDay.getEdtText(), mEdtSex.getSpinnerChoice(),
          mImageBase64Avatar, mImageBase64BackGround);
    }
    else Toast.makeText(getContext(), getString(R.string.app_no_intenet_connection), Toast.LENGTH_SHORT).show();
  }

  public void setClickToSelectImage(final ImageView btn) {
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showEasyDialog(btn);
      }
    });
  }

  public void showEasyDialog(final ImageView btn) {
    View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_camera_action, null);
    TextView btnTakePicture = view.findViewById(R.id.dialog_camera_action_btn_take_photo);
    TextView btnChooseFromPhone = view.findViewById(R.id.dialog_camera_action_btn_choose_from_phone);
    easyDialog = new EasyDialog(getContext())
        .setLayout(view)
        .setBackgroundColor(getContext().getResources().getColor(R.color.colorWhite))
        .setLocationByAttachedView(btn)
        .setGravity(EasyDialog.GRAVITY_TOP)
        .setTouchOutsideDismiss(true)
        .setMatchParent(false)
        .setOutsideColor(getContext().getResources().getColor(R.color.outside_color_gray))
        .setMarginLeftAndRight(24, 24)
        .show();

    btnTakePicture.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onCameraAction("TakePicture", btn);
      }
    });

    btnChooseFromPhone.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onCameraAction("ChooseFromPhone", btn);
      }
    });


  }

  public void onCameraAction(String action, ImageView btn) {
    if(action.equals("TakePicture")){
      if(btn.equals(mBtnAvatar)) clickedBtn = "btnAvatar";
      else clickedBtn = "btnBackGround";
      takePicture();
    }
    else
    {
      if(btn.equals(mBtnAvatar)) clickedBtn = "btnAvatar";
      else clickedBtn = "btnBackGround";
      magicalCamera.selectFragmentPicture(SignUpFragment.this, getString(R.string.upload_choose_your_file));
    }
  }

  private void takePicture() {
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(intent, REQUEST_ID_IMAGE_CAPTURE);
  }

  public void createSpinnerData() {
    listSex = new ArrayList<>();
    listSex.add("Nam");
    listSex.add("Nữ");
    listSex.add("Khác");
    mEdtSex.setDataForSpinner(listSex);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    magicalCamera.resultPhoto(requestCode, resultCode, data);
    if(resultCode == Activity.RESULT_OK) switch (requestCode) {
      case REQUEST_ID_IMAGE_CAPTURE:
        setImage(PictureUtils.resizePhoto((Bitmap) data.getExtras().get("data"), 400, false));
          break;

      case REQUEST_ID_IMAGE_FROM_DEVICE:
       setImage(PictureUtils.resizePhoto(magicalCamera.getPhoto(), 600, false));
        break;
    }

    }

    public void setImage(Bitmap bitmap) {
      byte[] imageBytes = ConvertSimpleImage.bitmapToBytes(bitmap, Bitmap.CompressFormat.PNG);
      if (clickedBtn.equals("btnAvatar")){
        mBtnAvatar.setImageBitmap(bitmap);
      mImageBase64Avatar = ConvertSimpleImage.bytesToStringBase64(imageBytes);
    }
      else
      {
        mImgBackGround.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mBtnAddBg.setVisibility(View.GONE);
        mImgBackGround.setVisibility(View.VISIBLE);
        mImgBackGround.setImageBitmap(bitmap);
        mImageBase64BackGround = ConvertSimpleImage.bytesToStringBase64(imageBytes);
      }
    }

  @Override
  public void moveToLogIn(String msg, int userId) {
    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    mPresenter.onMoveToLogin();
  }
}
