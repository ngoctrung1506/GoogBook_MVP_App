package giaodien.admin.doan_googbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frosquivel.magicalcamera.Utilities.ConvertSimpleImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.model.MyPost;

/**
 * Created by Admin on 13/11/2017.
 */

public class MyPostItemAdapter extends RecyclerView.Adapter<MyPostItemAdapter.MyPostItemViewHolder> {

  private Context mContext;
  private List<MyPost> mListMyPost;
  private ListPostsAdapter.OnMoveToDocDetailScreenListener mOnMove;

  public MyPostItemAdapter(Context mContext, List<MyPost> mListMyPost, ListPostsAdapter.OnMoveToDocDetailScreenListener mOnMove) {
    this.mContext = mContext;
    this.mListMyPost = mListMyPost;
    this.mOnMove = mOnMove;
  }

  @Override
  public MyPostItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_post, parent, false);
    return new MyPostItemViewHolder(view);
  }

  @Override
  public void onBindViewHolder(MyPostItemViewHolder holder, int position) {
       MyPost myPost = mListMyPost.get(position);
       String image = myPost.getmDocImage();
       if(image != null) Glide.with(mContext)
           .load(ConvertSimpleImage.stringBase64ToBytes(image))
           .asBitmap().into(holder.mImageView);

//    if(position == 0 ) holder.mImageView.setImageResource(R.drawable.anh6);
//    else if(position == 1 ) holder.mImageView.setImageResource(R.drawable.java);
//    else if(position == 2 || position == 3) holder.mImageView.setImageResource(R.drawable.xulyanh);

    if(myPost.getmDocName() != null) holder.mDocName.setText(myPost.getmDocName());
    else holder.mRowDocName.setVisibility(View.GONE);

    if(myPost.getmDocType() != null) holder.mDocType.setText(myPost.getmDocType());
    else holder.mRowDocType.setVisibility(View.GONE);

    if(myPost.getmDocLevel() != null) holder.mDocLevel.setText(myPost.getmDocLevel());
    else holder.mRowDocLevel.setVisibility(View.GONE);

    if(myPost.getmDocUniversity() == null || myPost.getmDocUniversity().length() > 0) holder.mDocUniversity.setText(myPost.getmDocUniversity());
    else holder.mRowUniversity.setVisibility(View.GONE);

    if(myPost.getmDocPostDay() != null) holder.mDocPostDay.setText(myPost.getmDocPostDay());
    else holder.mRowPostDay.setVisibility(View.GONE);

  }

  public void setListData(List<MyPost> listData){
    this.mListMyPost = listData;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return mListMyPost.size();
  }

  public class MyPostItemViewHolder extends ViewHolder {

    @BindView(R.id.item_my_post_layout_root)
    LinearLayout mRoot;

    @BindView(R.id.item_my_post_img)
    ImageView mImageView;

    @BindView(R.id.item_my_post_row_doc_name)
    TableRow mRowDocName;

    @BindView(R.id.item_my_post_txt_doc_name)
    TextView mDocName;

    @BindView(R.id.item_my_post_row_university)
    TableRow mRowUniversity;

    @BindView(R.id.item_my_post_txt_doc_university)
    TextView mDocUniversity;

    @BindView(R.id.item_my_post_row_doc_type)
    TableRow mRowDocType;

    @BindView(R.id.item_my_post_txt_doc_type)
    TextView mDocType;

    @BindView(R.id.item_my_post_row_doc_level)
    TableRow mRowDocLevel;

    @BindView(R.id.item_my_post_txt_doc_level)
    TextView mDocLevel;

    @BindView(R.id.item_my_post_row_doc_post_day)
    TableRow mRowPostDay;

    @BindView(R.id.item_my_post_txt_doc_post_day)
    TextView mDocPostDay;

    public MyPostItemViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      addEvent();
    }

    public void addEvent() {
      mRoot.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          mOnMove.onMoveToDocDetail(mListMyPost.get(getAdapterPosition()).getId(), "detail");
        }
      });
    }
  }
}
