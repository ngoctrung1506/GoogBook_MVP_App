package giaodien.admin.doan_googbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frosquivel.magicalcamera.Utilities.ConvertSimpleImage;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.model.Post;

/**
 * Created by Admin on 09/10/2017.
 */

public class ListPostsAdapter extends RecyclerView.Adapter<ListPostsAdapter.PostsViewHolder>{

  private Context mContext;
  private ArrayList<Post> mPostList;
  private OnMoveToDocDetailScreenListener mToDetailScreen;
  private OnMoveToUserDetailScreenListener mToUserScreen;
  private OnSendPostId mOnSendPostId;

  public ListPostsAdapter(Context mContext, ArrayList<Post> mPostList, OnMoveToDocDetailScreenListener onMoveToDocDetailScreenListener) {
    this.mContext = mContext;
    this.mPostList = mPostList;
    this.mToDetailScreen = onMoveToDocDetailScreenListener;
  }

  @Override
  public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View view = inflater.inflate(R.layout.item_post, parent, false);
    return new PostsViewHolder(view, mToDetailScreen);
  }

  @Override
  public void onBindViewHolder(PostsViewHolder holder, int position) {
    Post post = mPostList.get(position);
    if(post.getmAvatar() != null)
      Glide.with(mContext).
        load(ConvertSimpleImage.stringBase64ToBytes(post.getmAvatar())).
        asBitmap().into(holder.mAvatar);
    setTextView(post.getmUserAccount(), holder.mUserAccount);
    setTextView(post.getmTime(), holder.mTime);
    setTextView(post.getmDocName(), holder.mDocName);
    setTextView(post.getmDocLevel(), holder.mDocLevel);
    setTextView(post.getmDocUniversity(), holder.mDocUniversity);
    if(post.getmDocFirstImage() != null){
      holder.mDocImage.setVisibility(View.VISIBLE);
      Glide.with(mContext).
        load(ConvertSimpleImage.stringBase64ToBytes(post.getmDocFirstImage())).
        asBitmap().into(holder.mDocImage);
    }
  }

  public void setTextView(String data, TextView textView){
    if(data != null) textView.setText(data);
    else textView.setVisibility(View.GONE);
  }

  @Override
  public int getItemCount() {
    return mPostList.size();
  }

  public void setPostList(ArrayList<Post> postList){
    this.mPostList = postList;
    notifyDataSetChanged();
  }

  public class PostsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_post_avatar)
    CircularImageView mAvatar;

    @BindView(R.id.item_post_user_account)
    TextView mUserAccount;

    @BindView(R.id.item_post_time)
    TextView mTime;

    @BindView(R.id.item_post_doc_name)
    TextView mDocName;

    @BindView(R.id.item_post_doc_level)
    TextView mDocLevel;

    @BindView(R.id.item_post_doc_university)
    TextView mDocUniversity;

    @BindView(R.id.item_post_doc_image)
    ImageView mDocImage;

    @BindView(R.id.item_post_btn_like)
    LinearLayout mBtnLike;

    @BindView(R.id.item_post_btn_share)
    LinearLayout mBtnShare;

    @BindView(R.id.item_post_btn_save)
    LinearLayout mBtnSave;

    @BindView(R.id.item_post_layout_doc_info)
    LinearLayout mLayoutDocInfo;

    @BindView(R.id.item_post_layout_user_info)
    RelativeLayout mLayoutUserInfo;

    @BindView(R.id.item_post_img_like)
    ImageView mImgLike;

    @BindView(R.id.item_post_img_save)
    ImageView mImgSave;

    private OnMoveToDocDetailScreenListener mToDocDetail;
    private boolean isLiked = false;
    private boolean isSaved = false;

    public PostsViewHolder(View itemView, OnMoveToDocDetailScreenListener mToDetailScreen) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      this.mToDocDetail = mToDetailScreen;
      addEvent();
    }

    public void addEvent() {
      mLayoutDocInfo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          mToDetailScreen.onMoveToDocDetail(mPostList.get(getAdapterPosition()).getmId(), "detail");
        }
      });

      mBtnLike.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if(isLiked == false){
            mImgLike.setImageResource(R.drawable.ic_like_blue);
            isLiked = true;
          }
          else{
            mImgLike.setImageResource(R.drawable.ic_like_gray);
            isLiked = false;
          }
        }
      });

      mBtnSave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if(isSaved == false){
            mImgSave.setImageResource(R.mipmap.ic_save_blue);
            isSaved = true;
            mToDetailScreen.onMoveToDocDetail(mPostList.get(getAdapterPosition()).getmId(), "true");
          }
          else{
            mImgSave.setImageResource(R.mipmap.ic_save);
            isSaved = false;
            mToDetailScreen.onMoveToDocDetail(mPostList.get(getAdapterPosition()).getmId(), "false");
          }
        }
      });
    }
  }

  public interface OnMoveToDocDetailScreenListener{
     void onMoveToDocDetail(int idPost, String type);
  }

  public interface OnMoveToUserDetailScreenListener{
    void onMoveToUserDetail(int idPost);
  }

  public interface OnSendPostId{
    void onSendPostId(int postId);
  }

}
