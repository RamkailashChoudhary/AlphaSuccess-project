package com.app.alphasucess.ui.tabui.home.adapter;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.Sample;
import com.app.alphasucess.TopBarClickEvent;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.VideoPlayerActivity;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveData;
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveDataAdapter;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

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
            homeScreenVideoImg.setOnClickListener(videoPlayerScreen);
           /* image = (ImageView) v.findViewById(R.id.img_service);
            title = (TextView) v.findViewById(R.id.txt_service_name);
            lyt_parent = v.findViewById(R.id.parent_layout);*/
           /* Typeface lato_regular  = ResourcesCompat.getFont(ctx, R.font.lato_regular);
            title.setTypeface(lato_regular);*/
        }

        public void setData(LiveData item){
            homeScreenVideoImg.setTag(item);
            Picasso.with(mContext).load("http://demo1.stsm.co.in/"+item.getThumbnailurl())
                    .into(homeScreenVideoImg);
            txt_by.setText(""+item.getTeachername());
            txt_viewer.setText(""+item.getViews());
        }
    }

    private View.OnClickListener videoPlayerScreen = view -> {
        LiveData data = (LiveData) view.getTag();
        EventBus.getDefault().post(new TopBarClickEvent("-1"));
        playerVideoUrl(data.getId());
    };

    private void playerVideoUrl(String id){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
        restServiceLayer.singleVideodetails("Bearer "+ MyApplication.AUTH_TOKEN,id).enqueue(new Callback<ResoureData<LiveData>>() {
            @Override
            public void onResponse(Call<ResoureData<LiveData>> call, Response<ResoureData<LiveData>> response) {
                if(response.body().getReplycode().equalsIgnoreCase("1")) {

                    playerView("http://demo1.stsm.co.in/"+response.body().getData().getVideourl(),response.body().getData().getTitle());
                }
            }

            @Override
            public void onFailure(Call<ResoureData<LiveData>> call, Throwable t) {
                EventBus.getDefault().post(new TopBarClickEvent("-2"));
            }
        });
    }

    public void playerView(String url,String title){

        EventBus.getDefault().post(new TopBarClickEvent("-2"));
        Intent intent = new Intent(mContext, VideoPlayerActivity.class);
        intent.putExtra("VIDEO_TITLE",title);
        Sample sample = getSampleObj("Video", Uri.parse(url),null,false,null,null,null,null);
        sample.addToIntent(intent);
        mContext.startActivity(intent);
    }

    private Sample getSampleObj(String sampleName, Uri uri, String extension, boolean isLive, Sample.DrmInfo drmInfo,String adTagUri,String sphericalStereoMode,
                                Sample.SubtitleInfo subtitleInfo){

        return new Sample.UriSample(
                sampleName,
                uri,
                extension,
                isLive,
                drmInfo,
                adTagUri != null ? Uri.parse(adTagUri) : null,
                sphericalStereoMode,
                subtitleInfo);
    }
}
