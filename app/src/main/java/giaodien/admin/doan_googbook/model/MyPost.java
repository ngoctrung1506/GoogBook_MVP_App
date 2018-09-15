package giaodien.admin.doan_googbook.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 13/11/2017.
 */

public class MyPost implements Serializable {

  @SerializedName("id")
  private int id;

  @SerializedName("doc_name")
  private String mDocName;

  @SerializedName("doc_type")
  private String mDocType;

  @SerializedName("doc_level")
  private String mDocLevel;

  @SerializedName("doc_university")
  private String mDocUniversity;

  @SerializedName("post_day")
  private String mDocPostDay;

  @SerializedName("doc_image")
  private String mDocImage;

  public int getId() {
    return id;
  }

  public String getmDocName() {
    return mDocName;
  }

  public String getmDocType() {
    return mDocType;
  }

  public String getmDocLevel() {
    return mDocLevel;
  }

  public String getmDocUniversity() {
    return mDocUniversity;
  }

  public String getmDocPostDay() {
    return mDocPostDay;
  }

  public String getmDocImage() {
    return mDocImage;
  }
}
