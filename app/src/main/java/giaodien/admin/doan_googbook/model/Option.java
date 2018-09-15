package giaodien.admin.doan_googbook.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 04/11/2017.
 */

public class Option implements Serializable {

  @SerializedName("title")
  private String title;

  @SerializedName("attribute")
  private String attribute;

  public Option(String title, String attribute) {
    this.title = title;
    this.attribute = attribute;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAttribute() {
    return attribute;
  }

  public void setAttribute(String attribute) {
    this.attribute = attribute;
  }
}
