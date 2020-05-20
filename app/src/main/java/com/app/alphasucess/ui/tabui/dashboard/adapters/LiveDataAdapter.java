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
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.CommentActivity;
import com.app.alphasucess.ui.PDFViewActivity;
import com.app.alphasucess.ui.VideoPlayerActivity;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.tabui.download.adapter.DownloadData;
import com.app.alphasucess.ui.tabui.download.adapter.DownloadDataAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataAdapter extends RecyclerView.Adapter<LiveDataAdapter.ViewHolder> {

private ArrayList<LiveData> mValues;
private Context mContext;
private String fileName;

public LiveDataAdapter(Context context, ArrayList<LiveData> values) {
        mValues = values;
        mContext = context;
        }

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textView,pdfComments;
    private ImageView imageView;
    private LiveData item;

    public ViewHolder(View v) {
        super(v);
        v.setOnClickListener(this);
        v.setTag(item);
        textView = (TextView) v.findViewById(R.id.teacherName);
        imageView = (ImageView) v.findViewById(R.id.videoThumbnialImg);
    }

    public void setData(LiveData item) {
        this.item = item;
        textView.setText(item.getTeachername());

        Picasso.with(mContext).load("http://demo1.stsm.co.in"+item.getThumbnailurl())
                .into(imageView);
        //imageView.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public void onClick(View view) {

        LiveData data = (LiveData) view.getTag();
        playerView(data.getId());
    }

    private void playerView(String url){

        Intent intent = new Intent(mContext, VideoPlayerActivity.class);
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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.video_view_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}


