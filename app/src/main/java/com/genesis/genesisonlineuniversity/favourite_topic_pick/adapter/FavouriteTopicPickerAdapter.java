package com.genesis.genesisonlineuniversity.favourite_topic_pick.adapter;


import android.content.Context;
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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FavouriteTopicPickerAdapter extends RecyclerView.Adapter<FavouriteTopicPickerAdapter.FavouriteTopicPickerViewHolder> {

    private List<String> dataSet = new ArrayList<>();
    private List<String> topicTitleList = new ArrayList<>();
    private List<String> topicImageList = new ArrayList<>();
    private List<String> title_code_list = new ArrayList<>();
    Context context;

    Cursor dataCursor;

    private List<String> favTopicList = new ArrayList<>();
    HashMap<Object, String> cartWithTitleName = new HashMap<Object, String>();

    String categoryName = "";

    String title;
    String category_id;
    private List<String> memberListFiltered = new ArrayList<>();


    private AdapterCallback adapterCallback;

    public FavouriteTopicPickerAdapter(Context context) {
        this.context = context;

        try {
            adapterCallback = ((AdapterCallback) context);
        } catch (ClassCastException e) {
//            throw new ClassCastException("Activity must implement AdapterCallback.", e);
        }

    }


//    public static void updateCartList(List<Product> cartList){
//        this.cart = cartList;
//    }


    public FavouriteTopicPickerAdapter(Context context, Cursor cursor) {
        dataCursor = cursor;
        this.context = context;
        Log.d("tag1wwwww", " adapter: ");
    }

    public void setTitle(String title, String categoryId) {
//        this.categoryName = categoryName;
        this.title = title;
        this.category_id = categoryId;
    }

//    public void setData(List<String> dataSet) {
//        this.dataSet = dataSet;
//    }

    public void setData(List<String> topicTitleList, List<String> topicImageList) {
        this.topicTitleList = topicTitleList;
        this.topicImageList = topicImageList;
        this.title_code_list = title_code_list;
        Log.d("tagResponse", " topicTitleList: " + topicTitleList);
    }


    @Override
    public int getItemCount() {
        return topicTitleList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public FavouriteTopicPickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_favourite_topic_list, parent, false);
        FavouriteTopicPickerViewHolder myViewHolder = new FavouriteTopicPickerViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listener.onItemClick(v, myViewHolder.getPosition());
            }
        });
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final FavouriteTopicPickerAdapter.FavouriteTopicPickerViewHolder holder, int position) {
        TextView txtSlNo = holder.txtSlNo;
        TextView tv_title = holder.tv_title;
        TextView tv_remove_item = holder.tv_remove_item;
        LinearLayout linear1 = holder.linear1;
        RelativeLayout relative1 = holder.relative1;
        ImageView imageView = holder.imageView;
        ImageView imageViewTick = holder.imageViewTick;
        CardView cardview = holder.cardview;


        imageView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
//        cardview.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fall_down_animation));


        String topicTitle = topicTitleList.get(position);
        String topicImage = topicImageList.get(position);


        Log.d("tag333", " topicTitleList...: " + topicTitleList);
        Log.d("tag333", " favTopicList...: " + favTopicList);


//        tv_title.setText(doctorInfo.getDiseases_bangla());
//        tv_doctor_designation.setText(doctorInfo.getTreeName_bangla());
//
        tv_title.setText(topicTitle);
//        tv_doctor_designation.setText(doctorInfo3);


        Glide.with(context).load(topicImage).into(imageView);

        relative1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (imageViewTick.getVisibility() == View.GONE) {
                    relative1.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner6));
//                    relative1.setPadding(90, 80, 90, 80);
                    imageViewTick.setVisibility(View.VISIBLE);
//                    passwordIsEmpty = false;

                    favTopicList.add(topicTitle);
                    Log.d("tag333", " favTopicList...: " + favTopicList);
                    adapterCallback.onMethodCallback(favTopicList);
//
                } else {
                    relative1.setBackground(context.getResources().getDrawable(R.drawable.rounded_corner7));
//                    relative1.setPadding(90, 80, 90, 80);
                    imageViewTick.setVisibility(View.GONE);

                    favTopicList.remove(topicTitle);
                    Log.d("tag333", " favTopicList...: " + favTopicList);
                    adapterCallback.onMethodCallback(favTopicList);

                }

            }
        });


    }


    class FavouriteTopicPickerViewHolder extends RecyclerView.ViewHolder {

        TextView txtSlNo;
        TextView tv_title;
        TextView tv_remove_item;
        LinearLayout linear1;
        RelativeLayout relative1;

        ImageView imageView;
        ImageView imageViewTick;
        CardView cardview;

        public FavouriteTopicPickerViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            imageView = itemView.findViewById(R.id.imageView);
            imageViewTick = itemView.findViewById(R.id.imageViewTick);
            relative1 = itemView.findViewById(R.id.relative1);
//            cardview = itemView.findViewById(R.id.cardview);

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
//            memberListFiltered = topicTitleList;
//        } else {
////         List<MemberInfoModel.Result> filteredList = new ArrayList<>();
//            for (String row : topicTitleList) {
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

