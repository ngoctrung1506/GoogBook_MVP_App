package giaodien.admin.doan_googbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import giaodien.admin.doan_googbook.R;
import giaodien.admin.doan_googbook.dialog.LastRowDialog;
import giaodien.admin.doan_googbook.model.Doc;

/**
 * Created by Admin on 24/10/2017.
 */

public class DocListAdapter extends RecyclerView.Adapter<DocListAdapter.DocListViewHolder>{

  private Context mContext;
  private List<Doc> mListDoc;
  private OnUpLoadDocFile mOnUpLoadDocFile;

  public DocListAdapter(Context context, List<Doc> listDoc, OnUpLoadDocFile onUpLoadDocFile) {
    mContext = context;
    mListDoc = listDoc;
    mOnUpLoadDocFile = onUpLoadDocFile;
  }

  @Override
  public DocListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View view = inflater.inflate(R.layout.item_doc_file, parent, false);
    return new DocListViewHolder(view, mContext);
  }

  @Override
  public void onBindViewHolder(DocListViewHolder holder, int position) {
      Doc doc = mListDoc.get(position);
      holder.mTxtFileName.setFocusable(true);
      holder.mTxtFileName.setText(doc.getmDocName());
  }

  @Override
  public int getItemCount() {
    return mListDoc.size();
  }

  public void addRow() {
    Doc doc = new Doc(mContext.getString(R.string.upload_no_doc_choosen));
    mListDoc.add(doc);
    notifyItemInserted(getItemCount() + 1);
  }

  public void setTxtDocName(String name, int position) {
    mListDoc.get(position).setmDocName(name);
    notifyItemChanged(position);
  }

  public void updateData(List<Doc> list){
    this.mListDoc = list;
    notifyDataSetChanged();
  }

  public void clearData() {
    mListDoc.clear();
  }

  public class DocListViewHolder extends ViewHolder {

    @BindView(R.id.item_doc_file_btn_choose_file)
    Button mBtnChooseFile;

    @BindView(R.id.item_doc_file_txt_file_name)
    TextView mTxtFileName;

    @BindView(R.id.item_doc_file_btn_delete_row)
    ImageView mBtnDeleteRow;

    private Context mContext;

    public DocListViewHolder(View itemView, Context context) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      this.mContext = context;
      addEvent();
    }

    public void addEvent() {
      mBtnDeleteRow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if(mListDoc.size() == 1){
            new LastRowDialog(mContext, mOnUpLoadDocFile).show();
          }
          else {
            mListDoc.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
          }
        }
      });

      mBtnChooseFile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          mOnUpLoadDocFile.onSelectFileFromDevice(getAdapterPosition());
        }
      });
    }

    public void showDialogEvent() {
//      final Dialog optionDialog = new Dialog(mContext);
//      optionDialog.setContentView(R.layout.dialog_delele_last_row);
//      TextView mBtnTrue = optionDialog.findViewById(R.id.dialog_delele_last_row_true);
//      TextView mBtnWrong = optionDialog.findViewById(R.id.dialog_delele_last_row_wrong);
//      optionDialog.show();
//
//      mBtnTrue.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//           mOnUpLoadDocFile.hideUpLoadDocFileLayout();
//          optionDialog.hide();
//        }
//      });
//
//      mBtnWrong.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//          optionDialog.hide();
//        }
//      });
    }
  }

  public interface OnUpLoadDocFile{

    void hideUpLoadDocFileLayout();

    void onSelectFileFromDevice(int position);

  }
}
