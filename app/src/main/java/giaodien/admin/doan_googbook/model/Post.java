package giaodien.admin.doan_googbook.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 09/10/2017.
 */

public class Post implements Serializable {

  @SerializedName("id")
  private int mId;

  @SerializedName("user_id")
  private int mUserId;

  @SerializedName("user_avatar")
  private String mAvatar;

  @SerializedName("user_name")
  private String mUserName;

  @SerializedName("user_account")
  private String mUserAccount;

  @SerializedName("date")
  private String mTime;

  @SerializedName("doc_name")
  private String mDocName;

  @SerializedName("doc_type")
  private String mDocType;

  @SerializedName("doc_subject")
  private String mDocSubject;

  @SerializedName("doc_year")
  private String mDocYear;

  @SerializedName("doc_level")
  private String mDocLevel;

  @SerializedName("doc_university")
  private String mDocUniversity;

  @SerializedName("doc_list_images")
  private List<String> mDocImages;

  @SerializedName("doc_first_image")
  private String mDocFirstImage;

  @SerializedName("doc_first_file")
  private String mDocFirstFile;

  @SerializedName("doc_files")
  private ArrayList<String> mDocFileUploads;

  @SerializedName("doc_des")
  private String mDocDes;

  @SerializedName("user_email")
  private String mUserEmail;

  @SerializedName("user_phone")
  private String mUserPhone;

  public int getmId() {
    return mId;
  }

  public String getmAvatar() {
    return mAvatar;
  }

  public String getmUserName() {
    return mUserName;
  }

  public String getmTime() {
    return mTime;
  }

  public String getmDocName() {
    return mDocName;
  }

  public String getmLevel() {
    return mDocLevel;
  }

  public String getmUniversity() {
    return mDocUniversity;
  }

  public List<String> getmImages() {
    return mDocImages;
  }

  public String getmDes() {
    return mDocDes;
  }

  public String getmUserEmail() {
    return mUserEmail;
  }

  public String getmUserPhone() {
    return mUserPhone;
  }

  public String getmDocType() {
    return mDocType;
  }

  public String getmDocSubject() {
    return mDocSubject;
  }

  public String getmDocYear() {
    return mDocYear;
  }

  public String getmDocLevel() {
    return mDocLevel;
  }

  public String getmDocUniversity() {
    return mDocUniversity;
  }

  public List<String> getmDocImages() {
    return mDocImages;
  }


  public String getmUserAccount() {
    return mUserAccount;
  }

  public String getmDocDes() {
    return mDocDes;
  }

  public ArrayList<String> getmDocFileUploads() {
    return mDocFileUploads;
  }

  public int getmUserId() {
    return mUserId;
  }

  public String getmDocFirstImage() {
    return mDocFirstImage;
  }

  public String getmDocFirstFile() {
    return mDocFirstFile;
  }
}
