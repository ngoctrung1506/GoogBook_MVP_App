package giaodien.admin.doan_googbook.model;

/**
 * Created by Admin on 16/11/2017.
 */

public class ItemUserInfo {

  private String title;
  private String value;

  public ItemUserInfo(String title, String value) {
    this.title = title;
    this.value = value;
  }

  public ItemUserInfo(String value) {
    this.value = value;
  }

  public String getTitle() {
    return title;
  }

  public String getValue() {
    return value;
  }
}
