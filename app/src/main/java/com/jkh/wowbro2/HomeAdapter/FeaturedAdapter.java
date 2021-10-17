package com.jkh.wowbro2.HomeAdapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jkh.wowbro2.R;
import com.jkh.wowbro2.ThemeActivity1;
import com.jkh.wowbro2.ThemeActivity2;
import com.jkh.wowbro2.ThemeActivity3;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

     ArrayList<FeaturedHelperClass> featuredLocations ;
     Context context;

//

     public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredLocations, Context context) {
          this.featuredLocations = featuredLocations;
          this.context = context;

     }

     @NonNull
     @Override
     public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


          View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
          FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);


          return featuredViewHolder;
     }

     @Override
     public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

          FeaturedHelperClass featuredHelperClass = featuredLocations.get(position);

          holder.image.setImageResource(featuredHelperClass.getImage());
          holder.title.setText(featuredHelperClass.getTitle());


     }

     @Override
     public int getItemCount() {
          return featuredLocations.size();
     }


     public class FeaturedViewHolder extends RecyclerView.ViewHolder{

          ImageView image;
          TextView title;


          public FeaturedViewHolder(@NonNull View itemView) {
               super(itemView);

               //hooks;
               image = itemView.findViewById(R.id.fetured_image);
               title = itemView.findViewById(R.id.featured_text);


               itemView.setClickable(true);
               itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         Log.d("Recyclerview", "position = "+ getAdapterPosition());

                         if(getAdapterPosition()==0){
                              Intent intent = new Intent(context, ThemeActivity1.class);
                              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                              context.startActivity(intent);
                         }
                         if(getAdapterPosition()==1){
                              Intent intent = new Intent(context, ThemeActivity2.class);
                              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                              context.startActivity(intent);
                         }
                         if(getAdapterPosition()==2){
                              Intent intent = new Intent(context, ThemeActivity3.class);
                              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                              context.startActivity(intent);
                         }
                         //포지션 1,2,3,4 일때 각각 액티비티로 보내주면 됨





                    }
               });

          }
     }
}

