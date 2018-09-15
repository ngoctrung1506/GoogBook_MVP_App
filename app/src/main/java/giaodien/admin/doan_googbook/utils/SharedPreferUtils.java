package giaodien.admin.doan_googbook.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Admin on 11/11/2017.
 */

public class SharedPreferUtils {

  private static SharedPreferences sharedPreferences;
  private static SharedPreferences.Editor editor;

  public static SharedPreferences.Editor createSharedPrefencesEditor(Context context){
    if(editor == null){
      sharedPreferences = context.getSharedPreferences("loginSharedPreferences", Context.MODE_PRIVATE);
     editor = sharedPreferences.edit();
    }
    return editor;
  }

  public static SharedPreferences createSharedPrefences(Context context){
    if(sharedPreferences == null){
      sharedPreferences = context.getSharedPreferences("loginSharedPreferences", Context.MODE_PRIVATE);
    }
    return sharedPreferences;
  }

  public static void putString(Context context, String key, String value){
    createSharedPrefencesEditor(context).putString(key, value).commit();
  }

  public static void putInt(Context context, String key, int value){
    createSharedPrefencesEditor(context).putInt(key, value).commit();
  }

  public static void putBoolean(Context context, String key, boolean value){
    createSharedPrefencesEditor(context).putBoolean(key, value).commit();
  }

  public static String getString(Context context, String key){
    return createSharedPrefences(context).getString(key, "");
  }

  public static int getInt(Context context, String key){
    return createSharedPrefences(context).getInt(key, 0);
  }

  public static boolean getBoolean(Context context, String key){
    return createSharedPrefences(context).getBoolean(key, false);
  }


}
