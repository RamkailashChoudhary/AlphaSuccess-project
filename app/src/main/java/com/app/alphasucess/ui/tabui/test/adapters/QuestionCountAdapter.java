package com.app.alphasucess.ui.tabui.test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.view.CircleTextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class QuestionCountAdapter extends RecyclerView.Adapter<QuestionCountAdapter.ViewHolder> {

    private ArrayList mValues;
    private Context mContext;
    private String fileName;

    public QuestionCountAdapter(Context context, ArrayList values) {
        mValues = values;
        mContext = context;
//        fileName = path;
//        mListener=itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CircleTextView OnlinetextView1,OnlinetextView2,OnlinetextView3;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            OnlinetextView1 = (CircleTextView) v.findViewById(R.id.questionCountIndex);
        }

        public void setData(int item) {
            OnlinetextView1.setCustomText(item+"");
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.question_count_row, parent, false);
//        view.setLayoutParams(new ViewGroup.LayoutParams(getColumnWidth(parent.getContext()),ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
