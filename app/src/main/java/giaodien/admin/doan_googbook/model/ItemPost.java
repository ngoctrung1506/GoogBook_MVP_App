package giaodien.admin.doan_googbook.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 04/10/2017.
 */

public class ItemPost implements Serializable {

  @SerializedName("id")
  private int id;

  @SerializedName("doc_university")
  private String mUniversity;

  @SerializedName("doc_image")
  private String mImg;

  @SerializedName("doc_name")
  private String mName;

  @SerializedName("doc_author")
  private String mAuthor;

  public ItemPost() {
  }

  public ItemPost(String mImg, String mName, String mAuthor) {
    this.mImg = mImg;
    this.mName = mName;
    this.mAuthor = mAuthor;
  }

  public int getId() {
    return id;
  }

  public String getmUniversity() {
    return mUniversity;
  }

  public String getmImg() {
    return mImg;
  }

  public String getmName() {
    return mName;
  }

  public String getmAuthor() {
    return mAuthor;
  }
}
