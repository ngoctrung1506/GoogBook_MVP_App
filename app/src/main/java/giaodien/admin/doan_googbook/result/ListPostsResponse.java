package giaodien.admin.doan_googbook.result;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import giaodien.admin.doan_googbook.model.Post;
import giaodien.admin.doan_googbook.model.SimpleResult;

/**
 * Created by Admin on 09/10/2017.
 */

public class ListPostsResponse extends SimpleResult implements Serializable {

  @SerializedName("data")
  private ArrayList<Post> mListPosts;

  public ArrayList<Post> getmListPosts() {
    return mListPosts;
  }


}
