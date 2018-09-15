package giaodien.admin.doan_googbook.network;

import giaodien.admin.doan_googbook.result.ListPostsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Admin on 30/10/2017.
 */

public interface MockApi {

  @GET("getLastestNewData")
  Call<ListPostsResponse> getLastestNewData();

}
