package giaodien.admin.doan_googbook.screen.home.mypost;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.adapter.ListPostsAdapter;
import giaodien.admin.doan_googbook.adapter.MyPostItemAdapter;
import giaodien.admin.doan_googbook.constants.Constants;
import giaodien.admin.doan_googbook.model.MyPost;
import giaodien.admin.doan_googbook.utils.RecyclerUtils;
import giaodien.admin.doan_googbook.utils.SharedPreferUtils;
import giaodien.admin.doan_googbook.widget.CustomHeaderView;
import giaodien.admin.mylibrary.base.viper.ViewFragment;

/**
 * The MyPost Fragment
 */
public class MyPostFragment extends ViewFragment<MyPostContract.Presenter>
    implements MyPostContract.View, ListPostsAdapter.OnMoveToDocDetailScreenListener {

  @BindView(R.id.my_post_header)
  CustomHeaderView mHeader;

  @BindView(R.id.my_post_recycler)
  RecyclerView mRecycler;

  private MyPostItemAdapter mMyPostAdapter;
  private List<MyPost> mListMyPost;

  public static MyPostFragment getInstance() {
    return new MyPostFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_my_post;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mListMyPost = new ArrayList<>();
    RecyclerUtils.setUpVerticalRecyclerView(getContext(), mRecycler);
    mMyPostAdapter = new MyPostItemAdapter(getContext(), mListMyPost, this);
    mRecycler.setAdapter(mMyPostAdapter);

    mHeader.setOnLeftToggleListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.back();
      }
    });
  }

  @Override
  public void setUpView(List<MyPost> myPostList) {
    mMyPostAdapter.setListData(myPostList);
  }

  @Override
  public void showViewByType(String type) {
    SharedPreferUtils.putInt(getViewContext(), Constants.USER_ID, 15);
    if(type.equals("mine")){
      mPresenter.getAllMyPost(SharedPreferUtils.getInt(getContext(), Constants.USER_ID));
//      Log.d("id", String.valueOf(SharedPreferUtils.getInt(getContext(), Constants.USER_ID)));
      mHeader.setText(getString(R.string.personal_my_post));
    }
    else {
      mPresenter.getAllSavedPost(SharedPreferUtils.getInt(getContext(), Constants.USER_ID));
      mHeader.setText(getString(R.string.personal_my_mark));
    }
  }

  @Override
  public void onMoveToDocDetail(int idPost, String type) {
    mPresenter.moveToDocDetail(idPost);
  }
}
