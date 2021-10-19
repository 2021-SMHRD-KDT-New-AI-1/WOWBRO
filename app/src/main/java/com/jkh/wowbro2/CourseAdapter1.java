package com.jkh.wowbro2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CourseAdapter1 extends BaseAdapter {

    private Context context;
    private int layout;
    private List<CourseVO1> data;
    RequestQueue requestQueue;
    JSONArray desInfo;
    private LayoutInflater inflater;
    Context here ;


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

        ImageView img_1 = view.findViewById(R.id.rank_img);
        ImageView img_clear = view.findViewById(R.id.img_clear);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_location = view.findViewById(R.id.ranking_num);
        ImageView img_2 = view.findViewById(R.id.img_2);

        Glide.with(context).load(data.get(i).getImgPath()).into(img_1);

        img_2.setImageResource(R.drawable.thumb);
        img_clear.setColorFilter(Color.parseColor("#FFD740"));

        tv_name.setText(data.get(i).getName());
        tv_location.setText(data.get(i).getLocation());

        String desname = (String) tv_name.getText();
        here = context.getApplicationContext();

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(here);
        }

        String url = "http://10.0.2.2:3002/selectDes";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            desInfo = new JSONArray(response);
                            for (int i = 0;i<desInfo.length();i++) {
                                JSONObject info = null;
                                String user_id = "";
                                String imgPath = "";
                                String desName = "";
                                String desAddress = "";
                                String story = "";
                                String sub_name = "";
                                int like_check ;
                                String page = "";
                                String qr_check = "";
                                try {
                                    info = (JSONObject) desInfo.get(i);
                                    user_id = info.getString("user_id");
                                    imgPath = info.getString("desImagePath");
                                    desName = info.getString("desName");
                                    desAddress = info.getString("desAddress");
                                    story = info.getString("story");
                                    sub_name = info.getString("sub_name");
                                    like_check = info.getInt("like_check");
                                    page = info.getString("page");
                                    qr_check =info.getString("qr_check");



                                    //디비에 들어오는대로 이름값이랑 맞춰서 그림바꿔주면 됩니다.



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(request);



        return view;
    }
}
