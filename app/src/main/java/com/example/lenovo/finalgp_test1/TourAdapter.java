package com.example.lenovo.finalgp_test1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.finalgp_test1.TripPlannerClasses.ToursClass;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by LENOVO on 2018-03-07.
 */

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.ViewHolder> {

    private Context context;
    private List<ToursClass> list;

    public TourAdapter(Context context, List<ToursClass> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_tour, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ToursClass toursClass = list.get(position);
        holder.textTourName.setText(toursClass.getTour_name());
        holder.textTourPrice.setText("$ " + String.valueOf(toursClass.getTour_price()));
        //Picasso.with(this.context).load(toursClass.getTour_img()).into(holder.tourImg);
        Picasso.with(this.context).load(toursClass.getTour_img()).into(holder.tourImg);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ReserveTourActivity.class);
                i.putExtra("tour_name", toursClass.getTour_name());
                i.putExtra("tour_price", toursClass.getTour_price());
                i.putExtra("tour_img", toursClass.getTour_img());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textTourName, textTourPrice;
        public RelativeLayout relativeLayout;
        public ImageView tourImg;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textTourName = itemView.findViewById(R.id.txtTourname);
            this.textTourPrice = itemView.findViewById(R.id.txtTourPrice);
            this.relativeLayout = itemView.findViewById(R.id.tour_parent);
            this.tourImg = itemView.findViewById(R.id.imgTour);
        }
    }
}