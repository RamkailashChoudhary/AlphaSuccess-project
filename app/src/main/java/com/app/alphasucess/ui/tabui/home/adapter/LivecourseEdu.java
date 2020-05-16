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

import java.util.ArrayList;

/**
 * Created by Ashish Sharma on 1/4/2020.
 * Be U Salons
 * ashishsharma@beusalons.com
 */
public class LivecourseEdu extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context ctx;
    private ArrayList<String> categories;
    private boolean isProducts;

    public LivecourseEdu(Context ctx) {
        this.ctx = ctx;

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



    }



    @Override
    public int getItemCount() {
        return 6;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
           /* image = (ImageView) v.findViewById(R.id.img_service);
            title = (TextView) v.findViewById(R.id.txt_service_name);
            lyt_parent = v.findViewById(R.id.parent_layout);*/
           /* Typeface lato_regular  = ResourcesCompat.getFont(ctx, R.font.lato_regular);
            title.setTypeface(lato_regular);*/


        }
    }


}
