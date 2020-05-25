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

import java.util.ArrayList;

/**
 * Created by Ashish Sharma on 1/4/2020.
 * Be U Salons
 * ashishsharma@beusalons.com
 */
public class LivecourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context ctx;
    private ArrayList<LiveData> categories;
    private boolean isProducts;

    public LivecourseAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_livecourses, parent, false);
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

        }
    }


}
