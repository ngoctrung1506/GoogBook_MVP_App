package giaodien.admin.doan_googbook.screen.home.bookstore;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.adapter.BooksAdapter;
import giaodien.admin.doan_googbook.adapter.ListBooksAdapter;
import giaodien.admin.doan_googbook.dialog.DiologFilter;
import giaodien.admin.doan_googbook.model.ItemPost;
import giaodien.admin.doan_googbook.model.ListItemPost;
import giaodien.admin.doan_googbook.model.ListOptions;
import giaodien.admin.doan_googbook.utils.RecyclerUtils;
import giaodien.admin.doan_googbook.widget.CustomHeaderView;
import giaodien.admin.mylibrary.base.viper.ViewFragment;

/**
 * The BookStore Fragment
 */
public class BookStoreFragment extends ViewFragment<BookStoreContract.Presenter>
    implements BookStoreContract.View, ListBooksAdapter.OnShowByAction, DiologFilter.OnFilterListener {

  @BindView(R.id.book_store_recycler_view_main)
  SuperRecyclerView mRecyclerViewMain;

  @BindView(R.id.book_store_recycler_view_search)
  SuperRecyclerView mRecyclerViewSearch;

  @BindView(R.id.book_store_header)
  CustomHeaderView mHeader;

  @BindView(R.id.book_store_swipe_layout)
  SwipeRefreshLayout mSwipeLayout;

  private ListBooksAdapter mListBooksAdapter;
  private BooksAdapter mBooksAdapter;
  private ArrayList<ListItemPost> mListBooks;
  private ArrayList<ItemPost> mListItemPosts;


  public static BookStoreFragment getInstance() {
    return new BookStoreFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_book_store;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mListBooks = new ArrayList<>();
    mListItemPosts = new ArrayList<>();
    mPresenter.getDataForList();
    RecyclerUtils.setUpVerticalRecyclerView(getContext(), mRecyclerViewMain);
    mListBooksAdapter = new ListBooksAdapter(getContext(), mListBooks, this);
    mRecyclerViewMain.setAdapter(mListBooksAdapter);

    RecyclerUtils.setUpGridRecyclerView(getContext(), mRecyclerViewSearch, 3);
    mBooksAdapter = new BooksAdapter(getContext(), mListItemPosts, this);
    mRecyclerViewSearch.setAdapter(mBooksAdapter);

    mRecyclerViewSearch.setVisibility(View.GONE);
    mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        refresh();
      }
    });
    mHeader.setOnLeftToggleListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showPopUp();
      }
    });
  }

  public void showPopUp() {
    new DiologFilter(getContext(), this).show();
  }

  public void refresh() {
    mPresenter.getDataForList();
    mSwipeLayout.setRefreshing(false);
  }


  @Override
  public void showAll(String type) {
    mPresenter.move(type);
  }

  @Override
  public void showByPostId(int postId) {
     mPresenter.showByPostId(postId);
  }

  @Override
  public void setDataForMainScreen(ArrayList<ListItemPost> listItemPosts) {
    if(listItemPosts != null)
    mListBooksAdapter.setDataForList(listItemPosts);
  }

  @Override
  public void setUpViewForChoosenOption(ArrayList<ItemPost> itemPosts) {
       mRecyclerViewSearch.setVisibility(View.VISIBLE);
       mRecyclerViewMain.setVisibility(View.GONE);
       mBooksAdapter.updateList(itemPosts);
  }

  @Override
  public void onFilter(ListOptions listCondition) {
    mPresenter.sendConditionToServer(listCondition);
  }
}
