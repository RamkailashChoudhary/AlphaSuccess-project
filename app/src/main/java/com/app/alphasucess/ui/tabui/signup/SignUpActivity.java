package com.app.alphasucess.ui.tabui.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.R;
import com.app.alphasucess.ui.tabui.signup.SignUpFragment;

public class SignUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        TextView title=findViewById(R.id.middleTitle);
        title.setText("Sign Up");
        final ImageView backBtnView = findViewById(R.id.backBtnView);
        backBtnView.setOnClickListener(view -> {
            onBackPressed();
        });
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SignUpFragment.newInstance())
                    .commitNow();
        }
    }
}
