package com.genesis.genesisonlineuniversity.intro_pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.genesis.genesisonlineuniversity.R;
import com.genesis.genesisonlineuniversity.credential.LoginActivity;
import com.genesis.genesisonlineuniversity.credential.RegisterActivity;

public class IntroActivity extends AppCompatActivity {

    final ThreadLocal<ViewPager> mSLideViewPager = new ThreadLocal<ViewPager>();
    LinearLayout mDotLayout;
    private Button backbtn, nextbtn, skipbtn;
    private TextView tv_get_started, tv_already_have_account;

    //    TextView[] dots;
    View[] dots;
    IntroViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        initStatusBar();
        initComponents();

    }

    private void initComponents() {

        backbtn = findViewById(R.id.backbtn);
        nextbtn = findViewById(R.id.nextbtn);
        skipbtn = findViewById(R.id.skipButton);

        tv_get_started = findViewById(R.id.tv_get_started);
        tv_already_have_account = findViewById(R.id.tv_already_have_account);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) > 0) {

                    mSLideViewPager.get().setCurrentItem(getitem(-1), true);

                }

            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 2)
                    mSLideViewPager.get().setCurrentItem(getitem(1), true);
                else {

                    Intent i = new Intent(IntroActivity.this, RegisterActivity.class);
                    startActivity(i);
                    finish();

                }

            }
        });

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        tv_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        tv_already_have_account.setOnClickListener(view -> {
            Intent i = new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        });



        mSLideViewPager.set((ViewPager) findViewById(R.id.slideViewPager));
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);

        viewPagerAdapter = new IntroViewPagerAdapter(this);

        mSLideViewPager.get().setAdapter(viewPagerAdapter);

        setUpindicator(0);
        mSLideViewPager.get().addOnPageChangeListener(viewListener);

    }


    private void initStatusBar() {
        View decor = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.background, this.getTheme()));
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.background));
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }



    public void setUpindicator(int position) {

//        dots = new TextView[3];
        dots = new View[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {

//            dots[i] = new TextView(this);
////            dots[i].setText(Html.fromHtml("&#8226"));
//            dots[i].setText("  ------  ");
////            dots[i].setBackgroundResource(R.drawable.line_horizontal1);
//            dots[i].setTextSize(35);

            dots[i] = new View(this);
            dots[i].setLayoutParams(new LinearLayout.LayoutParams(
                    260,
                    10
            ));

            dots[i].setPadding(30, 20, 30, 0);

//            dots[i].setBackgroundColor(Color.parseColor("#B3B3B3"));
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) dots[i].getLayoutParams();
            params.setMargins(0, 0, 30, 0);
            dots[i].setLayoutParams(params);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                dots[i].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
                dots[i].setBackgroundColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
            }

            mDotLayout.addView(dots[i]);

        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            dots[position].setTextColor(getResources().getColor(R.color.active, getApplicationContext().getTheme()));
            dots[position].setBackgroundColor(getResources().getColor(R.color.active, getApplicationContext().getTheme()));
        }

    }


    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

            if (position > 0) {

                backbtn.setVisibility(View.VISIBLE);

            } else {

                backbtn.setVisibility(View.INVISIBLE);

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i) {

        return mSLideViewPager.get().getCurrentItem() + i;
    }


}