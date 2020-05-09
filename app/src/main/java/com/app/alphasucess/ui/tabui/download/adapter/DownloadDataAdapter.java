package com.app.alphasucess.ui.tabui.download.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.ebook.adapters.EbookData;
import com.app.alphasucess.ui.tabui.ebook.adapters.EbookRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class DownloadDataAdapter extends RecyclerView.Adapter<DownloadDataAdapter.ViewHolder> {

private ArrayList<DownloadData> mValues;
private Context mContext;
private String fileName;

public DownloadDataAdapter(Context context, ArrayList<DownloadData> values) {
        mValues = values;
        mContext = context;
//        fileName = path;
//        mListener=itemListener;
        }

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textView;
    private ImageView imageView;
    private RelativeLayout relativeLayout;
    private DownloadData item;

    public ViewHolder(View v) {
        super(v);
        v.setOnClickListener(this);
        textView = (TextView) v.findViewById(R.id.titleTxt);
        imageView = (ImageView) v.findViewById(R.id.ebookImg);
    }

    public void setData(DownloadData item) {
        this.item = item;
        // textView.setText("AAAA");

      /*  Picasso.with(mContext).load("https://image.shutterstock.com/image-photo/mountains-during-sunset-beautiful-natural-600w-407021107.jpg")
                .into(imageView);*/
        //imageView.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public void onClick(View view) {

    }
}

    @Override
    public DownloadDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.download_row, parent, false);
//        view.setLayoutParams(new ViewGroup.LayoutParams(getColumnWidth(parent.getContext()),ViewGroup.LayoutParams.WRAP_CONTENT));
        return new DownloadDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DownloadDataAdapter.ViewHolder viewHolder, int position) {

        viewHolder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}

