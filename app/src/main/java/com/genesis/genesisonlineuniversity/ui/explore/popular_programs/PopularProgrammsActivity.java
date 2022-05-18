package com.genesis.genesisonlineuniversity.ui.explore.popular_programs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.genesis.genesisonlineuniversity.R;
import com.genesis.genesisonlineuniversity.ui.explore.popular_programs.adapter.AllPopularProgramsAdapter;

import java.util.Arrays;
import java.util.List;

public class PopularProgrammsActivity extends AppCompatActivity {

    RecyclerView recyclerView1;
    ImageView iv_back;

    AllPopularProgramsAdapter adapter;
    private List<String> popularTopicPriceList, popularTopicTitleList, popularTopicImageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_programms);

        initStatusBar();
        initComponents();
        loadListData();
        loadRecyclerView();

    }

    private void initStatusBar() {
        View decor = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary, this.getTheme()));
//            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            decor.setSystemUiVisibility(decor.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
//            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            decor.setSystemUiVisibility(decor.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light
        }

    }



    private void initComponents() {

        recyclerView1 = findViewById(R.id.recyclerView1);
        iv_back = findViewById(R.id.iv_back);

        iv_back.setOnClickListener(view -> {
            finish();
        });

    }


    private void loadListData() {
        popularTopicPriceList = Arrays.asList(getResources().getStringArray(R.array.popular_topic_price_array));
        popularTopicTitleList = Arrays.asList(getResources().getStringArray(R.array.popular_topic_title_array));
        popularTopicImageList = Arrays.asList(getResources().getStringArray(R.array.popular_topic_image_array));
    }

    private void loadRecyclerView() {
        adapter = new AllPopularProgramsAdapter(this);
        recyclerView1.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView1.setLayoutManager(mLayoutManager);
        adapter.setData(popularTopicImageList, popularTopicTitleList, popularTopicPriceList, false);
        adapter.notifyDataSetChanged();
    }


}