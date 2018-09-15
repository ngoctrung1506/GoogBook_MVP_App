package giaodien.admin.doan_googbook.screen.home.userinfo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.adapter.UserInfoAdapter;
import giaodien.admin.doan_googbook.constants.Constants;
import giaodien.admin.doan_googbook.dialog.UpdateUserInfoDialog;
import giaodien.admin.doan_googbook.model.ItemUserInfo;
import giaodien.admin.doan_googbook.utils.NetworkUtils;
import giaodien.admin.doan_googbook.utils.RecyclerUtils;
import giaodien.admin.doan_googbook.utils.SharedPreferUtils;
import giaodien.admin.doan_googbook.widget.CustomHeaderView;
import giaodien.admin.mylibrary.base.viper.ViewFragment;

/**
 * The UserInfo Fragment
 */
public class UserInfoFragment extends ViewFragment<UserInfoContract.Presenter>
    implements UserInfoContract.View, UpdateUserInfoDialog.OnUpdateListener {

  @BindView(R.id.user_info_header)
  CustomHeaderView mHeader;

  @BindView(R.id.user_info_recycler)
  RecyclerView mRecycler;

  private List<ItemUserInfo> listUserInfo;
  private UserInfoAdapter userInfoAdapter;
  private int userId;

  public static UserInfoFragment getInstance() {
    return new UserInfoFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_user_info;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mHeader.setOnLeftToggleListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.back();
      }
    });

    listUserInfo = new ArrayList<>();
    userInfoAdapter = new UserInfoAdapter(getContext(), listUserInfo, this);
    RecyclerUtils.setUpVerticalRecyclerView(getContext(), mRecycler);
    mRecycler.setAdapter(userInfoAdapter);
  }

  @Override
  public void onUpdate(String title, String value, int postion) {
    if(NetworkUtils.isConnect(getContext())) {
      userInfoAdapter.updateItem(title, value, postion);
      mPresenter.onUpdate(userId, title, value);
    }
    else Toast.makeText(getContext(), R.string.app_no_data_retry_later, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void setUpView(int userId) {
    mPresenter.getUserInfo(SharedPreferUtils.getInt(getContext(), Constants.USER_ID));
    this.userId = userId;
  }

  @Override
  public void setView(int userId, String password, String address, String email, String name, String phone, String sex, String yearBorn) {
    listUserInfo.add(new ItemUserInfo("Tên người dùng", name));
//    listUserInfo.add(new ItemUserInfo("Tên của bạn", name));
//    listUserInfo.add(new ItemUserInfo("Mật khẩu", password));
    listUserInfo.add(new ItemUserInfo("Email", email));
    listUserInfo.add(new ItemUserInfo("Số điện thoại", phone));
    listUserInfo.add(new ItemUserInfo("Giới tính", sex));
    listUserInfo.add(new ItemUserInfo("Ngày tháng năm sinh", yearBorn));
    listUserInfo.add(new ItemUserInfo("Địa chỉ", address));
    userInfoAdapter.updateList(listUserInfo);
  }
}
