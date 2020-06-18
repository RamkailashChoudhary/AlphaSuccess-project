package com.app.alphasucess.ui.tabui.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.adapter.ExamAdapter;
import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class HomeViewAdapter extends RecyclerView.Adapter<HomeViewAdapter.ViewHolder> {

    private ArrayList<HomeData> mValues;
    private Context mContext;
    private String fileName;
    private final int INDIAN_EXAM_VIEW = 0;
    private final int RECOMMEND_VIEW = 1;
    private final int LIVE_CLASSES_VIEW = 2;

    public HomeViewAdapter(Context context, ArrayList<HomeData> values) {
        mValues = values;
        mContext = context;
//        fileName = path;
//        mListener=itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;
        private ImageView imageView;
        private RelativeLayout relativeLayout;
        private HomeData item;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.examName);
            imageView = (ImageView) v.findViewById(R.id.bannerImg);
            // imageView = (CircleImageView) v.findViewById(R.id.examImg);
        }

        public void setData(HomeData item) {
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

  /*  @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(mValues.get(position).getViewType()) && mValues.get(position).getViewType().equalsIgnoreCase("")) {
            return INDIAN_EXAM_VIEW;
        } else {
            return RECOMMEND_VIEW;
        }
    }*/

    @Override
    public HomeViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.live_classes_view, parent, false);
        return new HomeViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeViewAdapter.ViewHolder viewHolder, int position) {

        switch (getItemViewType(position)){

        }
        viewHolder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}

