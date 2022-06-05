package com.genesis.genesisonlineuniversity.ui.my_course;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.genesis.genesisonlineuniversity.R;
import com.genesis.genesisonlineuniversity.ui.my_course.adapter.MyCourseAdapter;

import java.util.Arrays;
import java.util.List;

public class MyCourseFragment extends Fragment {


    View rootView;
    Context context;
    RecyclerView recyclerView1, recyclerView2, recyclerView3;
    TextView tv_popular_programs, tv_e_library, tv_resources;

    MyCourseAdapter adapter;
    private List<String> popularTopicPriceList, popularTopicTitleList, popularTopicImageList;

    ColorStateList def;
    TextView item1;
    TextView item2;
    TextView item3;
    TextView select;

    LinearLayout linear_ongoing, linear_complete;


    public MyCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initStatusBar();
        rootView = inflater.inflate(R.layout.fragment_my_course, container, false);

        initComponents();
        loadListData();
        loadRecyclerView();

        return rootView;
    }

    private void initStatusBar() {
        View decor = getActivity().getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary, getActivity().getTheme()));
//            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            decor.setSystemUiVisibility(decor.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
//            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            decor.setSystemUiVisibility(decor.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light
        }

    }


    private void initComponents() {
        final MediaPlayer sound1 = MediaPlayer.create(getContext(), R.raw.sound1);

        recyclerView1 = rootView.findViewById(R.id.recyclerView1);
        recyclerView2 = rootView.findViewById(R.id.recyclerView2);
        recyclerView3 = rootView.findViewById(R.id.recyclerView3);

//        tv_popular_programs = rootView.findViewById(R.id.tv_popular_programs);
//        tv_e_library = rootView.findViewById(R.id.tv_e_library);
//        tv_resources = rootView.findViewById(R.id.tv_resources);
//
//
//        tv_popular_programs.setOnClickListener(view -> {
//            Intent intent = new Intent(getActivity(), PopularProgrammsActivity.class);
//            startActivity(intent);
//        });

        item1 = rootView.findViewById(R.id.item1);
        item2 = rootView.findViewById(R.id.item2);
        item3 = rootView.findViewById(R.id.item3);
        select = rootView.findViewById(R.id.select);
        def = item2.getTextColors();

        linear_ongoing = rootView.findViewById(R.id.linear_ongoing);
        linear_complete = rootView.findViewById(R.id.linear_complete);

        item1.setOnClickListener(v -> {
            sound1.start();
            select.animate().x(0).setDuration(100);
            item1.setTextColor(Color.WHITE);
            item2.setTextColor(def);

            linear_ongoing.setVisibility(View.VISIBLE);
            linear_complete.setVisibility(View.GONE);
        });

        item2.setOnClickListener(v -> {
            sound1.start();
            item1.setTextColor(def);
            item2.setTextColor(Color.WHITE);
            int size = item2.getWidth();
            select.animate().x(size).setDuration(100);

            linear_ongoing.setVisibility(View.GONE);
            linear_complete.setVisibility(View.VISIBLE);
        });


    }



    private void loadListData() {
        popularTopicPriceList = Arrays.asList(getResources().getStringArray(R.array.popular_topic_price_array));
        popularTopicTitleList = Arrays.asList(getResources().getStringArray(R.array.popular_topic_title_array));
        popularTopicImageList = Arrays.asList(getResources().getStringArray(R.array.popular_topic_image_array));
    }

    private void loadRecyclerView() {
        adapter = new MyCourseAdapter(context);
        recyclerView1.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        recyclerView1.setLayoutManager(mLayoutManager);
        adapter.setData(popularTopicImageList, popularTopicTitleList, popularTopicPriceList, false);
        adapter.notifyDataSetChanged();
    }

}