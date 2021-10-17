package com.jkh.wowbro2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RankingAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<RankingVO> data;

    private LayoutInflater inflater;
    public RankingAdapter(Context context, int layout, List<RankingVO> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        );
}
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view ==null){
            view = inflater.inflate(layout, null);
        }


        ImageView rank_img = view.findViewById(R.id.rank_img);
        TextView rank_name = view.findViewById(R.id.rank_name);
        TextView ranking_num = view.findViewById(R.id.ranking_num);
        Glide.with(context).load(data.get(i).getImgPath()).into(rank_img);

        rank_name.setText(data.get(i).getDesName());
        ranking_num.setText(String.valueOf(data.get(i).getLike_check()));

        return view;
    }
}
