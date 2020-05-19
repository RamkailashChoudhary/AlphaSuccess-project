package com.app.alphasucess.ui.tabui.test.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.adapter.CommentData;
import com.app.alphasucess.ui.tabui.adapter.ExamAdapter;
import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.app.alphasucess.ui.tabui.ebook.adapters.EbookData;
import com.app.alphasucess.ui.tabui.ebook.adapters.EbookRecyclerViewAdapter;
import com.app.alphasucess.ui.tabui.test.OnlineTestActivity;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OnlineTestAdapter extends RecyclerView.Adapter {

    private ArrayList<AllTestData> mValues;
    private Context mContext;
    private String fileName;
    private static final int TEST_LIST_ROW = 0;
    private static final int TEST_LIST_DATA = 1;
    private List<ExamData> examListData;

    public OnlineTestAdapter(Context context, ArrayList<AllTestData> values) {
        mValues = values;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
          return TEST_LIST_ROW;
        else
          return TEST_LIST_DATA;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView OnlinetextView1,questionMarks,OnlinetextView2,OnlinetextView3,examDuration,noQuestions;
        private AllTestData item;
        private MaterialButton playTestBtn,seeBtn;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            playTestBtn = (MaterialButton) v.findViewById(R.id.playTextButton);
            seeBtn = (MaterialButton) v.findViewById(R.id.seeResultBtn);
            OnlinetextView1 = (TextView) v.findViewById(R.id.OnlinetextView1);
            noQuestions = (TextView) v.findViewById(R.id.noQuestions);
            examDuration = (TextView) v.findViewById(R.id.examDuration);
            questionMarks = (TextView) v.findViewById(R.id.questionMarks);
            OnlinetextView3 = (TextView) v.findViewById(R.id.OnlinetextView3);
            playTestBtn.setOnClickListener(this);
        }

        public void setData(AllTestData item) {
            this.item = item;
            OnlinetextView1.setText(item.getTestname()+"");
            examDuration.setText(item.getTimeinminutes()+" Mins");
            noQuestions.setText(item.getQuestioncount());
            questionMarks.setText(item.getTotalmarks());
        }

        @Override
        public void onClick(View view) {

           //Toast.makeText(view.getContext(),"Content of the ",Toast.LENGTH_LONG).show();
            Intent playTestView = new Intent(view.getContext(), OnlineTestActivity.class);
            view.getContext().startActivity(playTestView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TEST_LIST_ROW) {
            view = LayoutInflater.from(mContext).inflate(R.layout.test_list_row, parent, false);
            return new TestListDataViewHolder(view);
        }else if (viewType == TEST_LIST_DATA){
            view = LayoutInflater.from(mContext).inflate(R.layout.onlinetestrow, parent, false);
            return new ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {
            case TEST_LIST_ROW:
               ((TestListDataViewHolder)viewHolder).bind(null);
               break;
            case TEST_LIST_DATA:
                ((ViewHolder)viewHolder).setData(mValues.get(position));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private class TestListDataViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerView;

        TestListDataViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.testListRecyclerView);
        }

        void bind(List<AllTestData> allTestData) {
            ArrayList<ExamData> examDataList = new ArrayList<>();
            examDataList.add(new ExamData());
            examDataList.add(new ExamData());
            examDataList.add(new ExamData());
            examDataList.add(new ExamData());
            examDataList.add(new ExamData());
            examDataList.add(new ExamData());
            examDataList.add(new ExamData());
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
            ExamAdapter examAdapter = new ExamAdapter(mContext,examDataList);
            recyclerView.setAdapter(examAdapter);
        }
    }
}

