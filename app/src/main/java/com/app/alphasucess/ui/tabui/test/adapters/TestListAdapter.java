package com.app.alphasucess.ui.tabui.test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.adapter.ExamAdapter;
import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.ViewHolder> {

    private ArrayList<AllTestData> mValues;
    private Context mContext;
    private String fileName;

    public TestListAdapter(Context context, ArrayList<AllTestData> values) {
        mValues = values;
        mContext = context;
//        fileName = path;
//        mListener=itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;
        private CircleImageView imageView;
        private RelativeLayout relativeLayout;
        private AllTestData item;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.examName);
            imageView = (CircleImageView) v.findViewById(R.id.examImg);
            // imageView = (CircleImageView) v.findViewById(R.id.examImg);
        }

        public void setData(AllTestData item) {
            this.item = item;
             textView.setText(item.getTestname());
            Picasso.with(mContext).load("https://image.shutterstock.com/image-photo/mountains-during-sunset-beautiful-natural-600w-407021107.jpg")
                    .into(imageView);
            //imageView.setImageResource(R.drawable.ic_launcher_background);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.top_view_row, parent, false);
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



