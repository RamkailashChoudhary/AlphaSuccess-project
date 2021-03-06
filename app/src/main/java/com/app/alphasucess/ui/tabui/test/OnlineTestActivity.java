package com.app.alphasucess.ui.tabui.test;

import androidx.appcompat.app.AppCompatActivity;
import de.mrapp.android.dialog.MaterialDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.app.alphasucess.ui.view.CircleProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

public class OnlineTestActivity extends BaseActivity implements OnlineTestListener, View.OnClickListener {

    private int QUESTION_INDEX = 0;

    private SingleTestQuestion singleTestQuestion;
    private RelativeLayout nextBtnView,preBtnView,submitBtnView;
    private ImageView backBtnView;
    private FrameLayout frameLayoutView;
    private JSONArray testResultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_test);

        TextView header=findViewById(R.id.middleTitle);
        frameLayoutView = findViewById(R.id.ontestScreenView);
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

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class,MyApplication.REFRESH_TOKEN,this);
        restServiceLayer.singleTestQuestions("Bearer "+ MyApplication.AUTH_TOKEN,"1").enqueue(new Callback<ResoureData<SingleTestQuestion>>() {
            @Override
            public void onResponse(Call<ResoureData<SingleTestQuestion>> call, Response<ResoureData<SingleTestQuestion>> response) {
                if(response.body().getReplycode().equalsIgnoreCase("1")) {

                    singleTestQuestion = response.body().getData();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.ontestScreenView, TestScreenFragment.newInstance(singleTestQuestion.getQuestions().get(QUESTION_INDEX)))
                            .commitNow();
              //      Toast.makeText(OnlineTestActivity.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();
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
           // Toast.makeText(OnlineTestActivity.this, "" + singleTestQuestion.getQuestions().get(QUESTION_INDEX).getTestquestion(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void preQuestion(TestData testData) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ontestScreenView, TestScreenFragment.newInstance(testData))
                    .commitNow();
          //  Toast.makeText(OnlineTestActivity.this, "" + singleTestQuestion.getQuestions().get(QUESTION_INDEX).getTestquestion(), Toast.LENGTH_LONG).show();

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
        }
    }

    private void resultCalculation()throws Exception{

        int RESUT_COUND = 0;
        testResultData = new JSONArray();
        for (int i=0; i < singleTestQuestion.getQuestions().size(); i++){

            int answer = singleTestQuestion.getQuestions().get(i).getAnswerData();
            for(int j = 0; j < singleTestQuestion.getQuestions().get(i).getOptions().size(); j++) {

                if(singleTestQuestion.getQuestions().get(i).getOptions().get(j).getIscorrect().equalsIgnoreCase("true")) {

                    if(answer == j){
                       JSONObject obj = new JSONObject();
                       obj.put(singleTestQuestion.getQuestions().get(i).getId(),singleTestQuestion.getQuestions().get(i).getOptions().get(j).getId());
                       testResultData.put(obj);
                        RESUT_COUND++;
                        break;
                    }else{
                        JSONObject obj = new JSONObject();
                        obj.put(singleTestQuestion.getQuestions().get(i).getId(),singleTestQuestion.getQuestions().get(i).getOptions().get(j).getId());
                        testResultData.put(obj);
                    }
                }
            }
        }

        int totalQuestion = singleTestQuestion.getQuestions().size();
        Log.d("Result", "Total Size :"+totalQuestion);
        float percentageResult = ((RESUT_COUND * 100) / totalQuestion);
        Log.d("Result", "Divide data :"+percentageResult);
        Log.d("Result", "percentage :"+percentageResult);
        Log.d("Result", "Data RESULT :" + RESUT_COUND+"/"+singleTestQuestion.getQuestions().size());
       // showDialog(percentageResult);
        showResultView(percentageResult,RESUT_COUND+"/"+singleTestQuestion.getQuestions().size());

       perQuestionMarks =  Integer.parseInt(singleTestQuestion.getTotalmarks()) / totalQuestion;
       totalMarks = perQuestionMarks * RESUT_COUND;
    }

    int perQuestionMarks = 0;
    int totalMarks = 0;

    private void showDialog(){

        MaterialDialog.Builder dialogBuilder = new MaterialDialog.Builder(this);
        dialogBuilder.setTitle(R.string.app_name);
        dialogBuilder.setMessage("Do you want to submit your test ?");
        dialogBuilder.setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {

            try {
                resultCalculation();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        dialogBuilder.setNegativeButton(android.R.string.cancel, null);
        MaterialDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private void showResultView(float percentage,String overAllResultData){

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.result_body,viewGroup,false);
        builder.setView(view);
        MaterialDialog dialog = builder.create();
        dialog.show();
        TextView overAllResult = view.findViewById(R.id.overAllResult);
        overAllResult.setText(overAllResultData);
        LinearLayout okButtonView = view.findViewById(R.id.okDialogView);
        CircleProgressBar circleProgressBar = view.findViewById(R.id.progressBarPercentage);
        if(circleProgressBar != null){
            circleProgressBar.setText(""+percentage);
           circleProgressBar.setProgress(percentage);
        }else {
            Toast.makeText(this,"NULL FOUND",Toast.LENGTH_LONG).show();
        }
        okButtonView.setOnClickListener(view1 -> {
            Intent leaderBoardView = new Intent(OnlineTestActivity.this,LeaderboardActivity.class);
            leaderBoardView.putExtra("TestID",singleTestQuestion.getId());
            leaderBoardView.putExtra("Test-Result",testResultData.toString());
            leaderBoardView.putExtra("Test-Marks",singleTestQuestion.getTotalmarks()+"");
            startActivity(leaderBoardView);
            dialog.dismiss();
        });
    }
}
