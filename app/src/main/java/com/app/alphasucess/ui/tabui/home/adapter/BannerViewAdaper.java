package com.app.alphasucess.ui.tabui.home.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashish Sharma on 1/4/2020.
 * Be U Salons
 * ashishsharma@beusalons.com
 */
public class BannerViewAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context ctx;
    private List<BannerData> categories;
    private boolean isProducts;

    public BannerViewAdaper(Context ctx, List<BannerData> bannerDataList) {
        this.ctx = ctx;
        this.categories = bannerDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_online_education, parent, false);
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

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView bannerImageView;
        public TextView title;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            bannerImageView = v.findViewById(R.id.bannerImageView);
        }

        public void setData(BannerData item){
            Picasso.with(ctx).load("http://demo1.stsm.co.in/"+item.getBannerurl())
                    .into(bannerImageView);
        }
    }


}
