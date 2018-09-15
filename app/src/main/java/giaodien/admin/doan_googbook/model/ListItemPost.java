package giaodien.admin.doan_googbook.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Admin on 04/10/2017.
 */

public class ListItemPost extends SimpleResult implements Serializable {

  @SerializedName("type")
  private String mType;

  @SerializedName("listItemPost")
  private ArrayList<ItemPost> mItemPosts;

  public String getmType() {
    return mType;
  }

  public ArrayList<ItemPost> getmItemPosts() {
    return mItemPosts;
  }

  public void setmType(String mType) {
    this.mType = mType;
  }

  public void setmItemPosts(ArrayList<ItemPost> mItemPosts) {
    this.mItemPosts = mItemPosts;
  }

  public ListItemPost(String mType, ArrayList<ItemPost> mItemPosts) {
    this.mType = mType;
    this.mItemPosts = mItemPosts;
  }

  public ListItemPost() {
  }
}
