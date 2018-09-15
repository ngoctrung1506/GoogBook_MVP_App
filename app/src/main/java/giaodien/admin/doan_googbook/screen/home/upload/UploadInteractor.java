package giaodien.admin.doan_googbook.screen.home.upload;

import giaodien.admin.doan_googbook.model.SimpleResult;
import giaodien.admin.doan_googbook.network.NetWorkController;
import giaodien.admin.mylibrary.base.viper.Interactor;
import retrofit2.Callback;

/**
 * The Upload interactor
 */
class UploadInteractor extends Interactor<UploadContract.Presenter>
    implements UploadContract.Interactor {

  UploadInteractor(UploadContract.Presenter presenter) {
    super(presenter);
  }


  @Override
  public void uploadDoc(int userId, String docSubject, String docName, String docType, String docDes, String docLevel, String docUniversity, String docYear, String listImageToUpload, String fileList, Callback<SimpleResult> callback) {
    NetWorkController.uploadDoc(userId, docSubject, docName, docType, docDes, docLevel, docUniversity, docYear, listImageToUpload, fileList, callback);
  }
}
