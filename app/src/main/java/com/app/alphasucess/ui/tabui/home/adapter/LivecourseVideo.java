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
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveData;
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveDataAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ashish Sharma on 1/4/2020.
 * Be U Salons
 * ashishsharma@beusalons.com
 */
public class LivecourseVideo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<LiveData> categories;
    private boolean isProducts;

    public LivecourseVideo(Context ctx,ArrayList<LiveData> categories) {
        this.mContext = ctx;
        this.categories = categories;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_online_video, parent, false);
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

        public ImageView homeScreenVideoImg;
        public TextView txt_by,txt_viewer;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            homeScreenVideoImg = v.findViewById(R.id.homeScreenVideoImg);
            txt_by = v.findViewById(R.id.txt_by);
            txt_viewer = v.findViewById(R.id.txt_viewer);
           /* image = (ImageView) v.findViewById(R.id.img_service);
            title = (TextView) v.findViewById(R.id.txt_service_name);
            lyt_parent = v.findViewById(R.id.parent_layout);*/
           /* Typeface lato_regular  = ResourcesCompat.getFont(ctx, R.font.lato_regular);
            title.setTypeface(lato_regular);*/
        }

        public void setData(LiveData item){

            Picasso.with(mContext).load("http://demo1.stsm.co.in/"+item.getThumbnailurl())
                    .into(homeScreenVideoImg);
            txt_by.setText(""+item.getTeachername());
            txt_viewer.setText(""+item.getViews());
        }
    }


}
