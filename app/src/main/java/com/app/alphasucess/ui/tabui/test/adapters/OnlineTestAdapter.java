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
import com.app.alphasucess.ui.tabui.ebook.adapters.EbookData;
import com.app.alphasucess.ui.tabui.ebook.adapters.EbookRecyclerViewAdapter;
import com.app.alphasucess.ui.tabui.test.OnlineTestActivity;
import com.google.android.material.button.MaterialButton;
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
        private MaterialButton playTestBtn,seeBtn;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            playTestBtn = (MaterialButton) v.findViewById(R.id.playTextButton);
            seeBtn = (MaterialButton) v.findViewById(R.id.seeResultBtn);
            OnlinetextView1 = (TextView) v.findViewById(R.id.OnlinetextView1);
            OnlinetextView2 = (TextView) v.findViewById(R.id.OnlinetextView2);
            OnlinetextView3 = (TextView) v.findViewById(R.id.OnlinetextView3);
            playTestBtn.setOnClickListener(this);
        }

        public void setData(TestData item) {
            this.item = item;
            OnlinetextView1.setText("AAAA");
        }

        @Override
        public void onClick(View view) {

           Toast.makeText(view.getContext(),"Content of the ",Toast.LENGTH_LONG).show();
            Intent playTestView = new Intent(view.getContext(), OnlineTestActivity.class);
            view.getContext().startActivity(playTestView);
        }
    }

    @Override
    public OnlineTestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.onlinetestrow, parent, false);
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

