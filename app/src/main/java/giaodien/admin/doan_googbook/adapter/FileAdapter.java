package giaodien.admin.doan_googbook.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import giaodien.admin.doan_googbook.R;

/**
 * Created by Admin on 30/12/2017.
 */

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder>{

  private Context mContext;
  private ArrayList<String> listFile;

  public FileAdapter(Context mContext, ArrayList<String> listFile) {
    this.mContext = mContext;
    this.listFile = listFile;
  }

  @Override
  public FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View view = inflater.inflate(R.layout.item_file, parent, false);
    return new FileViewHolder(view);
  }

  @Override
  public void onBindViewHolder(FileViewHolder holder, int position) {
    String fileName = listFile.get(position);
    holder.mFileName.setText(fileName);
    holder.mFileName.setTextColor(ContextCompat.getColor(mContext, R.color.mainColor));
    holder.mFileName.setPaintFlags(holder.mFileName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
  }

  @Override
  public int getItemCount() {
    return listFile.size();
  }

  public void updateList(ArrayList<String> listFile) {
    this.listFile = listFile;
    notifyDataSetChanged();
  }

  public class FileViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_file_file_name)
    TextView mFileName;

    public FileViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
//      addEvent();
    }

//    public void addEvent() {
//      if(NetworkUtils.isConnect(mContext))
//        mRoot.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//            onShowByAction.showByPostId(itemPosts.get(getAdapterPosition()).getId());
//          }
//        });
//      else Toast.makeText(mContext, R.string.app_no_data_retry_later, Toast.LENGTH_SHORT).show();
//    }

  }
}
