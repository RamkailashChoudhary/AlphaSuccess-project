package com.app.alphasucess.ui.tabui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.alphasucess.R;
import com.app.alphasucess.TopBarClickEvent;
import com.app.alphasucess.ui.SubscriptionDetailActivity;
import com.app.alphasucess.ui.data.model.SubscriptionData;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.ViewHolder> {

    private ArrayList<SubscriptionData> mValues;
    private Context mContext;
    private String fileName;

    public SubscriptionAdapter(Context context, ArrayList<SubscriptionData> values) {
        mValues = values;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtprice,txt_engdesc,txthindidesc,textView6;
        private ImageView imageView;
        private RelativeLayout relativeLayout;
        private SubscriptionData item;

        public ViewHolder(View v) {
            super(v);
            txtprice = (TextView) v.findViewById(R.id.txtprice);
            textView6 = (TextView) v.findViewById(R.id.textView6);
            txt_engdesc = (TextView) v.findViewById(R.id.txt_engdesc);
            txthindidesc = (TextView) v.findViewById(R.id.txthindidesc);
            imageView = (ImageView) v.findViewById(R.id.subscriptionImageView);
            textView6.setOnClickListener(this);
        }

        public void setData(SubscriptionData item) {
            this.item = item;
            textView6.setTag(item);
            Picasso.with(mContext).load("http://demo1.stsm.co.in/" + item.getHomebannerurl())
                    .into(imageView);
            txtprice.setText(item.getPrice());
            txt_engdesc.setText(item.getExamname());
            txthindidesc.setText(item.getDescription());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new TopBarClickEvent(item.getId()));
                }
            });
        }

        @Override
        public void onClick(View view) {
           if(view ==  textView6){
               SubscriptionData data = (SubscriptionData) view.getTag();
               Intent subscriptionDetail = new Intent(mContext, SubscriptionDetailActivity.class);
               subscriptionDetail.putExtra("SUBSCRIPTION-PLAN-ID",data.getId());
               mContext.startActivity(subscriptionDetail);
            }
        }
    }

    @Override
    public SubscriptionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_booklist, parent, false);
//        view.setLayoutParams(new ViewGroup.LayoutParams(getColumnWidth(parent.getContext()),ViewGroup.LayoutParams.WRAP_CONTENT));
        return new SubscriptionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubscriptionAdapter.ViewHolder viewHolder, int position) {

        viewHolder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}