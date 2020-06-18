package com.app.alphasucess.ui.tabui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.home.adapter.LiveClassData;
import com.app.alphasucess.ui.tabui.home.adapter.LivecourseAdapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        ArrayList<LiveClassData> categories=new ArrayList<>();
        LivecourseAdapter mAdapter = new LivecourseAdapter(this,categories);
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
