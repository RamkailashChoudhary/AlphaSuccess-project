package com.app.alphasucess.ui.tabui.test.adapters;

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
import com.app.alphasucess.ui.tabui.ebook.adapters.EbookData;
import com.app.alphasucess.ui.tabui.ebook.adapters.EbookRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class OnlineTestAdapter extends RecyclerView.Adapter<OnlineTestAdapter.ViewHolder> {

    private ArrayList<TestData> mValues;
    private Context mContext;
    private String fileName;

    public OnlineTestAdapter(Context context, ArrayList<TestData> values) {
        mValues = values;
        mContext = context;
//        fileName = path;
//        mListener=itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView OnlinetextView1,OnlinetextView2,OnlinetextView3;
        private TestData item;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            OnlinetextView1 = (TextView) v.findViewById(R.id.OnlinetextView1);
            OnlinetextView2 = (TextView) v.findViewById(R.id.OnlinetextView2);
            OnlinetextView3 = (TextView) v.findViewById(R.id.OnlinetextView3);
        }

        public void setData(TestData item) {
            this.item = item;
            OnlinetextView1.setText("AAAA");
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public OnlineTestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.onlinetestrow, parent, false);
//        view.setLayoutParams(new ViewGroup.LayoutParams(getColumnWidth(parent.getContext()),ViewGroup.LayoutParams.WRAP_CONTENT));
        return new OnlineTestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OnlineTestAdapter.ViewHolder viewHolder, int position) {

        viewHolder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}

