package com.app.alphasucess.ui.tabui.ebook.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.app.alphasucess.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

public class EbookRecyclerViewAdapter extends RecyclerView.Adapter<EbookRecyclerViewAdapter.ViewHolder> {

    private ArrayList<EbookData> mValues;
    private Context mContext;
    private String fileName;

    public EbookRecyclerViewAdapter(Context context, ArrayList<EbookData> values) {
        mValues = values;
        mContext = context;
//        fileName = path;
//        mListener=itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;
        private ImageView imageView;
        private RelativeLayout relativeLayout;
        private EbookData item;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.titleTxt);
            imageView = (ImageView) v.findViewById(R.id.ebookImg);
        }

        public void setData(EbookData item) {
            this.item = item;
            textView.setText("AAAA");

            Picasso.with(mContext).load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTvYa_bNispXYutpFAm0KilTrMmlOky8HN0_Ws_Ni4QOWz3e-fO&usqp=CAU")
                    .into(imageView);
            //imageView.setImageResource(R.drawable.ic_launcher_background);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ebookrow, parent, false);
//        view.setLayoutParams(new ViewGroup.LayoutParams(getColumnWidth(parent.getContext()),ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}

