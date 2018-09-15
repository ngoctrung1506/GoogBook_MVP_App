package giaodien.admin.doan_googbook.screen.home.lastestnew;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.adapter.ListPostsAdapter;
import giaodien.admin.doan_googbook.constants.Constants;
import giaodien.admin.doan_googbook.model.Post;
import giaodien.admin.doan_googbook.utils.RecyclerUtils;
import giaodien.admin.doan_googbook.utils.SharedPreferUtils;
import giaodien.admin.mylibrary.base.viper.ViewFragment;


/**
 * The LastNews Fragment
 */
public class LastestNewsFragment extends ViewFragment<LastestNewsContract.Presenter>
    implements LastestNewsContract.View,
    ListPostsAdapter.OnMoveToDocDetailScreenListener, ListPostsAdapter.OnMoveToUserDetailScreenListener, ListPostsAdapter.OnSendPostId {

  @BindView(R.id.lastest_news_btn_upload)
  Button mBtnUpload;

  @BindView(R.id.lastest_news_super_recycler)
  RecyclerView mRecyclerView;

  @BindView(R.id.lastest_news_swipe_refresh)
  SwipeRefreshLayout mSwipeRefresh;

  private ListPostsAdapter mListPostsAdapter;
  private ArrayList<Post> mPostList;

  public static LastestNewsFragment getInstance() {
    return new LastestNewsFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_lastest_news;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    setUpRecyclerView();
    mBtnUpload.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.onMoveToUpLoadView();
      }
    });

    mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        mPresenter.getLastestNewData();
        mSwipeRefresh.setRefreshing(false);
      }
    });

//    mRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//      @Override
//      public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//      }
//    });

  }

  public void setUpRecyclerView() {
    mPostList = new ArrayList<>();
    RecyclerUtils.setUpVerticalRecyclerView(getViewContext(), mRecyclerView);
    mListPostsAdapter = new ListPostsAdapter(getViewContext(), mPostList, this);
    mRecyclerView.setAdapter(mListPostsAdapter);
    mPresenter.getLastestNewData();
  }

  @Override
  public void setLastestNewData(ArrayList<Post> posts) {
    if(posts != null)
    mListPostsAdapter.setPostList(posts);
  }

  @Override
  public void onMoveToDocDetail(int idPost, String type) {
    if(type.equals("detail"))
        mPresenter.moveToDocDetailScreen(idPost);
    else
    {
      if(type.equals("true"))
        mPresenter.onActionSave(SharedPreferUtils.getInt(getContext(), Constants.USER_ID), idPost, Constants.TRUE);
//        mPresenter.onActionSave(1, idPost, Constants.TRUE);
//      else mPresenter.onActionSave(1, idPost, Constants.FALSE);
      else mPresenter.onActionSave(SharedPreferUtils.getInt(getContext(), Constants.USER_ID), idPost, Constants.FALSE);
    }
  }

  @Override
  public void onMoveToUserDetail(int idPost) {

  }

  @Override
  public void onSendPostId(int postId) {

  }
}
