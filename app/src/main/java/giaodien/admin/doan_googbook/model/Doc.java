package giaodien.admin.doan_googbook.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 24/10/2017.
 */

public class Doc implements Serializable {

  private String mDocType;

  private String mDocSubject;

  private String mDocYear;

  private String mDocLevel;

  private String mDocUniversity;

  private List<String> mDocImages;

  private List<FileUpload> mDocFileUploads;

  private String mDocDes;

  private String mDocName;

  public Doc() {
  }

  public Doc(String mDocType, String mDocSubject, String mDocYear, String mDocLevel, String mDocUniversity, List<String> mDocImages, List<FileUpload> mDocFileUploads, String mDocDes, String mDocName) {
    this.mDocType = mDocType;
    this.mDocSubject = mDocSubject;
    this.mDocYear = mDocYear;
    this.mDocLevel = mDocLevel;
    this.mDocUniversity = mDocUniversity;
    this.mDocImages = mDocImages;
    this.mDocFileUploads = mDocFileUploads;
    this.mDocDes = mDocDes;
    this.mDocName = mDocName;
  }

  public Doc(String mDocName) {
    this.mDocName = mDocName;
  }

  public String getmDocName() {
    return mDocName;
  }

  public void setmDocName(String mDocName) {
    this.mDocName = mDocName;
  }

  public String getmDocType() {
    return mDocType;
  }

  public void setmDocType(String mDocType) {
    this.mDocType = mDocType;
  }

  public String getmDocSubject() {
    return mDocSubject;
  }

  public void setmDocSubject(String mDocSubject) {
    this.mDocSubject = mDocSubject;
  }

  public String getmDocYear() {
    return mDocYear;
  }

  public void setmDocYear(String mDocYear) {
    this.mDocYear = mDocYear;
  }

  public String getmDocLevel() {
    return mDocLevel;
  }

  public void setmDocLevel(String mDocLevel) {
    this.mDocLevel = mDocLevel;
  }

  public String getmDocUniversity() {
    return mDocUniversity;
  }

  public void setmDocUniversity(String mDocUniversity) {
    this.mDocUniversity = mDocUniversity;
  }

  public List<String> getmDocImages() {
    return mDocImages;
  }

  public void setmDocImages(List<String> mDocImages) {
    this.mDocImages = mDocImages;
  }

  public List<FileUpload> getmDocFileUploads() {
    return mDocFileUploads;
  }

  public void setmDocFileUploads(List<FileUpload> mDocFileUploads) {
    this.mDocFileUploads = mDocFileUploads;
  }

  public String getmDocDes() {
    return mDocDes;
  }

  public void setmDocDes(String mDocDes) {
    this.mDocDes = mDocDes;
  }
}
