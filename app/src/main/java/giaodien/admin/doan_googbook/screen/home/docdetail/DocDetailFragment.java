package giaodien.admin.doan_googbook.screen.home.docdetail;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.frosquivel.magicalcamera.Utilities.ConvertSimpleImage;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import butterknife.BindView;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.adapter.FileAdapter;
import giaodien.admin.doan_googbook.adapter.SlideImageAdapter;
import giaodien.admin.doan_googbook.constants.Constants;
import giaodien.admin.doan_googbook.dialog.EditOptionDialog;
import giaodien.admin.doan_googbook.model.Post;
import giaodien.admin.doan_googbook.utils.RecyclerUtils;
import giaodien.admin.doan_googbook.utils.SharedPreferUtils;
import giaodien.admin.doan_googbook.widget.CustomHeaderView;
import giaodien.admin.mylibrary.base.viper.ViewFragment;

/**
 * The DocDetail Fragment
 */
public class DocDetailFragment extends ViewFragment<DocDetailContract.Presenter>
    implements DocDetailContract.View , EditOptionDialog.OnEditOptionListener{

  @BindView(R.id.doc_detail_header_view)
  CustomHeaderView mHeaderView;

  @BindView(R.id.doc_detail_view_pager)
  ViewPager mViewPager;

  @BindView(R.id.doc_detail_image_user_avatar)
  CircularImageView mUserAvatar;

  @BindView(R.id.doc_detail_txt_user_name)
  TextView mUserName;

  @BindView(R.id.doc_detail_txt_user_email)
  TextView mUserEmail;

  @BindView(R.id.doc_detail_txt_user_phone)
  TextView mUserPhone;

  @BindView(R.id.doc_detail_txt_post_time)
  TextView mPostTime;

  @BindView(R.id.doc_detail_txt_doc_name)
  TextView mDocName;

  @BindView(R.id.doc_detail_txt_doc_level)
  TextView mDocLevel;

  @BindView(R.id.doc_detail_txt_doc_subject)
  TextView mDocSubject;

  @BindView(R.id.doc_detail_txt_doc_university)
  TextView mDocUniversity;

  @BindView(R.id.doc_detail_txt_doc_type)
  TextView mDocType;

  @BindView(R.id.doc_detail_row_doc_type)
  TableRow mRowType;

  @BindView(R.id.doc_detail_txt_doc_year)
  TextView mDocYear;

  @BindView(R.id.doc_detail_row_university)
  TableRow mRowUniversity;

  @BindView(R.id.doc_detail_row_year)
  TableRow mRowYear;

  @BindView(R.id.doc_detail_row_doc_name)
  TableRow mRowName;

  @BindView(R.id.doc_detail_row_doc_level)
  TableRow mRowLevel;

  @BindView(R.id.doc_detail_row_doc_subject)
  TableRow mRowSubject;

  @BindView(R.id.doc_detail_row_user_email)
  TableRow mRowEmail;

  @BindView(R.id.doc_detail_row_user_phone)
  TableRow mRowPhone;

  @BindView(R.id.doc_detail_txt_des)
  TextView mDocDes;

  @BindView(R.id.doc_detail_recycler_doc_file)
  RecyclerView mRecyelerDocFile;

  @BindView(R.id.doc_detail_btn_like)
  LinearLayout mBtnLike;

  @BindView(R.id.doc_detail_btn_share)
  LinearLayout mBtnShare;

  @BindView(R.id.doc_detail_btn_save)
  LinearLayout mBtnSave;

  @BindView(R.id.doc_detail_img_like)
  ImageView mImgLike;

  @BindView(R.id.doc_detail_img_save)
  ImageView mImgSave;

  @BindView(R.id.doc_detail_recycler_comment)
  RecyclerView mRecyclerComment;

  @BindView(R.id.doc_detail_edt_input)
  EditText mEdtInput;

  @BindView(R.id.doc_detail_btn_send)
  Button mBtnSend;

  @BindView(R.id.doc_detail_img_edit)
  ImageView mImgEdit;

  @BindView(R.id.doc_detail_txt_no_data)
  TextView mTxtNoData;

  @BindView(R.id.doc_detail_app_bar)
  AppBarLayout mAppBar;

  @BindView(R.id.doc_detail_nestedscroll)
  NestedScrollView mScrollView;

  private SlideImageAdapter mSlideImageAdapter;
  private FileAdapter mFileAdapter;
  private ArrayList<String> mListImages;
  private ArrayList<String> mListDocFiles;
  private static Post mPost;
  private boolean isLiked = false;
  private boolean isSaved = false;
  private static int postId = 0;
  private static int userId = 0;

  public static DocDetailFragment getInstance() {
    return new DocDetailFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_doc_detail;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    mImgEdit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showDialog();
      }
    });

    mHeaderView.setOnLeftToggleListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.back();
      }
    });

    mBtnLike.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(mImgLike.getBackground().equals(ContextCompat.getDrawable(getContext(),R.drawable.ic_like_blue)))
          mImgLike.setBackgroundResource(R.drawable.ic_like_gray);
        else mImgLike.setBackgroundResource(R.drawable.ic_like_blue);
      }
    });

    mBtnLike.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(isLiked == false){
          mImgLike.setImageResource(R.drawable.ic_like_blue);
          isLiked = true;
        }
        else{
          mImgLike.setImageResource(R.drawable.ic_like_gray);
          isLiked = false;
        }
      }
    });

    userId = SharedPreferUtils.getInt(getContext(), Constants.USER_ID);
    mBtnSave.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(isSaved == false){
          mImgSave.setImageResource(R.mipmap.ic_save_blue);
          isSaved = true;
          mPresenter.onSave(userId, postId, Constants.TRUE);
        }
        else{
          mImgSave.setImageResource(R.mipmap.ic_save);
          isSaved = false;
          mPresenter.onSave(userId, postId, Constants.FALSE);

        }
      }
    });

  }

  public void showDialog() {
    new EditOptionDialog(getContext(), this).show();
  }

  @Override
  public void setUpData(int mIdPost) {
    mPresenter.getDocDetail(mIdPost);
    showProgress();
    mListImages = new ArrayList<>();
    mSlideImageAdapter = new SlideImageAdapter(getViewContext(),  mListImages);
    mListDocFiles = new ArrayList<>();
    RecyclerUtils.setUpVerticalRecyclerView(getViewContext(), mRecyelerDocFile);
    mFileAdapter = new FileAdapter(getViewContext(), mListDocFiles);
    mRecyelerDocFile.setAdapter(mFileAdapter);
    mViewPager.setAdapter(mSlideImageAdapter);
  }

  @Override
  public void setUpView(final Post data) {
//      mUserAvatar.setText(data.getmUserName());
    mTxtNoData.setVisibility(View.GONE);
    this.mPost = data;
    this.postId = data.getmId();
    Glide.with(this).load(ConvertSimpleImage.stringBase64ToBytes(data.getmAvatar())).asBitmap().into(mUserAvatar);
     if(data.getmUserId() == SharedPreferUtils.getInt(getContext(), Constants.USER_ID)) mImgEdit.setVisibility(View.VISIBLE);
    else mImgEdit.setVisibility(View.GONE);
    setData(data.getmDocType(), mDocType, mRowType);
    setData(data.getmUserAccount(), mUserName, mRowName);
    setData(data.getmUserPhone(), mUserPhone, mRowPhone);
    setData(data.getmUserEmail(), mUserEmail, mRowEmail);
//    setData(data.getmTime(), mPostTime, mPostTime);
    mPostTime.setText("10/12/2017");
    setData(data.getmDocName(), mDocName, mRowName);
    setData(data.getmDocSubject(), mDocSubject, mRowSubject);
    setData(data.getmDocLevel(), mDocLevel, mRowLevel);
    setData(data.getmUniversity(), mDocUniversity, mRowUniversity);
    setData(data.getmDocYear(), mDocYear, mRowYear);
    setData(data.getmDes(), mDocDes, mDocDes);

    if(data.getmDocFileUploads() != null)
    mFileAdapter.updateList(data.getmDocFileUploads());
    else{
      if(data.getmDocFirstFile()!=null) {
        mListDocFiles.add(data.getmDocFirstFile());
        mFileAdapter.updateList(mListDocFiles);
      }
    }

    if(data.getmDocImages() != null) {
      mSlideImageAdapter.updateListImages(data.getmImages());
    }
    else {
      if(data.getmDocFirstImage()!=null) {
        mListImages.add(data.getmDocFirstImage());
        mSlideImageAdapter.updateListImages(mListImages);
      }
      else mViewPager.setVisibility(View.GONE);

    }
    mUserPhone.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showPhone(data.getmUserPhone());
      }
    });

    mUserEmail.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showEmail(data.getmUserEmail());
      }
    });

  }

  @Override
  public void showError(String msg) {
     mAppBar.setVisibility(View.GONE);
     mScrollView.setVisibility(View.GONE);
    mTxtNoData.setVisibility(View.VISIBLE);
     mTxtNoData.setText(msg);
  }

  private void showEmail(String email) {
    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
//    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
//    emailIntent.putExtra(Intent.EXTRA_TEXT, "");
//emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text
    startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
  }

  private void showPhone(String phone) {
    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
    startActivity(intent);
  }

  public void setData(String data, TextView txt, View view){
    if(data != null && !data.equals("") ) txt.setText(data);
    else view.setVisibility(View.GONE);

    if((txt.equals(mUserPhone) || txt.equals(mUserEmail)) && getContext()!= null){
      txt.setTextColor(ContextCompat.getColor(getContext(),R.color.mainColor));
      txt.setPaintFlags(txt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
  }

  @Override
  public void editListener(String action) {
        if(action.equals("edit")){
          mPresenter.sentToEditScreen(mPost);
        }
        else {
          Toast.makeText(getContext(), "Da xoa", Toast.LENGTH_SHORT).show();
        }
  }
}
