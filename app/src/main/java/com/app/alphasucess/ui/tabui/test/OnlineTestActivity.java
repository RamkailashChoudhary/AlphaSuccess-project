package com.app.alphasucess.ui.tabui.test;

import androidx.appcompat.app.AppCompatActivity;
import de.mrapp.android.dialog.MaterialDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.tabui.test.adapters.OnlineTestListener;
import com.app.alphasucess.ui.tabui.test.adapters.SingleTestQuestion;
import com.app.alphasucess.ui.tabui.test.adapters.TestData;

public class OnlineTestActivity extends BaseActivity implements OnlineTestListener, View.OnClickListener {

    private int QUESTION_INDEX = 0;

    private SingleTestQuestion singleTestQuestion;
    private RelativeLayout nextBtnView,preBtnView,submitBtnView;
    private ImageView backBtnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_test);

        TextView header=findViewById(R.id.middleTitle);
        header.setText("Questions");
        backBtnView = findViewById(R.id.backBtnView);
        backBtnView.setOnClickListener(this);
        nextBtnView = findViewById(R.id.nextBtnView);
        preBtnView = findViewById(R.id.preBtnView);
        submitBtnView = findViewById(R.id.submitBtnView);
        nextBtnView.setOnClickListener(this);
        preBtnView.setOnClickListener(this);
        submitBtnView.setOnClickListener(this);
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


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ontestScreenView, TestScreenFragment.newInstance(testData))
                    .commitNow();
            Toast.makeText(OnlineTestActivity.this, "" + singleTestQuestion.getQuestions().get(QUESTION_INDEX).getTestquestion(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void preQuestion(TestData testData) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ontestScreenView, TestScreenFragment.newInstance(testData))
                    .commitNow();
            Toast.makeText(OnlineTestActivity.this, "" + singleTestQuestion.getQuestions().get(QUESTION_INDEX).getTestquestion(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view) {
        if(view == preBtnView){

            QUESTION_INDEX--;
            if(QUESTION_INDEX > 0 && singleTestQuestion.getQuestions().size() > QUESTION_INDEX) {

                preQuestion(singleTestQuestion.getQuestions().get(QUESTION_INDEX));
            }
        }else if(view == nextBtnView) {

            QUESTION_INDEX++;
            if(singleTestQuestion.getQuestions().size() > QUESTION_INDEX) {

                nextQuestion(singleTestQuestion.getQuestions().get(QUESTION_INDEX));
            }
        }else if(view == backBtnView){

            onBackPressed();
        } else if(view == submitBtnView){
            showDialog();
            int RESUT_COUND = 0;
            for (int i=0; i < singleTestQuestion.getQuestions().size(); i++){

                int answer = singleTestQuestion.getQuestions().get(i).getAnswerData();
                for(int j = 0; j < singleTestQuestion.getQuestions().get(i).getOptions().size(); j++) {

                    if(singleTestQuestion.getQuestions().get(i).getOptions().get(j).getIscorrect().equalsIgnoreCase("true")) {

                        if(answer == j){
                            RESUT_COUND++;
                            break;
                        }
                    }
                }
            }

            Log.d("Result", "Data RESULT :" + RESUT_COUND);
        }
    }

    private void showDialog(){

        MaterialDialog.Builder dialogBuilder = new MaterialDialog.Builder(this);
        dialogBuilder.setTitle(R.string.app_name);
        dialogBuilder.setMessage("Do you want to submit your test ?");
        dialogBuilder.setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {

        });
        dialogBuilder.setNegativeButton(android.R.string.cancel, null);
        MaterialDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private void showResultView(){

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
       /* builder.setView(R.layout.custom_dialog_content);
        builder.setCustomTitle(R.layout.custom_dialog_title);
        builder.setCustomMessage(R.layout.custom_dialog_message);
        builder.setCustomButtonBar(R.layout.custom_dialog_button_bar);
        builder.setCustomHeader(R.layout.custom_dialog_header);*/
    }
}
