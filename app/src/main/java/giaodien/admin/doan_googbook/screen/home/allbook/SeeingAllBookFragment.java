package giaodien.admin.doan_googbook.screen.home.allbook;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.adapter.BooksAdapter;
import giaodien.admin.doan_googbook.adapter.ListBooksAdapter;
import giaodien.admin.doan_googbook.model.ItemPost;
import giaodien.admin.doan_googbook.utils.RecyclerUtils;
import giaodien.admin.doan_googbook.widget.CustomHeaderView;
import giaodien.admin.mylibrary.base.viper.ViewFragment;


/**
 * The SeeingAllBook Fragment
 */
public class SeeingAllBookFragment extends ViewFragment<SeeingAllBookContract.Presenter>
    implements SeeingAllBookContract.View, ListBooksAdapter.OnShowByAction {

  @BindView(R.id.seeing_all_book_header_view)
  CustomHeaderView mHeaderView;

  @BindView(R.id.seeing_all_book_recycler_view)
  SuperRecyclerView mRecyclerView;

  private BooksAdapter booksAdapter;
  private ArrayList<ItemPost> mItemPostList;

  public static SeeingAllBookFragment getInstance() {
    return new SeeingAllBookFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_seeing_all_book;
  }

  @Override
  public void initLayout() {
    super.initLayout();

    mItemPostList = new ArrayList<>();
    RecyclerUtils.setUpGridRecyclerView(getContext(), mRecyclerView, 3);
    booksAdapter = new BooksAdapter(getContext(), mItemPostList , this);
    mRecyclerView.setAdapter(booksAdapter);
  }

  @Override
  public void setUpView(String type) {
    mPresenter.showAllByType(type);
  }

  @Override
  public void createData(ArrayList<ItemPost> itemPosts) {
    booksAdapter.updateList(itemPosts);
  }

  @Override
  public void showAll(String type) {

  }

  @Override
  public void showByPostId(int postId) {
    mPresenter.showPostById(postId);
  }
}
