package giaodien.admin.doan_googbook.result;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import giaodien.admin.doan_googbook.model.SimpleResult;

/**
 * Created by Admin on 15/11/2017.
 */

public class UserInfo extends SimpleResult implements Serializable {

  @SerializedName("password")
  private String password;

  @SerializedName("name")
  private String name;

  @SerializedName("email")
  private String email;

  @SerializedName("phone")
  private String phone;

  @SerializedName("sex")
  private String sex;

  @SerializedName("year_born")
  private String yearBorn;

  @SerializedName("address")
  private String address;

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getSex() {
    return sex;
  }

  public String getYearBorn() {
    return yearBorn;
  }

  public String getAddress() {
    return address;
  }
}
