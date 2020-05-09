package com.app.alphasucess.ui.tabui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.download.adapter.DownloadData;
import com.app.alphasucess.ui.tabui.download.adapter.DownloadDataAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {

    private ArrayList<ExamData> mValues;
    private Context mContext;
    private String fileName;

    public ExamAdapter(Context context, ArrayList<ExamData> values) {
        mValues = values;
        mContext = context;
//        fileName = path;
//        mListener=itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;
        private CircleImageView imageView;
        private RelativeLayout relativeLayout;
        private ExamData item;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.examName);
            imageView = (CircleImageView) v.findViewById(R.id.examImg);
           // imageView = (CircleImageView) v.findViewById(R.id.examImg);
        }

        public void setData(ExamData item) {
            this.item = item;
            // textView.setText("AAAA");

            Picasso.with(mContext).load("https://image.shutterstock.com/image-photo/mountains-during-sunset-beautiful-natural-600w-407021107.jpg")
                    .into(imageView);
            //imageView.setImageResource(R.drawable.ic_launcher_background);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public ExamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.top_view_row, parent, false);
//        view.setLayoutParams(new ViewGroup.LayoutParams(getColumnWidth(parent.getContext()),ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ExamAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExamAdapter.ViewHolder viewHolder, int position) {

        viewHolder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}


