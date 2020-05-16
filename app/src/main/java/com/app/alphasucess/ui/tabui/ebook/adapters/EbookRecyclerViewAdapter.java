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
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView,bookPrice;
        private ImageView imageView;
        private RelativeLayout relativeLayout;
        private EbookData item;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.titleTxt);
            bookPrice = (TextView) v.findViewById(R.id.bookPrice);
            imageView = (ImageView) v.findViewById(R.id.ebookImg);
        }

        public void setData(EbookData item) {
            this.item = item;
            Picasso.with(mContext).load("http://demo1.stsm.co.in/"+item.getImageurl())
                    .into(imageView);
            textView.setText(item.getBookname());
            String rupee = mContext.getResources().getString(R.string.Rs);
            bookPrice.setText(rupee+ " "+item.getBookprice());
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ebookrow, parent, false);
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

