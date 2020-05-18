package com.app.alphasucess.ui.tabui.test;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.tabui.test.adapters.OnlineTestListener;
import com.app.alphasucess.ui.tabui.test.adapters.SingleTestQuestion;
import com.app.alphasucess.ui.tabui.test.adapters.TestData;

public class OnlineTestActivity extends AppCompatActivity implements OnlineTestListener, View.OnClickListener {

    private int QUESTION_INDEX = 0;

    private SingleTestQuestion singleTestQuestion;
    private RelativeLayout nextBtnView,preBtnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_test);
        nextBtnView = findViewById(R.id.nextBtnView);
        preBtnView = findViewById(R.id.preBtnView);
        nextBtnView.setOnClickListener(this);
        preBtnView.setOnClickListener(this);
        testQuestionDataList();
    }

    private void testQuestionDataList(){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
        restServiceLayer.singleTestQuestions("Bearer "+ MyApplication.AUTH_TOKEN,"1").enqueue(new Callback<ResoureData<SingleTestQuestion>>() {
            @Override
            public void onResponse(Call<ResoureData<SingleTestQuestion>> call, Response<ResoureData<SingleTestQuestion>> response) {
                if(response.body().getReplycode().equalsIgnoreCase("1")) {

                    singleTestQuestion = response.body().getData();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.ontestScreenView, TestScreenFragment.newInstance(singleTestQuestion.getQuestions().get(QUESTION_INDEX)))
                            .commitNow();
                    Toast.makeText(OnlineTestActivity.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResoureData<SingleTestQuestion>> call, Throwable t) {

            }
        });
    }

    @Override
    public void nextQuestion(TestData testData) {

        QUESTION_INDEX++;
        if(singleTestQuestion.getQuestions().size() > QUESTION_INDEX) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ontestScreenView, TestScreenFragment.newInstance(singleTestQuestion.getQuestions().get(QUESTION_INDEX)))
                    .commitNow();
            Toast.makeText(OnlineTestActivity.this, "" + singleTestQuestion.getQuestions().get(QUESTION_INDEX).getTestquestion(), Toast.LENGTH_LONG).show();
          }else
            Toast.makeText(OnlineTestActivity.this, "END" , Toast.LENGTH_LONG).show();
    }

    @Override
    public void preQuestion(TestData testData) {
        QUESTION_INDEX--;
        if(QUESTION_INDEX > 0 && singleTestQuestion.getQuestions().size() > QUESTION_INDEX) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ontestScreenView, TestScreenFragment.newInstance(singleTestQuestion.getQuestions().get(QUESTION_INDEX)))
                    .commitNow();
            Toast.makeText(OnlineTestActivity.this, "" + singleTestQuestion.getQuestions().get(QUESTION_INDEX).getTestquestion(), Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(OnlineTestActivity.this, "END" , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if(view == preBtnView){
             preQuestion(null);
        }else if(view == nextBtnView) {
            nextQuestion(null);
        }
    }
}
