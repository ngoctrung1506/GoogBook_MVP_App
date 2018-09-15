package giaodien.admin.doan_googbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.dialog.UpdateUserInfoDialog;
import giaodien.admin.doan_googbook.model.ItemUserInfo;

/**
 * Created by Admin on 15/11/2017.
 */

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.UserInfoViewHolder> {

  private Context mContext;
  private List<ItemUserInfo> mListUserInfo;
  private UpdateUserInfoDialog.OnUpdateListener mOnUpdate;

  public UserInfoAdapter(Context mContext, List<ItemUserInfo> listUserInfo, UpdateUserInfoDialog.OnUpdateListener mOnUpdate) {
    this.mContext = mContext;
    this.mListUserInfo = listUserInfo;
    this.mOnUpdate = mOnUpdate;
  }

  @Override
  public UserInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.item_user_info, parent, false);
    return new UserInfoViewHolder(view);
  }

  @Override
  public void onBindViewHolder(UserInfoViewHolder holder, int position) {
     ItemUserInfo userInfo = mListUserInfo.get(position);
     if(userInfo.getTitle() != null) holder.mTxtTitle.setText(userInfo.getTitle());
     if(userInfo.getValue() != null) holder.mTxtValue.setText(userInfo.getValue());
  }

  @Override
  public int getItemCount() {
    return mListUserInfo.size();
  }

  public void updateItem(String title, String value, int postion){
    mListUserInfo.set(postion, new ItemUserInfo(title, value));
    notifyItemChanged(postion);
    Log.d("item", title + " " + value);

  }

  public void updateList(List<ItemUserInfo> itemUserInfos){
    this.mListUserInfo = itemUserInfos;
    notifyDataSetChanged();
  }

  public class UserInfoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_user_info_root)
    RelativeLayout mRoot;

    @BindView(R.id.item_user_info_value)
    TextView mTxtValue;

    @BindView(R.id.item_user_info_title)
    TextView mTxtTitle;

    public UserInfoViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      mRoot.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          showDialog();
        }
      });
    }

    public void showDialog() {
      new UpdateUserInfoDialog(mContext, mOnUpdate, mListUserInfo.get(getAdapterPosition()).getTitle(), mListUserInfo.get(getAdapterPosition()).getValue(), getAdapterPosition()).show();

    }
  }
}
