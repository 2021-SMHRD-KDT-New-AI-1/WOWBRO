package com.jkh.wowbro2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Course2Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Course2VO> data;
//
    private LayoutInflater inflater;

    public Course2Adapter(Context context, int layout, List<Course2VO> data){
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

        ImageView img_3 = view.findViewById(R.id.img_3);
        ImageView img_4 = view.findViewById(R.id.img_4);
        ImageView img_clear2 = view.findViewById(R.id.img_clear2);
        TextView tv_name2 = view.findViewById(R.id.tv_name2);
        TextView tv_location2 = view.findViewById(R.id.tv_location2);

        img_3.setImageResource(data.get(i).getImg());
        img_4.setImageResource(R.drawable.thumb);
        img_clear2.setImageResource(R.drawable.clear2);
        img_clear2.setColorFilter(Color.parseColor("#66CCCC"));

        tv_name2.setText(data.get(i).getName());
        tv_location2.setText(data.get(i).getLocation());

        return  view;

    }
}
