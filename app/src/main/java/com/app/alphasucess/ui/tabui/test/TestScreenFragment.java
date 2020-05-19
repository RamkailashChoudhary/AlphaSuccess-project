package com.app.alphasucess.ui.tabui.test;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.test.adapters.TestData;
import com.app.alphasucess.ui.view.CircleTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TestScreenFragment extends Fragment implements View.OnClickListener {

    private TestData testData;
    private RadioButton answerA,answerB,answerC,answerD,answerSelected;

    public static TestScreenFragment newInstance(TestData testData){
        TestScreenFragment testScreenFragment = new TestScreenFragment();
        testScreenFragment.testData = testData;
        return testScreenFragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.test_screen, container, false);
        final CircleTextView circleTextViewA = root.findViewById(R.id.questionA);
        final CircleTextView circleTextViewB = root.findViewById(R.id.questionB);
        final CircleTextView circleTextViewC = root.findViewById(R.id.questionC);
        final CircleTextView circleTextViewD = root.findViewById(R.id.questionD);

        answerA = root.findViewById(R.id.answerA);
        answerB = root.findViewById(R.id.answerB);
        answerC = root.findViewById(R.id.answerC);
        answerD = root.findViewById(R.id.answerD);

        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);

        final TextView testQuestion = root.findViewById(R.id.testQuestion);
        final TextView optionA = root.findViewById(R.id.optionA);
        final TextView optionB = root.findViewById(R.id.optionB);
        final TextView optionC = root.findViewById(R.id.optionC);
        final TextView optionD = root.findViewById(R.id.optionD);
        testQuestion.setText(testData.getTestquestion());
        optionA.setText(testData.getOptions().get(0).getOptiontext());
        optionB.setText(testData.getOptions().get(1).getOptiontext());
        optionC.setText(testData.getOptions().get(2).getOptiontext());
        optionD.setText(testData.getOptions().get(3).getOptiontext());
        circleTextViewA.setCustomText("A");
        circleTextViewB.setCustomText("B");
        circleTextViewC.setCustomText("C");
        circleTextViewD.setCustomText("D");
        return root;
    }

    @Override
    public void onClick(View view) {
        if(view == answerA){
            if(answerSelected == null) {
                answerSelected = answerA;
                answerA.setChecked(true);
            }else{
                answerSelected.setChecked(false);
                answerSelected = answerA;
                answerA.setChecked(true);
            }
            testData.setAnswerData(0);
        }else if(view == answerB){
            if(answerSelected == null) {
                answerSelected = answerB;
                answerB.setChecked(true);
            }else{
                answerSelected.setChecked(false);
                answerSelected = answerB;
                answerB.setChecked(true);
            }
            testData.setAnswerData(1);
        }else if(view == answerC){
            if(answerSelected == null) {
                answerSelected = answerC;
                answerC.setChecked(true);
            }else{
                answerSelected.setChecked(false);
                answerSelected = answerC;
                answerC.setChecked(true);
            }
            testData.setAnswerData(2);
        }else if(view == answerD){
            if(answerSelected == null) {
                answerSelected = answerD;
                answerD.setChecked(true);
            }else{
                answerSelected.setChecked(false);
                answerSelected = answerD;
                answerD.setChecked(true);
            }
            testData.setAnswerData(3);
        }
    }
}