package com.genesis.genesisonlineuniversity.ui.explore.adapter;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.genesis.genesisonlineuniversity.R;
import com.genesis.genesisonlineuniversity.ui.explore.popular_programs.PopularProgrammsDetailsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PopularProgramsAdapter extends RecyclerView.Adapter<PopularProgramsAdapter.PopularProgramsViewHolder> {

    private List<String> dataSet = new ArrayList<>();
    private List<String> popularTopicImageList = new ArrayList<>();
    private List<String> popularTopicTitleList = new ArrayList<>();
    private List<String> popularTopicPriceList = new ArrayList<>();
    private boolean allItemStatus = false;
    Context context;

    Cursor dataCursor;

//    private List<String> favTopicList = new ArrayList<>();


    String categoryName = "";

    String title;
    String category_id;
    private List<String> memberListFiltered = new ArrayList<>();


    private AdapterCallback adapterCallback;

    public PopularProgramsAdapter(Context context) {
        this.context = context;

        try {
            adapterCallback = ((AdapterCallback) context);
        } catch (ClassCastException e) {
//            throw new ClassCastException("Activity must implement AdapterCallback.", e);
        }

    }


    public PopularProgramsAdapter(Context context, Cursor cursor) {
        dataCursor = cursor;
        this.context = context;
        Log.d("tag1wwwww", " adapter: ");
    }


    public void setData(List<String> popularTopicImageList, List<String> popularTopicTitleList,
                        List<String> popularTopicPriceList, boolean allItemStatus) {
        this.popularTopicImageList = popularTopicImageList;
        this.popularTopicTitleList = popularTopicTitleList;
        this.popularTopicPriceList = popularTopicPriceList;
        this.allItemStatus = allItemStatus;
        Log.d("tagResponse", " popularTopicImageList: " + popularTopicImageList);
    }


    @Override
    public int getItemCount() {
        return popularTopicTitleList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public PopularProgramsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_popular_topic_list, parent, false);
        PopularProgramsViewHolder myViewHolder = new PopularProgramsViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listener.onItemClick(v, myViewHolder.getPosition());
            }
        });
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final PopularProgramsAdapter.PopularProgramsViewHolder holder, int position) {
        TextView txtSlNo = holder.txtSlNo;
        TextView tv_title = holder.tv_title;
        TextView tv_price = holder.tv_price;
        TextView tv_remove_item = holder.tv_remove_item;
        LinearLayout linear1 = holder.linear1;
        RelativeLayout relative1 = holder.relative1;
        ImageView imageView = holder.imageView;
        ImageView imageViewTick = holder.imageViewTick;
        CardView card1 = holder.card1;


//        imageView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
//        cardview.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fall_down_animation));


        String title = popularTopicTitleList.get(position);
        String image = popularTopicImageList.get(position);
        String price = popularTopicPriceList.get(position);


        Log.d("tag333", " popularTopicTitleList...: " + popularTopicTitleList);


//        tv_title.setText(doctorInfo.getDiseases_bangla());
//        tv_doctor_designation.setText(doctorInfo.getTreeName_bangla());
//
        tv_title.setText(title);
        tv_price.setText("\u09F3 " + price + " BDT");
//        tv_doctor_designation.setText(doctorInfo3);

//        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(mRecyclerView.getLayoutParams());
//        marginLayoutParams.setMargins(0, 10, 0, 10);
//        mRecyclerView.setLayoutParams(marginLayoutParams);


        if (position > 3) {
            card1.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }


//        if (allItemStatus == true) {
//            holder.itemView.setVisibility(View.VISIBLE);
//            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        } else {
//            if (position > 3) {
//                card1.setVisibility(View.GONE);
//                holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//            } else {
//                holder.itemView.setVisibility(View.VISIBLE);
//                holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            }
//        }


        Glide.with(context).load(image).into(imageView);

        relative1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, PopularProgrammsDetailsActivity.class);
                context.startActivity(intent);

//                if (imageViewTick.getVisibility() == View.GONE) {
//                    relative1.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner6));
////                    relative1.setPadding(90, 80, 90, 80);
//                    imageViewTick.setVisibility(View.VISIBLE);
////                    passwordIsEmpty = false;
//
//                    favTopicList.add(title);
//                    Log.d("tag333", " favTopicList...: " + favTopicList);
//                    adapterCallback.onMethodCallback(favTopicList);
////
//                } else {
//                    relative1.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner7));
////                    relative1.setPadding(90, 80, 90, 80);
//                    imageViewTick.setVisibility(View.GONE);
//
//                    favTopicList.remove(title);
//                    Log.d("tag333", " favTopicList...: " + favTopicList);
//                    adapterCallback.onMethodCallback(favTopicList);
//
//                }

            }
        });


    }


    class PopularProgramsViewHolder extends RecyclerView.ViewHolder {

        TextView txtSlNo;
        TextView tv_title;
        TextView tv_price;
        TextView tv_remove_item;
        LinearLayout linear1;
        RelativeLayout relative1;

        ImageView imageView;
        ImageView imageViewTick;
        CardView card1;

        public PopularProgramsViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_price = itemView.findViewById(R.id.tv_price);
            imageView = itemView.findViewById(R.id.imageView);
            imageViewTick = itemView.findViewById(R.id.imageViewTick);
            relative1 = itemView.findViewById(R.id.relative1);
            linear1 = itemView.findViewById(R.id.linear1);
            card1 = itemView.findViewById(R.id.card1);

        }
    }


//    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
//    public void filter(CharSequence charText) {
//
//        List<String> filteredList = new ArrayList<>();
//        String charString = charText.toString();
//
//        if (charString.length() == 0) {
//////         memberListFiltered = dataSet;
////         filteredList.addAll(dataSet);
//            Log.i("tag", String.valueOf("1:  "+charString.length())+dataSet);
//
//
//        }
//
//        if (charString.isEmpty() || charString.equalsIgnoreCase("")) {
//            memberListFiltered = titleList;
//        } else {
////         List<MemberInfoModel.Result> filteredList = new ArrayList<>();
//            for (String row : titleList) {
//                if (
//                        row.toLowerCase().contains(charString.toLowerCase())
////                                ||
////                                row.getMobile().toLowerCase().contains(charString.toLowerCase())
////                                ||
////                                row.getYear().toLowerCase().contains(charString.toLowerCase())
////                                ||
////                                row.getMember_id_String().toLowerCase().contains(charString.toLowerCase())
////                             ||
////                             row.getTakaAmount().toLowerCase().contains(charString.toLowerCase()) ||
////                             row.getPaymentStatus().toLowerCase().contains(charString.toLowerCase())
//                ) {
//                    filteredList.add(row);
//                }
//            }
//
//            Log.i("tag", "2:  "+String.valueOf(charString.length())+filteredList);
//
//            memberListFiltered = filteredList;
//        }
//
////     Filter.FilterResults filterResults = new Filter.FilterResults();
////     filterResults.values = memberListFiltered;
//        this.setData(memberListFiltered, memberListFiltered, memberListFiltered);
//        this.notifyDataSetChanged();
//    }


    public interface AdapterCallback {
        void onMethodCallback(List<String> favTopicList);

        void onMethodCallback();
    }


}

