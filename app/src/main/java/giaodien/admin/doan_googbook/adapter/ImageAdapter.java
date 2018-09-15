package giaodien.admin.doan_googbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frosquivel.magicalcamera.Utilities.ConvertSimpleImage;
import com.michael.easydialog.EasyDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import giaodien.admin.doan_googbook.R;

/**
 * Created by Admin on 17/10/2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{

  private Context mContext;
  private List<String> listImages;
  private OnCameraAction mCameraAction;

  public ImageAdapter(Context mContext, List<String> listImages, OnCameraAction mCameraAction) {
    this.mContext = mContext;
    this.listImages = listImages;
    this.mCameraAction = mCameraAction;
  }

  @Override
  public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View view = inflater.inflate(R.layout.item_camera_image, parent, false);
    return new ImageViewHolder(view, mContext, mCameraAction);
  }

  @Override
  public void onBindViewHolder(ImageViewHolder holder, int position) {
    String image = listImages.get(position);
    if (image.equals("")) {
      holder.mImage.setImageResource(R.mipmap.ic_camera);
      holder.mImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
    } else {
      Glide.with(mContext)
          .load(ConvertSimpleImage.stringBase64ToBytes(image))
          .asBitmap()
          .into(holder.mImage);
      holder.mImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
  }

  @Override
  public int getItemCount() {
    return listImages.size();
  }

  public List<String> getListImages(){
    return this.listImages;
  }

  public void setImage(String string64, int postion){
    listImages.set(postion, string64);
    notifyItemChanged(postion);
  }
  public void updateList(List<String> list){
    this.listImages = list;
    notifyDataSetChanged();
  }

  public class ImageViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_camera_image_img)
    ImageView mImage;

    private Context mContext;
    private OnCameraAction mCameraAction;

    public ImageViewHolder(final View itemView, Context context, OnCameraAction mCameraAction) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      this.mContext = context;
      this.mCameraAction = mCameraAction;
      mImage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          setEasyDialog(getAdapterPosition());
        }
      });
    }

    private void setEasyDialog(final int postion) {
      View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_camera_action, null);
      TextView btnTakePicture = view.findViewById(R.id.dialog_camera_action_btn_take_photo);
      TextView btnChooseFromPhone = view.findViewById(R.id.dialog_camera_action_btn_choose_from_phone);
      new EasyDialog(mContext)
          .setLayout(view)
          .setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite))
          .setLocationByAttachedView(mImage)
          .setGravity(EasyDialog.GRAVITY_TOP)
          .setTouchOutsideDismiss(true)
          .setMatchParent(false)
          .setOutsideColor(mContext.getResources().getColor(R.color.outside_color_gray))
          .setMarginLeftAndRight(24, 24)
          .show();

      btnTakePicture.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          mCameraAction.onCameraAction(mContext.getString(R.string.upload_action_take_picture), postion);
        }
      });

      btnChooseFromPhone.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          mCameraAction.onCameraAction(mContext.getString(R.string.upload_action_choose_from_phone), postion);
        }
      });
    }

  }

  public interface OnCameraAction{
     void onCameraAction(String action, int position);
  }
}
