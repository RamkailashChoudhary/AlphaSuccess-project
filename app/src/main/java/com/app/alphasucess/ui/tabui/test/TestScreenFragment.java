package com.app.alphasucess.ui.tabui.test;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.alphasucess.R;
import com.app.alphasucess.ui.view.CircleTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TestScreenFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.test_screen, container, false);
        final CircleTextView circleTextViewA = root.findViewById(R.id.questionA);
        final CircleTextView circleTextViewB = root.findViewById(R.id.questionB);
        final CircleTextView circleTextViewC = root.findViewById(R.id.questionC);
        final CircleTextView circleTextViewD = root.findViewById(R.id.questionD);
        circleTextViewA.setCustomText("A");
        circleTextViewB.setCustomText("B");
        circleTextViewC.setCustomText("C");
        circleTextViewD.setCustomText("D");
    //    circleTextView.setTextColor(Color.WHITE);
        return root;
    }
}