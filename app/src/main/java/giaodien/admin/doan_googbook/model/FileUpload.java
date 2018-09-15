package giaodien.admin.doan_googbook.model;

import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Admin on 31/10/2017.
 */

public class FileUpload implements Serializable {

  @SerializedName("name")
  private String name;

  @SerializedName("data")
  private File data;

  public FileUpload() {
  }

  public FileUpload(String name, File data) {
    this.name = name;
    this.data = data;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public File getData() {
    return data;
  }

  public void setData(File data) {
    this.data = data;
  }
}
