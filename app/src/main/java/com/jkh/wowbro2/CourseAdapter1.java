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

public class CourseAdapter1 extends BaseAdapter {

    private Context context;
    private int layout;
    private List<CourseVO1> data;

    private LayoutInflater inflater;

    public CourseAdapter1(Context context, int layout, List<CourseVO1> data) {
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

       ImageView img_1 = view.findViewById(R.id.img_1);
       ImageView img_clear = view.findViewById(R.id.img_clear);
       TextView tv_name = view.findViewById(R.id.tv_name);
       TextView tv_location = view.findViewById(R.id.tv_location);
       ImageView img_2 = view.findViewById(R.id.img_2);

       img_1.setImageResource(data.get(i).getImg());
       img_clear.setImageResource(R.drawable.clear2);
       img_2.setImageResource(R.drawable.thumb);
       img_clear.setColorFilter(Color.parseColor("#FFD740"));



       tv_name.setText(data.get(i).getName());
       tv_location.setText(data.get(i).getLocation());

        return view;
    }
}
