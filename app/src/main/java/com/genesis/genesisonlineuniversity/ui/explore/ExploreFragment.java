package com.genesis.genesisonlineuniversity.ui.explore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.genesis.genesisonlineuniversity.R;
import com.genesis.genesisonlineuniversity.ui.explore.adapter.ElibraryAdapter;
import com.genesis.genesisonlineuniversity.ui.explore.adapter.PopularProgramsAdapter;
import com.genesis.genesisonlineuniversity.ui.explore.adapter.ResourcesAdapter;
import com.genesis.genesisonlineuniversity.ui.explore.popular_programs.PopularProgrammsActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Arrays;
import java.util.List;


public class ExploreFragment extends Fragment {

    View rootView;
    Context context;
    RelativeLayout rl2, rl3;
    RecyclerView recyclerView1, recyclerView2, recyclerView3;
    TextView tv_popular_programs, tv_e_library, tv_resources;

    PopularProgramsAdapter popularProgramsAdapter;
    ElibraryAdapter elibraryAdapter;
    ResourcesAdapter resourcesAdapter;
    private List<String> popularTopicPriceList, popularTopicTitleList, popularTopicImageList, elibraryImageList, resourcesTitleList, resourceImageList;


    public ExploreFragment() {

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
        rootView = inflater.inflate(R.layout.fragment_explore, container, false);

        initComponents();
        loadListData();
        loadRecyclerView();
        loadRecyclerView2();
        loadRecyclerView3();

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

        rl2 = rootView.findViewById(R.id.rl2);
        rl3 = rootView.findViewById(R.id.rl3);

        recyclerView1 = rootView.findViewById(R.id.recyclerView1);
        recyclerView2 = rootView.findViewById(R.id.recyclerView2);
        recyclerView3 = rootView.findViewById(R.id.recyclerView3);

        tv_popular_programs = rootView.findViewById(R.id.tv_popular_programs);
        tv_e_library = rootView.findViewById(R.id.tv_e_library);
        tv_resources = rootView.findViewById(R.id.tv_resources);


//        tv_popular_programs.setOnClickListener(view -> {
//            if (tv_popular_programs.getText().toString().equalsIgnoreCase("See all")){
//                tv_popular_programs.setText("See less");
//
//                popularProgramsAdapter.setData(popularTopicImageList, popularTopicTitleList, popularTopicPriceList, true);
//                popularProgramsAdapter.notifyDataSetChanged();
//            }else {
//                tv_popular_programs.setText("See all");
//
//                popularProgramsAdapter.setData(popularTopicImageList, popularTopicTitleList, popularTopicPriceList, false);
//                popularProgramsAdapter.notifyDataSetChanged();
//            }
//
//        });

        tv_popular_programs.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), PopularProgrammsActivity.class);
            startActivity(intent);
        });

        rl3.setOnClickListener(view -> {
            showBottomSheetDialog();
        });


    }

    //
    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.BSheetDialog);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_explore_notification);

        LinearLayout linear_cross = bottomSheetDialog.findViewById(R.id.linear_cross);

        linear_cross.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }


    private void loadListData() {
        popularTopicPriceList = Arrays.asList(getResources().getStringArray(R.array.popular_topic_price_array));
        popularTopicTitleList = Arrays.asList(getResources().getStringArray(R.array.popular_topic_title_array));
        popularTopicImageList = Arrays.asList(getResources().getStringArray(R.array.popular_topic_image_array));

        elibraryImageList = Arrays.asList(getResources().getStringArray(R.array.elibrary_image_array));

        resourcesTitleList = Arrays.asList(getResources().getStringArray(R.array.resources_title_array));
        resourceImageList = Arrays.asList(getResources().getStringArray(R.array.resources_image_array));
    }

    private void loadRecyclerView() {
        popularProgramsAdapter = new PopularProgramsAdapter(context);
        recyclerView1.setAdapter(popularProgramsAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView1.setLayoutManager(mLayoutManager);
        popularProgramsAdapter.setData(popularTopicImageList, popularTopicTitleList, popularTopicPriceList, false);
        popularProgramsAdapter.notifyDataSetChanged();
    }

    private void loadRecyclerView2() {
        elibraryAdapter = new ElibraryAdapter(context);
        recyclerView2.setAdapter(elibraryAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView2.setLayoutManager(mLayoutManager);
        elibraryAdapter.setData(elibraryImageList, popularTopicTitleList, popularTopicPriceList, false);
        elibraryAdapter.notifyDataSetChanged();
    }

    private void loadRecyclerView3() {
        resourcesAdapter = new ResourcesAdapter(context);
        recyclerView3.setAdapter(resourcesAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView3.setLayoutManager(mLayoutManager);
        resourcesAdapter.setData(resourceImageList, resourcesTitleList, popularTopicPriceList, false);
        resourcesAdapter.notifyDataSetChanged();
    }


}