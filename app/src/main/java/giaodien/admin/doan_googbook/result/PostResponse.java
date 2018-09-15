package giaodien.admin.doan_googbook.result;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import giaodien.admin.doan_googbook.model.Post;
import giaodien.admin.doan_googbook.model.SimpleResult;

/**
 * Created by Admin on 27/10/2017.
 */

public class PostResponse extends SimpleResult implements Serializable {

  @SerializedName("data")
  private Post mPost;

  public Post getData() {
    return mPost;
  }

}
