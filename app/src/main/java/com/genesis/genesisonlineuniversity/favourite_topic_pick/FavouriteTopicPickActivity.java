package com.genesis.genesisonlineuniversity.favourite_topic_pick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.genesis.genesisonlineuniversity.activities.MainActivity;
import com.genesis.genesisonlineuniversity.R;
import com.genesis.genesisonlineuniversity.favourite_topic_pick.adapter.FavouriteTopicPickerAdapter;

import java.util.Arrays;
import java.util.List;

public class FavouriteTopicPickActivity extends AppCompatActivity implements FavouriteTopicPickerAdapter.AdapterCallback {

    boolean passwordVissible;
    private ImageView iv_hi;
    private TextView tv_Terms_conditions, tv_sign_up, tv_sign_in, tv_forgot_password, tv_start_journey;
    private EditText etFullName, etEmail, etPhoneNumber, etPassword;
    private boolean nameIsEmpty = true;
    private boolean emailIsEmpty = true;
    private boolean phoneIsEmpty = true;
    private boolean passwordIsEmpty = true;

    private List<String> topicTitleList;
    private List<String> topicImageList;
    private RecyclerView recyclerView;
    private FavouriteTopicPickerAdapter favouriteTopicPickerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_topic_pick);

        initStatusBar();
        initComponents();
        loadListData();
        loadRecyclerView();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initComponents() {

        tv_sign_up = findViewById(R.id.tv_sign_up);
        tv_sign_in = findViewById(R.id.tv_sign_in);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);
        tv_Terms_conditions = findViewById(R.id.tv_Terms_conditions);
        tv_start_journey = findViewById(R.id.tv_start_journey);

        iv_hi = findViewById(R.id.iv_hi);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);

        recyclerView = findViewById(R.id.recyclerView);


        tv_start_journey.setOnClickListener(view -> { 
            Intent intent = new Intent(FavouriteTopicPickActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }


    private void loadListData() {
        topicTitleList = Arrays.asList(getResources().getStringArray(R.array.favourite_topic_title_array));
        topicImageList = Arrays.asList(getResources().getStringArray(R.array.favourite_topic_image_array));
    }

    private void loadRecyclerView() {
        favouriteTopicPickerAdapter = new FavouriteTopicPickerAdapter(this);
        recyclerView.setAdapter(favouriteTopicPickerAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        favouriteTopicPickerAdapter.setData(topicTitleList, topicImageList);
        favouriteTopicPickerAdapter.notifyDataSetChanged();
    }



    public void checkStartJourneyButtonActive(List<String> favTopicList) {
        if (favTopicList.size()>0) {
            tv_start_journey.setBackground(getResources().getDrawable(R.drawable.rounded_corner1));
            tv_start_journey.setPadding(45, 40, 45, 40);
        } else {
            tv_start_journey.setBackground(getResources().getDrawable(R.drawable.rounded_corner5));
            tv_start_journey.setPadding(45, 40, 45, 40);
        }
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


    @Override
    public void onMethodCallback(List<String> favTopicList) {
        checkStartJourneyButtonActive(favTopicList);
    }

    @Override
    public void onMethodCallback() {

    }


}