package giaodien.admin.doan_googbook.network;

import giaodien.admin.doan_googbook.model.ListItemPost;
import giaodien.admin.doan_googbook.model.ListOptions;
import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.result.ListBooksResponse;
import giaodien.admin.doan_googbook.result.ListPostsResponse;
import giaodien.admin.doan_googbook.result.MyPostResponse;
import giaodien.admin.doan_googbook.result.PostResponse;
import giaodien.admin.doan_googbook.result.UserInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Admin on 11/10/2017.
 */

public interface GoogBookAPI {

  @GET("createPost")
  Call<SimpleResult> createPost(@Field("user_id") String userId);

  @GET("post/getLastestNewData")
  Call<ListPostsResponse> getLastestNewData();

  @GET("user/getUserInfoById/{userId}")
  Call<UserInfo> getUserInfoById(@Path("userId") int userId);

  @GET("post/getPostById/{postId}")
  Call<PostResponse> getPostById(@Path("postId") int postId);

  @FormUrlEncoded
  @POST("post/createNewPost")
  Call<SimpleResult> createNewPost(@Field("userId") int userId,
                                   @Field("docSubject") String docSubject, @Field("docName") String docName,
                                   @Field("docType") String docType, @Field("docDes") String docDes,
                                   @Field("docLevel") String docLevel, @Field("docUniversity") String docUniversity,
                                   @Field("docYear") String docYear, @Field("docImages") String listImages, @Field("docFiles") String listFiles);

  @GET("post/docstore/getDataForMainScreen")
  Call<ListBooksResponse> getDataForMainScreen();

  @GET("post/docstore/getAllPostByType/{postType}")
  Call<ListItemPost> getAllPostByType(@Path("postType") String postType);

  @Headers("Content-Type: application/json")
  @POST("post/docstore/getAllPostByOption")
  Call<ListOptions> getAllPostByCondition(@Body ListOptions listOption);

  @FormUrlEncoded
  @POST("user/signUp")
  Call<SimpleResult> signUp(@Field("account") String account, @Field("password") String password,
                            @Field("userName") String userName, @Field("email") String email,
                            @Field("phone") String phone, @Field("address") String address,
                            @Field("birthDay") String birthDay, @Field("sex") String sex,
                            @Field("base64Avatar") String base64Avatar, @Field("base64BackGround") String base64BackGround);

  @FormUrlEncoded
  @POST("user/login")
  Call<SimpleResult> login(@Field("account") String account, @Field("password") String password);

  @FormUrlEncoded
  @POST("user/updateInfo")
  Call<SimpleResult> onUpdate(@Field("userId") int userId, @Field("title") String title, @Field("value") String value);

  @GET("post/getUserPostByUserId/{userId}")
  Call<MyPostResponse> getUserPostByUserId(@Path("userId") int userId);

  @GET("interaction/getSavedPostByUserId/{userId}")
  Call<MyPostResponse> getSavedPostByUserId(@Path("userId") int userId);

  @FormUrlEncoded
  @POST("interaction/onSave")
  Call<SimpleResult> onSave(@Field("userId") int userId, @Field("postId") int postId, @Field("isSaved") int isSaved);

  @POST("user/test")
  Call<SimpleResult> onTest(@Body SimpleResult simpleResult);


}
