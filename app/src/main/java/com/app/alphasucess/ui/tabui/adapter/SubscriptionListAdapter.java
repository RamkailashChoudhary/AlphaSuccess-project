package com.app.alphasucess.ui.tabui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.SubjectTopicActivity;
import com.app.alphasucess.ui.SubscriptionDetailActivity;
import com.app.alphasucess.ui.data.model.SubscriptionListData;
import com.app.alphasucess.ui.tabui.home.adapter.BannerData;
import com.app.alphasucess.ui.tabui.home.adapter.BannerViewAdaper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SubscriptionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context ctx;
    private List<SubscriptionListData> categories;
    private boolean isProducts;

    public SubscriptionListAdapter(Context ctx, List<SubscriptionListData> bannerDataList) {
        this.ctx = ctx;
        this.categories = bannerDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscriptionlist_row, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((OriginalViewHolder)holder).setData(categories.get(position));
    }



    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.subscriptionListTxt);
            title.setOnClickListener(this);
        }

        public void setData(SubscriptionListData item){
           title.setText(item.getSubjectname());
           title.setTag(item);
        }

        @Override
        public void onClick(View view) {
            SubscriptionListData item = (SubscriptionListData) view.getTag();
            Intent subscriptionDetail = new Intent(ctx, SubjectTopicActivity.class);
            subscriptionDetail.putExtra("SUBSCRIPTION-SUBJECT-ID",item.getId());
            ctx.startActivity(subscriptionDetail);
        }
    }
}