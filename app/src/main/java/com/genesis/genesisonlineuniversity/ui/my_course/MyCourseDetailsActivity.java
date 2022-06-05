package com.genesis.genesisonlineuniversity.ui.my_course;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.genesis.genesisonlineuniversity.R;

import org.w3c.dom.Text;

public class MyCourseDetailsActivity extends AppCompatActivity {

    private LinearLayout linear_details, linear_back;
    private TextView tv_hide_details;
    private ImageView iv_arrow_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_course_details);
        initStatusBar();
        initComponents();
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

    private void initComponents() {

        final MediaPlayer sound1 = MediaPlayer.create(this, R.raw.sound1);

        linear_details = findViewById(R.id.linear_details);
        linear_back = findViewById(R.id.linear_back);

        tv_hide_details = findViewById(R.id.tv_hide_details);

        iv_arrow_up = findViewById(R.id.iv_arrow_up);

        tv_hide_details.setOnClickListener(v -> {
            if (tv_hide_details.getText().toString().equalsIgnoreCase("Hide Details")){
                tv_hide_details.setText("Show Details");
                linear_details.setVisibility(View.GONE);
                iv_arrow_up.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down));
            }else {
                tv_hide_details.setText("Hide Details");
                linear_details.setVisibility(View.VISIBLE);
                iv_arrow_up.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_up));
            }

        });

        linear_back.setOnClickListener(v -> {
            sound1.start();
            finish();
        });


    }


}