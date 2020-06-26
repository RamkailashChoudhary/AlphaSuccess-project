package com.app.alphasucess.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.tabui.adapter.CommentAdapter;
import com.app.alphasucess.ui.tabui.adapter.CommentData;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends BaseActivity implements View.OnClickListener {

    private List<CommentData> commentDataList = new ArrayList<>();
    private CommentAdapter commentAdapter;
    private MaterialButton sendBtnView;
    private EditText commentTxtData;
    private String commentId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Bundle bundle = getIntent().getExtras();
        commentId = bundle.getString("COMMENT_ID");
        TextView header=findViewById(R.id.middleTitle);
        header.setText("Comments");
        final ImageView backBtnView = findViewById(R.id.backBtnView);
        sendBtnView = findViewById(R.id.sendBtnView);
        commentTxtData = findViewById(R.id.commentTxtData);
        sendBtnView.setOnClickListener(this);
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

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class,MyApplication.REFRESH_TOKEN);
        restServiceLayer.commentDataList("Bearer "+ MyApplication.AUTH_TOKEN, commentId, "1").enqueue(new Callback<ResoureData<List<CommentData>>>() {
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

    @Override
    public void onClick(View view) {

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class,MyApplication.REFRESH_TOKEN);
        restServiceLayer.addCommentData("Bearer "+ MyApplication.AUTH_TOKEN,commentId,""+commentTxtData.getText().toString()).enqueue(new Callback<ResoureData>() {
            @Override
            public void onResponse(Call<ResoureData> call, Response<ResoureData> response) {

                if(response.body().getReplycode().equalsIgnoreCase("1")) {

                    CommentData commentData = new CommentData();
                    commentData.setComment(commentTxtData.getText().toString());
                    commentData.setUsername("Ram");
                    commentData.setUserid("2");
                    commentDataList.add(commentData);
                    commentAdapter.notifyDataSetChanged();
                    commentTxtData.setText("");
                    Toast.makeText(CommentActivity.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResoureData> call, Throwable t) {

            }
        });
    }
}
