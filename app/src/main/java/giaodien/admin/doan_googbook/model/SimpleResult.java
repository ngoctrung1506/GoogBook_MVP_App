package giaodien.admin.doan_googbook.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 09/10/2017.
 */

public class SimpleResult implements Serializable {

  @SerializedName("errorCode")
  private int errrorCode;

  @SerializedName("msg")
  private String msg;

  @SerializedName("userId")
  private int userId;

  @SerializedName("user_avatar")
  private String userAvatar;

  @SerializedName("user_background")
  private String userBackground;


  public int getErrrorCode() {
    return errrorCode;
  }

  public String getMsg() {
    return msg;
  }

  public int getUserId() {
    return userId;
  }

  public String getUserAvatar() {
    return userAvatar;
  }

  public String getUserBackground() {
    return userBackground;
  }

  public void setErrrorCode(int errrorCode) {
    this.errrorCode = errrorCode;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }
}
