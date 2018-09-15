package giaodien.admin.doan_googbook.model;

import java.io.Serializable;

/**
 * Created by Admin on 17/10/2017.
 */

public class Image implements Serializable{

  private String mId;
  private String mString64;
//  private int mResources;

  public Image(String mId, String mString64) {
    this.mId = mId;
    this.mString64 = mString64;
  }

  public String getmId() {
    return mId;
  }

  public void setmId(String mId) {
    this.mId = mId;
  }

  public String getmString64() {
    return mString64;
  }

  public void setmString64(String mString64) {
    this.mString64 = mString64;
  }

}
