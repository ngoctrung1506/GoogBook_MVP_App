package giaodien.admin.doan_googbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.frosquivel.magicalcamera.Utilities.ConvertSimpleImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.model.ItemPost;
import giaodien.admin.doan_googbook.utils.NetworkUtils;

/**
 * Created by Admin on 04/10/2017.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder>{

  private Context mContext;
  private ArrayList<ItemPost> itemPosts;
  private ListBooksAdapter.OnShowByAction onShowByAction;

  public BooksAdapter(Context mContext, ArrayList<ItemPost> arrayList, ListBooksAdapter.OnShowByAction mOnShowByAction) {
    this.mContext = mContext;
    this.itemPosts = arrayList;
    this.onShowByAction = mOnShowByAction;
  }

  @Override
  public BooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View view = inflater.inflate(R.layout.item_doc, parent, false);
    return new BooksViewHolder(view);
  }

  @Override
  public void onBindViewHolder(BooksViewHolder holder, int position) {
       ItemPost itemPost = itemPosts.get(position);
    if(itemPost.getmImg() != null && !itemPost.getmImg().equals(" "))
      Glide.with(mContext)
          .load(ConvertSimpleImage.stringBase64ToBytes(itemPost.getmImg()))
          .asBitmap().into(holder.mImg);
    holder.mImg.setScaleType(ImageView.ScaleType.FIT_XY);

       if(itemPost.getmName() != null ) holder.mTxtName.setText(itemPost.getmName());
       else holder.mTxtName.setVisibility(View.GONE);

         if(itemPost.getmAuthor() != null ) holder.mTxtAuthor.setText(itemPost.getmAuthor());
         else holder.mTxtAuthor.setVisibility(View.GONE);

         if(itemPost.getmUniversity() != null )  holder.mTxtUniversity.setText(itemPost.getmUniversity());
         else holder.mTxtUniversity.setVisibility(View.GONE);

  }

  @Override
  public int getItemCount() {
    return itemPosts.size();
  }

  public void updateList(ArrayList<ItemPost> itemPosts) {
    this.itemPosts = itemPosts;
    notifyDataSetChanged();
  }

  public class BooksViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_doc_img)
    ImageView mImg;

    @BindView(R.id.item_doc_txt_author)
    TextView mTxtAuthor;

    @BindView(R.id.item_doc_txt_name)
    TextView mTxtName;

    @BindView(R.id.item_doc_txt_university)
    TextView mTxtUniversity;

    @BindView(R.id.item_doc_root_view)
    LinearLayout mRoot;

    public BooksViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      addEvent();
    }

    public void addEvent() {
      if(NetworkUtils.isConnect(mContext))
      mRoot.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          onShowByAction.showByPostId(itemPosts.get(getAdapterPosition()).getId());
        }
      });
      else Toast.makeText(mContext, R.string.app_no_data_retry_later, Toast.LENGTH_SHORT).show();
    }


  }

}
