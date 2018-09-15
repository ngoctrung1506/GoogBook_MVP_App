package giaodien.admin.doan_googbook.result;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import giaodien.admin.doan_googbook.model.ListItemPost;
import giaodien.admin.doan_googbook.model.SimpleResult;

/**
 * Created by Admin on 04/10/2017.
 */

public class ListBooksResponse extends SimpleResult implements Serializable  {

  @SerializedName("data")
  private ArrayList<ListItemPost> listBooksArrayList;

  public ArrayList<ListItemPost> getListBooksArrayList() {
    return listBooksArrayList;
  }
}
