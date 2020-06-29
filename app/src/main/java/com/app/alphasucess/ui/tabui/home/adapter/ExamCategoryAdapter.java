package com.app.alphasucess.ui.tabui.home.adapter;



import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.BookListActivity;
import com.app.alphasucess.ui.tabui.dashboard.adapters.LiveData;
import com.app.alphasucess.ui.tabui.home.ExamCategoryData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ashish Sharma on 1/4/2020.
 * Be U Salons
 * ashishsharma@beusalons.com
 */
public class ExamCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<ExamCategoryData> categories;
    private boolean isProducts;

    public ExamCategoryAdapter(Context ctx, ArrayList<ExamCategoryData> categories) {
        this.mContext = ctx;
        this.categories = categories;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_indianexam, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((OriginalViewHolder)holder).txt_type.setText(categories.get(position).getCategoryname());
        Picasso.with(mContext).load("http://demo1.stsm.co.in/"+categories.get(position).getIconurl())
                .into(((OriginalViewHolder)holder).img_star);
        ((OriginalViewHolder)holder).examRootBg.setBackgroundColor(Color.parseColor(categories.get(position).getColorcode()));
        ((OriginalViewHolder)holder).examRootBg.setTag(categories.get(position));
    }



    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        public ImageView img_star;
        public TextView txt_type,txt_viewer;
        public View lyt_parent;
        public ConstraintLayout examRootBg;

        public OriginalViewHolder(View v) {
            super(v);
            img_star = v.findViewById(R.id.img_star);
            txt_type = v.findViewById(R.id.txt_type);
            examRootBg = v.findViewById(R.id.examRootBg);
            examRootBg.setOnClickListener(examCardListener);
//            txt_viewer = v.findViewById(R.id.txt_viewer);
           /* image = (ImageView) v.findViewById(R.id.img_service);
            title = (TextView) v.findViewById(R.id.txt_service_name);
            lyt_parent = v.findViewById(R.id.parent_layout);*/
           /* Typeface lato_regular  = ResourcesCompat.getFont(ctx, R.font.lato_regular);
            title.setTypeface(lato_regular);*/
        }

        private View.OnClickListener examCardListener = view -> {
            ExamCategoryData examCategoryData = (ExamCategoryData) view.getTag();
            Intent subscriptionListData = new Intent(mContext, BookListActivity.class);
            System.out.println("EXAM ID :"+examCategoryData.getId());
            subscriptionListData.putExtra("SUBSCRIPTION-PLAN-ID",examCategoryData.getId()+"");
            mContext.startActivity(subscriptionListData);
        };

        public void setData(LiveData item){

//            Picasso.with(mContext).load("http://demo1.stsm.co.in/"+item.getThumbnailurl())
//                    .into(homeScreenVideoImg);
//            txt_by.setText(""+item.getTeachername());
//            txt_viewer.setText(""+item.getViews());
        }
    }


}
