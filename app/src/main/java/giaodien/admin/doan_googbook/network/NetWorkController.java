package giaodien.admin.doan_googbook.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import giaodien.admin.doan_googbook.constants.Constants;
import giaodien.admin.doan_googbook.model.ListItemPost;
import giaodien.admin.doan_googbook.model.ListOptions;
import giaodien.admin.doan_googbook.model.Option;
import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.result.ListBooksResponse;
import giaodien.admin.doan_googbook.result.ListPostsResponse;
import giaodien.admin.doan_googbook.result.MyPostResponse;
import giaodien.admin.doan_googbook.result.PostResponse;
import giaodien.admin.doan_googbook.result.UserInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 11/10/2017.
 */

public class NetWorkController {

  private static volatile GoogBookAPI googBookAPI;
  private static volatile MockApi mockAPI;

  public static GoogBookAPI getAPIBuilder(){
    if(googBookAPI == null){
      Gson gson = new GsonBuilder().setLenient().create();
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(Constants.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build();
      googBookAPI = retrofit.create(GoogBookAPI.class);
    }
    return googBookAPI;
  }

  public static MockApi getMockAPIBuilder(){
    if(mockAPI == null){
      Gson gson = new GsonBuilder().setLenient().create();
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(Constants.BASE_MOCK_URL)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .build();
      mockAPI = retrofit.create(MockApi.class);
    }
    return mockAPI;
  }

  public static void getLastestNewData(Callback<ListPostsResponse> callback){
    Call<ListPostsResponse> call = getAPIBuilder().getLastestNewData();
    call.enqueue(callback);
  }

  public static void getDocDetail(int postId, Callback<PostResponse> callback){
    Call<PostResponse> call = getAPIBuilder().getPostById(postId);
    Log.d("url", call.request().url().toString());
    call.enqueue(callback);
  }

  public static void getDataForMainSrcreen(Callback<ListBooksResponse> callback){
    Call<ListBooksResponse> call = getAPIBuilder().getDataForMainScreen();
    call.enqueue(callback);
  }

  public static void showAllByType(String type, Callback<ListItemPost> callback) {
    Call<ListItemPost> call = getAPIBuilder().getAllPostByType(type);
    call.enqueue(callback);
  }

  public static void getAllPostByCondition(ListOptions list, Callback<ListOptions> callback){
    ListOptions listOptions = new ListOptions();
		  List<Option> optionList = new ArrayList<>();
		  Option option = new Option("name", "Java");
		  Option option1 = new Option("name", "Trung");
		  Option option2 = new Option("type", "Sách");
		  Option option3 = new Option("level", "Trung học Phổ thông");
    optionList.add(option);
    optionList.add(option1);
    optionList.add(option2);
    optionList.add(option3);
		  listOptions.setList(optionList);
    Call<ListOptions> call = getAPIBuilder().getAllPostByCondition(listOptions);
    call.equals(callback);
  }

  public static void signUp(String account, String password, String userName, String email, String phone, String address, String birthDay, String sex, String mImageBase64Avatar, String mImageBase64BackGround, Callback<SimpleResult> callback) {
    Call<SimpleResult> call = getAPIBuilder().signUp(account, password, userName, email, phone, address, birthDay, sex, mImageBase64Avatar, mImageBase64BackGround);
    call.enqueue(callback);
  }

  public static void login(String account, String password, Callback<SimpleResult> callback){
    Call<SimpleResult> call = getAPIBuilder().login(account, password);
    call.enqueue(callback);
  }

  public static void getUserPostByUserId(int userId, Callback<MyPostResponse> callback){
    Call<MyPostResponse> call = getAPIBuilder().getUserPostByUserId(userId);
    Log.d("url", call.request().url().toString());
    call.enqueue(callback);
  }

  public static void getSavedPostByUserId(int userId, Callback<MyPostResponse> callback){
    Call<MyPostResponse> call = getAPIBuilder().getSavedPostByUserId(userId);
    Log.d("url", call.request().url().toString());
    call.enqueue(callback);
  }

  public static void onSave(int userId, int postId, int isSaved, Callback<SimpleResult> callback){
    Call<SimpleResult> call = getAPIBuilder().onSave(userId, postId, isSaved);
    call.enqueue(callback);
  }

  public static void getUserInfoById(int userId, Callback<UserInfo> callback){
    Call<UserInfo> call = getAPIBuilder().getUserInfoById(userId);
    call.enqueue(callback);
  }

  public static void onUpdate(int userId, String title, String value, Callback<SimpleResult> callback){
    Call<SimpleResult> call = getAPIBuilder().onUpdate(userId, title, value);
    call.enqueue(callback);
  }

 public static void onTest(SimpleResult simpleResult, Callback<SimpleResult> callback){
    Call<SimpleResult> call = getAPIBuilder().onTest(simpleResult);
    Log.d("url",call.request().url().toString());
    call.enqueue(callback);
  }


  public static void uploadDoc(int userId, String docSubject, String docName, String docType, String docDes, String docLevel, String docUniversity, String docYear, String listImageToUpload, String fileList, Callback<SimpleResult> callback) {
    Call<SimpleResult> call = getAPIBuilder().createNewPost(userId, docSubject, docName, docType, docDes, docLevel, docUniversity, docYear, listImageToUpload, fileList);
    call.enqueue(callback);
  }
}
