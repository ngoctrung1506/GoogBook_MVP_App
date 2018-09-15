package giaodien.admin.doan_googbook.result;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import giaodien.admin.doan_googbook.model.MyPost;
import giaodien.admin.doan_googbook.model.SimpleResult;

/**
 * Created by Admin on 13/11/2017.
 */

public class MyPostResponse extends SimpleResult implements Serializable {

  @SerializedName("listMyPost")
  List<MyPost> myPostList;

  public List<MyPost> getMyPostList() {
    return myPostList;
  }
}
