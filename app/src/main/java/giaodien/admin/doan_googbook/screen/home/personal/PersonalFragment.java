package giaodien.admin.doan_googbook.screen.home.personal;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frosquivel.magicalcamera.Utilities.ConvertSimpleImage;

import butterknife.BindView;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.constants.Constants;
import giaodien.admin.doan_googbook.utils.SharedPreferUtils;
import giaodien.admin.doan_googbook.widget.CustomHeaderView;
import giaodien.admin.mylibrary.base.viper.ViewFragment;

/**
 * The Personal Fragment
 */
public class PersonalFragment extends ViewFragment<PersonalContract.Presenter> implements PersonalContract.View {

  @BindView(R.id.personal_header_view)
  CustomHeaderView mHeaderView;

  @BindView(R.id.personal_img_avatar)
  ImageView mImgAvatar;

  @BindView(R.id.personal_img_background)
  ImageView mImgBackGround;

  @BindView(R.id.personal_txt_user_account)
  TextView mUserAccount;

  @BindView(R.id.personal_layout_my_post)
  LinearLayout mMyPost;

  @BindView(R.id.personal_layout_my_mark)
  LinearLayout mMyMark;

  @BindView(R.id.personal_layout_my_info)
  LinearLayout mMyInfo;

  @BindView(R.id.personal_layout_my_dowloaded)
  LinearLayout mMyDownLoaded;

  @BindView(R.id.personal_layout_log_out)
  LinearLayout mLogOut;

  public static PersonalFragment getInstance() {
    return new PersonalFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_personal;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    SharedPreferUtils.putInt(getContext(), Constants.USER_ID, 1);
    mHeaderView.setOnLeftToggleListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.back();
      }
    });
    setImage();
    mMyPost.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.showViewByType("mine");
      }
    });

    mMyMark.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.showViewByType("saved");
      }
    });

    mLogOut.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new Thread(new Runnable() {
          @Override
          public void run() {
            SharedPreferUtils.putString(getContext(), Constants.IS_LOGED_IN, "no");
          }
        }).start();
        mPresenter.logOut();
      }
    });

    mMyInfo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.showUserInfo();
      }
    });

  }

  public void setImage() {
    String baseAvatar64 = SharedPreferUtils.getString(getContext(), Constants.USER_AVATAR);
    String baseBackGround64 = SharedPreferUtils.getString(getContext(), Constants.USER_BACKGROUND);
    if(baseAvatar64 != null)
      Glide.with(getContext())
          .load(ConvertSimpleImage.stringBase64ToBytes(baseAvatar64))
          .asBitmap().into(mImgAvatar);

    if(baseBackGround64 != null)
      Glide.with(getContext())
          .load(ConvertSimpleImage.stringBase64ToBytes(baseBackGround64))
          .asBitmap().into(mImgBackGround);
  }
}
