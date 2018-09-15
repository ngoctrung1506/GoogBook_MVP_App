package giaodien.admin.doan_googbook.screen.home.righttoggle;

import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.frosquivel.magicalcamera.Utilities.ConvertSimpleImage;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.constants.Constants;
import giaodien.admin.doan_googbook.utils.SharedPreferUtils;
import giaodien.admin.mylibrary.base.viper.ViewFragment;


/**
 * The Personal Fragment
 */
public class RightToggleFragment extends ViewFragment<RightToggleContract.Presenter> implements RightToggleContract.View {

  @BindView(R.id.right_toggle_layout_personal)
  LinearLayout mLayoutPersonal;

  @BindView(R.id.right_toggle_layout_intro)
  LinearLayout mLayoutIntro;

  @BindView(R.id.right_toggle_layout_support)
  LinearLayout mLayoutSupport;

  @BindView(R.id.right_toggle_avatar)
  CircularImageView mAvatar;

  public static RightToggleFragment getInstance() {
    return new RightToggleFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_right_toggle;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mLayoutPersonal.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.moveToPersonal();
      }
    });

    String baseAvatar64 = SharedPreferUtils.getString(getContext(), Constants.USER_AVATAR);
    if(baseAvatar64 != null)
    Glide.with(getContext())
        .load(ConvertSimpleImage.stringBase64ToBytes(baseAvatar64))
        .asBitmap().into(mAvatar);
  }
}
