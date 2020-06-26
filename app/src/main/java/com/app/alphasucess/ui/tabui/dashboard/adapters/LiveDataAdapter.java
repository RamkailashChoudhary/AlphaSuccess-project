package com.app.alphasucess.ui.tabui.dashboard.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.Sample;
import com.app.alphasucess.TopBarClickEvent;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.CommentActivity;
import com.app.alphasucess.ui.PDFViewActivity;
import com.app.alphasucess.ui.VideoPlayerActivity;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.tabui.adapter.ExamAdapter;
import com.app.alphasucess.ui.tabui.adapter.ExamData;
import com.app.alphasucess.ui.tabui.download.adapter.DownloadData;
import com.app.alphasucess.ui.tabui.download.adapter.DownloadDataAdapter;
import com.app.alphasucess.ui.tabui.home.HomeFragment;
import com.app.alphasucess.ui.tabui.test.adapters.AllTestData;
import com.app.alphasucess.ui.tabui.test.adapters.OnlineTestAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataAdapter extends RecyclerView.Adapter {

private ArrayList<LiveData> mValues;
private Context mContext;
private String fileName;
    private static final int TEST_LIST_ROW = 0;
    private static final int TEST_LIST_DATA = 1;
    private ArrayList<ExamData> examListData = new ArrayList<>();


public LiveDataAdapter(Context context, ArrayList<LiveData> values) {
        mValues = values;
        mContext = context;
        }

        public void setExamCategoryData(ArrayList<ExamData> examCategoryDataList){
            examListData = examCategoryDataList;
        }

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textView,pdfComments;
    private ImageView imageView;
    private LiveData item;

    public ViewHolder(View v) {
        super(v);
        textView = (TextView) v.findViewById(R.id.teacherName);
        imageView = (ImageView) v.findViewById(R.id.videoThumbnialImg);
    }

    public void setData(LiveData item) {
        this.item = item;
        imageView.setOnClickListener(this);
        imageView.setTag(item);
        textView.setText(item.getTeachername());

        Picasso.with(mContext).load("http://demo1.stsm.co.in"+item.getThumbnailurl())
                .into(imageView);
        //imageView.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public void onClick(View view) {

        LiveData data = (LiveData) view.getTag();
        playerVideoUrl(data.getId());
      }
   }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TEST_LIST_ROW;
        else
            return TEST_LIST_DATA;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TEST_LIST_ROW) {
            view = LayoutInflater.from(mContext).inflate(R.layout.test_list_row, parent, false);
            return new TestListDataViewHolder(view);
        }else if (viewType == TEST_LIST_DATA){
            view = LayoutInflater.from(mContext).inflate(R.layout.video_view_row, parent, false);
            return new ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {
            case TEST_LIST_ROW:
                ((TestListDataViewHolder)viewHolder).bind(examListData);
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

        void bind(ArrayList<ExamData> allTestData) {

            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            ExamAdapter examAdapter = new ExamAdapter(mContext,allTestData);
            recyclerView.setAdapter(examAdapter);
        }
    }

    private void playerVideoUrl(String id){

        EventBus.getDefault().post(new TopBarClickEvent("-1"));
        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class,MyApplication.REFRESH_TOKEN,mContext);
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


