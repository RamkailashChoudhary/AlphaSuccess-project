package com.app.alphasucess.ui.tabui.download.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.CommentActivity;
import com.app.alphasucess.ui.PDFViewActivity;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.tabui.ebook.adapters.EbookData;
import com.app.alphasucess.ui.tabui.ebook.adapters.EbookRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadDataAdapter extends RecyclerView.Adapter<DownloadDataAdapter.ViewHolder> {

private ArrayList<DownloadData> mValues;
private Context mContext;
private String fileName;

public DownloadDataAdapter(Context context, ArrayList<DownloadData> values) {
        mValues = values;
        mContext = context;
//        fileName = path;
//        mListener=itemListener;
        }

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textView,pdfViews,pdfLikes,pdfComments;
    private ImageView imageView;
    private DownloadData item;

    public ViewHolder(View v) {
        super(v);
        v.setOnClickListener(this);
        textView = (TextView) v.findViewById(R.id.titleTxt);
        pdfViews = (TextView) v.findViewById(R.id.pdfViews);
        pdfLikes = (TextView) v.findViewById(R.id.pdfLikes);
        pdfComments = (TextView) v.findViewById(R.id.pdfComments);
        imageView = (ImageView) v.findViewById(R.id.downloadImg);
    }

    public void setData(DownloadData item) {
        this.item = item;
         textView.setText(item.getPdfname());
        pdfViews.setText(""+item.getViews());
        pdfLikes.setText(""+item.getLikescount());
        pdfComments.setText(""+item.getViews());
        imageView.setTag(item);
        imageView.setOnClickListener(this);
        pdfLikes.setTag(item);
        pdfComments.setTag(item);
        pdfLikes.setOnClickListener(this);
        pdfComments.setOnClickListener(this);
        Drawable like_blue = mContext.getResources().getDrawable(R.drawable.like_blue);
        Drawable like_black = mContext.getResources().getDrawable(R.drawable.like);
        pdfLikes.setCompoundDrawablesWithIntrinsicBounds(item.isLikedbyUser() ? like_blue : like_black,null,null,null);
         Picasso.with(mContext).load("http://demo1.stsm.co.in"+item.getThumbnailurl())
                .into(imageView);
        //imageView.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public void onClick(View view) {
        if(view == pdfLikes) {

            DownloadData data = (DownloadData) view.getTag();
            pdfLikeApi(data.isLikedbyUser() ? "/api/App/UpdateUnlike" : "/api/App/UpdateLike", data);
        }else if(view == pdfComments){

            DownloadData data = (DownloadData) view.getTag();
            Intent commentView = new Intent(mContext, CommentActivity.class);
            commentView.putExtra("COMMENT_ID",data.getId());
            mContext.startActivity(commentView);
        } else if(view == imageView){

            DownloadData data = (DownloadData) view.getTag();
            if(data.getIspaid().equalsIgnoreCase("false") && (data.getDemopdfurl() != null && data.getDemopdfurl().length()> 0)) {

                Intent commentView = new Intent(mContext, PDFViewActivity.class);
                commentView.putExtra("PDF_URL", data.getPdfurl());
                mContext.startActivity(commentView);
            }else{
                Toast.makeText(mContext,"Please first purchase this PDF Book",Toast.LENGTH_LONG).show();
            }
        }
    }
}

    @Override
    public DownloadDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.download_row, parent, false);
        return new DownloadDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DownloadDataAdapter.ViewHolder viewHolder, int position) {

        viewHolder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private void pdfLikeApi(String url,DownloadData data){
       RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
       restServiceLayer.bookLikeOrUnLike(url, "Bearer "+MyApplication.AUTH_TOKEN,data.getId()).enqueue(new Callback<ResoureData>() {
            @Override
            public void onResponse(Call<ResoureData> call, Response<ResoureData> response) {

                if(response.body() != null && response.body().getReplycode().equalsIgnoreCase("1")) {

                    int likeCount = Integer.parseInt(data.getLikescount());
                    likeCount = data.isLikedbyUser() ? (likeCount-1) : (likeCount+1);
                    data.setLikescount(likeCount+"");
                    data.setLikedbyUser(data.isLikedbyUser() ? false : true);
                    notifyDataSetChanged();
                    Toast.makeText(mContext,""+response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResoureData> call, Throwable t) {

            }
        });
    }
}

