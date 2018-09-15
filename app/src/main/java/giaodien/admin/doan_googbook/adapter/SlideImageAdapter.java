package giaodien.admin.doan_googbook.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.frosquivel.magicalcamera.Utilities.ConvertSimpleImage;

import java.util.List;

import giaodien.admin.doan_googbook.R;

/**
 * Created by Admin on 26/10/2017.
 */

public class SlideImageAdapter extends PagerAdapter {

  private List<String> mListImages;
  private Context mContext;

  public SlideImageAdapter(Context context, List<String> listImage) {
    this.mContext = context;
    this.mListImages = listImage;
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_doc_image, container, false);
    ImageView imageView = view.findViewById(R.id.doc_image_img);

    Glide.with(mContext).
        load(ConvertSimpleImage.stringBase64ToBytes(mListImages.get(position))).
        asBitmap().into(imageView);
    container.addView(view);
    return view;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  @Override
  public int getCount() {
    return mListImages.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return super.getPageTitle(position);
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == (View) object;
  }

  public void updateListImages(List<String> listImages){
    this.mListImages = listImages;
    notifyDataSetChanged();
  }

  @Override
  public int getItemPosition(Object object) {
    return POSITION_NONE;
  }



}
