package com.app.alphasucess.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.tabui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookListActivity extends BaseActivity {
    ProgressBar loadingProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);
        final ImageView backBtnView = findViewById(R.id.backBtnView);
        TextView header=findViewById(R.id.middleTitle);
//        header.setText("Forgot Password");

        backBtnView.setOnClickListener(view -> {
            onBackPressed();
        });
    }


    private void forgotApiService(String username){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
        restServiceLayer.forgotPassword(username).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

//                loadingProgressBar.setVisibility(View.VISIBLE);
                Intent forgotPassword1 = new Intent(BookListActivity.this, LoginActivity.class);
                startActivity(forgotPassword1);
                finish();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

//                loadingProgressBar.setVisibility(View.GONE);
                Toast.makeText(BookListActivity.this,""+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
