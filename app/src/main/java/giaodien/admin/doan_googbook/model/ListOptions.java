package giaodien.admin.doan_googbook.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 05/11/2017.
 */

public class ListOptions implements Serializable {

  @SerializedName("listOption")
  List<Option> list;

  public ListOptions() {
  }

  public ListOptions(List<Option> list) {
    this.list = list;
  }

  public List<Option> getList() {
    return list;
  }

  public void setList(List<Option> list) {
    this.list = list;
  }
}
