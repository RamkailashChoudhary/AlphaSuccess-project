package com.app.alphasucess.ui.tabui.home.adapter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.R;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewAllLiveClassesActivity extends BaseActivity implements View.OnClickListener {

    private ImageView backBtnView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_liveclasses_activity);
        recyclerView = findViewById(R.id.livecoursesViewAll);
        TextView header=findViewById(R.id.middleTitle);
        header.setText("Live Classes");
        backBtnView = findViewById(R.id.backBtnView);
        backBtnView.setOnClickListener(this);
        initViewAll(recyclerView);
    }

    private void initViewAll(RecyclerView recyclerView){

        LivecourseAdapter mAdapter = new LivecourseAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view) {
        if(view == backBtnView){

            onBackPressed();
        }
    }
}
