package com.app.alphasucess.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.tabui.adapter.CommentAdapter;
import com.app.alphasucess.ui.tabui.adapter.CommentData;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends BaseActivity {

    private List<CommentData> commentDataList = new ArrayList<>();
    private CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        TextView header=findViewById(R.id.middleTitle);
        header.setText("Comments");
        final ImageView backBtnView = findViewById(R.id.backBtnView);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewComment);
        initCommentDataList(recyclerView);
        backBtnView.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void initCommentDataList(RecyclerView recyclerView){

        commentAdapter = new CommentAdapter(this,commentDataList);
        recyclerView.setAdapter(commentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentDataList();
    }

    private void commentDataList() {

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
        restServiceLayer.commentDataList("Bearer "+ MyApplication.AUTH_TOKEN, "1", "1").enqueue(new Callback<ResoureData<List<CommentData>>>() {
            @Override
            public void onResponse(Call<ResoureData<List<CommentData>>> call, Response<ResoureData<List<CommentData>>> response) {

                if(response.body().getReplycode().equalsIgnoreCase("1")) {

                    commentDataList.addAll(response.body().getData());
                    commentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResoureData<List<CommentData>>> call, Throwable t) {

            }
        });
    }
}
