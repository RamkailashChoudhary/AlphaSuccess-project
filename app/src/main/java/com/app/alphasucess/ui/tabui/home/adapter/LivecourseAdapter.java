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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ashish Sharma on 1/4/2020.
 * Be U Salons
 * ashishsharma@beusalons.com
 */
public class LivecourseAdapter extends RecyclerView.Adapter<LivecourseAdapter.OriginalViewHolder> {
    private Context ctx;
    private ArrayList<LiveClassData> categories;
    private boolean isProducts;

    public LivecourseAdapter(Context ctx,ArrayList<LiveClassData> categories) {
        this.ctx = ctx;
        this.categories=categories;
    }

    @NonNull
    @Override
    public OriginalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OriginalViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_livecourses, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull OriginalViewHolder holder, int position) {
        holder.txt_subject.setText(categories.get(position).getSubject());
        holder.txt_description.setText(categories.get(position).getTitle());
        holder.txt_startin_time.setText(categories.get(position).getStarttime());
        holder.txt_name.setText(categories.get(position).getTeachername());
        holder.txt_lang.setText("Language - "+categories.get(position).getLanguage());
        holder.txt_enrolled.setText("Students Enrolled - "+categories.get(position).getEnrolledstudents());
        holder.txt_enrolled.setText(categories.get(position).getFromtime()+" - "+categories.get(position).getTotime());
        Picasso.with(ctx).load("http://demo1.stsm.co.in/"+categories.get(position).getTeacherimage())
                .into(holder.img_profile);
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_profile;
        public TextView title;
        public TextView txt_subject,txt_description,txt_startin_time,txt_name,txt_lang,txt_enrolled,txt_timings;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            txt_subject=v.findViewById(R.id.txt_subject);
            img_profile=v.findViewById(R.id.img_profile);
            txt_description=v.findViewById(R.id.txt_description);
            txt_startin_time=v.findViewById(R.id.txt_startin_time);
            txt_name=v.findViewById(R.id.txt_name);
            txt_lang=v.findViewById(R.id.txt_lang);
            txt_enrolled=v.findViewById(R.id.txt_enrolled);
            txt_timings=v.findViewById(R.id.txt_timings);

        }
    }
}
