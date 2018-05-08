package com.example.lenovo.finalgp_test1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.finalgp_test1.TripPlannerClasses.HotelClass;

import java.util.List;

/**
 * Created by Lenovo on 6/03/2018.
 */

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    private Context context;
    private List<HotelClass> list;

    public HotelAdapter(Context context, List<HotelClass> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(context).inflate(R.layout.single_hotel, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HotelClass hotelClass = list.get(position);
        holder.textHotel.setText(hotelClass.getHotel_name());
        holder.textStars.setText(String.valueOf(hotelClass.getHotel_stars()));

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textHotel, textStars;
        public ViewHolder(View itemView) {
            super(itemView);
            textHotel= itemView.findViewById(R.id.txtHotel);
            textStars= itemView.findViewById(R.id.txtStars);
        }
    }
}
