package giaodien.admin.doan_googbook.screen.home.upload;

import giaodien.admin.doan_googbook.model.Post;
import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.mylibrary.base.viper.interfaces.IInteractor;
import giaodien.admin.mylibrary.base.viper.interfaces.IPresenter;
import giaodien.admin.mylibrary.base.viper.interfaces.PresentView;
import retrofit2.Callback;

/**
 * The Upload Contract
 */
interface UploadContract {

  interface Interactor extends IInteractor<Presenter> {

    void uploadDoc(int userId, String docSubject, String docName, String docType, String docDes, String docLevel, String docUniversity, String docYear, String listImageToUpload, String fileList, Callback<SimpleResult> callback);
  }

  interface View extends PresentView<Presenter> {
    void setUpView(Post post);

    void clearView(String msg);
  }

  interface Presenter extends IPresenter<View, Interactor> {

    void uploadDoc(int userId, String docSubject, String docName, String docType, String docDes, String docLevel, String docUniversity, String docYear, String listImageToUpload, String fileList);
  }
}



