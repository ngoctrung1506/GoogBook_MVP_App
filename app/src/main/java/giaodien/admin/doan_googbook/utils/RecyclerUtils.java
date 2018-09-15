package giaodien.admin.doan_googbook.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

/**
 * Created by Admin on 04/10/2017.
 */

public class RecyclerUtils {

  public static void setUpVerticalRecyclerView(Context context, SuperRecyclerView superRecyclerView){
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
    layoutManager.setAutoMeasureEnabled(true);
    superRecyclerView.setLayoutManager(layoutManager);
    superRecyclerView.setClipToPadding(false);
    superRecyclerView.setFitsSystemWindows(true);
  }

  public static void setUpVerticalRecyclerView(Context context, RecyclerView recyclerView){
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setClipToPadding(false);
    recyclerView.setFitsSystemWindows(true);
  }

  public static void setUpHorizontalRecyclerView(Context context, RecyclerView recyclerView){
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setClipToPadding(false);
    recyclerView.setFitsSystemWindows(true);
  }

  public static void setUpGridRecyclerView(Context context, SuperRecyclerView superRecyclerView, int span){
    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,span);
    superRecyclerView.setLayoutManager(layoutManager);
    superRecyclerView.setClipToPadding(false);
    superRecyclerView.setFitsSystemWindows(true);
  }

 public static void setUpGridRecyclerView(Context context, RecyclerView recyclerView, int span){
    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,span);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setClipToPadding(false);
    recyclerView.setFitsSystemWindows(true);
  }


}
