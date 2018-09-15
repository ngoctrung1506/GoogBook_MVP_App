package giaodien.admin.doan_googbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.model.ItemPost;
import giaodien.admin.doan_googbook.model.ListItemPost;
import giaodien.admin.doan_googbook.utils.NetworkUtils;
import giaodien.admin.doan_googbook.utils.RecyclerUtils;

/**
 * Created by Admin on 04/10/2017.
 */

public class ListBooksAdapter extends RecyclerView.Adapter<ListBooksAdapter.ListBooksViewHolder> {


  private Context mContext;
  private ArrayList<ListItemPost> mListBooks;
  private BooksAdapter mBooksAdapter;
  private OnShowByAction mOnShowByAction;

  public ListBooksAdapter(Context mContext, ArrayList<ListItemPost> listBooks, OnShowByAction onShowByAction) {
    this.mContext = mContext;
    this.mListBooks = listBooks;
    this.mOnShowByAction = onShowByAction;
  }

  @Override
  public ListBooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View view = inflater.inflate(R.layout.item_list_book, parent, false);
    return new ListBooksViewHolder(view, mOnShowByAction);
  }

  @Override
  public void onBindViewHolder(ListBooksViewHolder holder, int position) {
       ListItemPost books = mListBooks.get(position);
       holder.mTypeOfBook.setText(books.getmType());
       ArrayList<ItemPost> arrayList = books.getmItemPosts();
       RecyclerUtils.setUpHorizontalRecyclerView(mContext, holder.mRecyclerView);
       mBooksAdapter = new BooksAdapter(mContext, arrayList, mOnShowByAction);
       holder.mRecyclerView.setAdapter(mBooksAdapter);
  }

  public void setDataForList(ArrayList<ListItemPost> dataForList){
    mListBooks = dataForList;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return mListBooks.size();
  }

  public class ListBooksViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_list_book_type_of_book)
    TextView mTypeOfBook;

    @BindView(R.id.item_list_book_btn_see_all)
    TextView mBtnSeeAll;

    @BindView(R.id.item_list_book_recycler_view)
    RecyclerView mRecyclerView;

    private OnShowByAction mOnShowByAction;

    public ListBooksViewHolder(View itemView, OnShowByAction mOnShowByAction) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      this.mOnShowByAction = mOnShowByAction;
      addEvent();
    }

    public void addEvent() {
      if(NetworkUtils.isConnect(mContext))
      mBtnSeeAll.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          mOnShowByAction.showAll(mTypeOfBook.getText().toString());
        }
      });
      else Toast.makeText(mContext, R.string.app_no_data_retry_later, Toast.LENGTH_SHORT).show();
    }
  }

  public interface OnShowByAction {
    void showAll(String type);
    void showByPostId(int postId);
  }

}
