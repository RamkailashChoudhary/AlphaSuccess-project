package com.app.alphasucess.ui.tabui.test;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.test.adapters.TestData;
import com.app.alphasucess.ui.view.CircleTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TestScreenFragment extends Fragment {

    private TestData testData;
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
}