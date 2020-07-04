package com.app.alphasucess.ui.tabui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.SubscriptionDetailActivity;
import com.app.alphasucess.ui.data.model.SubjectTopicData;
import com.app.alphasucess.ui.data.model.SubscriptionListData;
import com.app.alphasucess.ui.tabui.home.adapter.BannerData;

import java.util.List;

public class SubjectTopicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context ctx;
    private List<SubjectTopicData> categories;
    private boolean isProducts;

    public SubjectTopicAdapter(Context ctx, List<SubjectTopicData> bannerDataList) {
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
        ((OriginalViewHolder) holder).setData(categories.get(position));
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
        }

        public void setData(SubjectTopicData item) {
            title.setText(item.getTopicname());
        }

        @Override
        public void onClick(View view) {
            BannerData item = (BannerData) view.getTag();
            Intent subscriptionDetail = new Intent(ctx, SubscriptionDetailActivity.class);
            subscriptionDetail.putExtra("SUBSCRIPTION-PLAN-ID", item.getSubscription_id());
            ctx.startActivity(subscriptionDetail);
        }
    }
}
