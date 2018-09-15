package giaodien.admin.doan_googbook.screen.home.bookstore;

import android.util.Log;

import giaodien.admin.doan_googbook.model.ListOptions;
import giaodien.admin.doan_googbook.result.ListBooksResponse;
import giaodien.admin.doan_googbook.screen.home.allbook.SeeingAllBookPresenter;
import giaodien.admin.doan_googbook.screen.home.docdetail.DocDetailPresenter;
import giaodien.admin.mylibrary.base.viper.Presenter;
import giaodien.admin.mylibrary.base.viper.interfaces.ContainerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The BookStore Presenter
 */
public class BookStorePresenter extends Presenter<BookStoreContract.View, BookStoreContract.Interactor>
    implements BookStoreContract.Presenter {

  public BookStorePresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public BookStoreContract.View onCreateView() {
    return BookStoreFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public BookStoreContract.Interactor onCreateInteractor() {
    return new BookStoreInteractor(this);
  }

  @Override
  public void move(String type) {
    new SeeingAllBookPresenter(mContainerView).setType(type).pushView();
  }

  @Override
  public void getDataForList() {
    mInteractor.getDataForList(new Callback<ListBooksResponse>() {
      @Override
      public void onResponse(Call<ListBooksResponse> call, Response<ListBooksResponse> response) {
        if(response.isSuccessful() && response.body() != null){
          mView.setDataForMainScreen(response.body().getListBooksArrayList());
        }
        else Log.d("error", "Body is null");
      }

      @Override
      public void onFailure(Call<ListBooksResponse> call, Throwable t) {
//        Log.d("error", t.getMessage());
      }
    });
  }

  @Override
  public void showByPostId(int postId) {
    new DocDetailPresenter(mContainerView).setIdPost(postId).pushView();
  }

  @Override
  public void sendConditionToServer(ListOptions listCondition) {
     mInteractor.sendConditionToServer(listCondition, new Callback<ListOptions>() {
       @Override
       public void onResponse(Call<ListOptions> call, Response<ListOptions> response) {
//         if(response.isSuccessful() && response.body() != null) {
//           if (response.body().getErrrorCode() == 0) {
//             mView.setUpViewForChoosenOption(response.body().getmItemPosts());
//           } else Log.d("error", "No data");
//         }
//         else
           Log.d("error", response.message());
       }

       @Override
       public void onFailure(Call<ListOptions> call, Throwable t) {
             Log.d("error", t.getMessage());
       }
     });
    Log.d("error", "in but nothing");
  }
}
