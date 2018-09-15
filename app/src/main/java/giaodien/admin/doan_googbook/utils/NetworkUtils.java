package giaodien.admin.doan_googbook.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Admin on 11/11/2017.
 */

public class NetworkUtils {
  public static final String TAG = NetworkUtils.class.getSimpleName();
  public static final int TYPE_WIFI = 1;
  public static final int TYPE_MOBILE = 2;
  public static final int TYPE_NOT_CONNECTED = 0;

  public static int getConnectivityStatus(Context context) {
    ConnectivityManager cm = (ConnectivityManager) context
        .getSystemService(Context.CONNECTIVITY_SERVICE);

    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    if (null != activeNetwork) {
      if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
        Log.d(TAG, "activeNetwork TYPE_WIFI");
        return TYPE_WIFI;
      }

      if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
        Log.d(TAG, "activeNetwork TYPE_MOBILE");
        return TYPE_MOBILE;
      }
    }
    return TYPE_NOT_CONNECTED;
  }

  public static boolean isConnect(Context context) {
    boolean isCon = false;
    ConnectivityManager cm = (ConnectivityManager) context
        .getSystemService(Context.CONNECTIVITY_SERVICE);
    if (cm != null) {
      NetworkInfo networkInfo = cm.getActiveNetworkInfo();
      if (networkInfo != null && networkInfo.isConnected()) {
        isCon = true;
      }
    }
    return isCon;
  }

}
