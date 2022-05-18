package com.genesis.genesisonlineuniversity.ui.explore.popular_programs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.genesis.genesisonlineuniversity.R;

public class PopularProgrammsDetailsActivity extends AppCompatActivity {

    private TextView tv_enroll_course_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_programms_details);

        initStatusBar();
        initComponents();

    }

    private void initComponents() {
        tv_enroll_course_now = findViewById(R.id.tv_enroll_course_now);

        tv_enroll_course_now.setOnClickListener(v -> {
            Intent intent = new Intent(PopularProgrammsDetailsActivity.this, TransactionSuccessActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initStatusBar() {
        View decor = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white, this.getTheme()));
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            decor.setSystemUiVisibility(decor.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            decor.setSystemUiVisibility(decor.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light
        }

    }

}